package com.amway.amwaydms.services.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import com.amway.core.annotations.AmwayBean;
import com.amway.core.data.CommonResponseFieldsData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.log4j.Logger;

import com.amway.core.enums.AmwayInfraAvlStatus;
import com.amway.core.infraavail.service.AmwayInfraAvailabilityService;
import com.amway.core.charon.client.services.CharonClientConfigService;
import com.amway.core.model.AmwayInfraAvailabilityModel;

import com.amway.core.data.CommonRequestFieldsData;

import rx.Observable;
import rx.functions.Action1;
import com.hybris.charon.exp.HttpException;
import rx.Subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @param <X>
 * @param <Y>
 * @param <Z>
 * @author ashishalishetty
 */
@AmwayBean(ext="amwaydms2",docs="https://jira.amway.com:8444/display/HC/amwaydms2," +
        "https://jira.amway.com:8444/display/HC/AbstractDmsService")
public abstract class AbstractDmsService<X, Y, Z> {

    private static final Logger LOG = Logger.getLogger(AbstractDmsService.class);


    private static final String xclientRefId = "x-ref";
    private String urlPath;
    private String serviceAvailCode;

    private String defaultCronAppId = "HybrisCronAppId";
    private String defaultProxyAppId = "HybrisProxyAppId";
    private AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService;

    private CharonClientConfigService charonClientConfigService;

    private Converter inputConverter;
    private Converter outputConverter;


    protected abstract X createResultObject();

    protected abstract X createDefaultResult();

    protected abstract Z executeEvent(Object input);

    protected Z executeFallbackEvent(final Object input) {
        LOG.debug("calling execute fallback event " + input);
        return null;
    }

    /**
     * Handles the default fall back and run events for DMS service by checking the infrastructure (health check)
     * availability for LOS
     *
     * @param requestData
     * @return dmsResponse
     */
    public X process(final Y requestData) {

        Z dmsResponse = null;
        if (!shouldFallback())
        {
            final long startTime = Calendar.getInstance().getTimeInMillis();
            try {
                dmsResponse = executeEvent(requestData);
                LOG.info("Time-DmsService (" + urlPath + ") :" + (Calendar.getInstance().getTimeInMillis() - startTime)
                        + "ms");
            } catch (com.hybris.charon.exp.HttpException rxexc) {
                return processObservableException(rxexc);
            }
        } else {
            LOG.debug("calling fallback event");
            dmsResponse = executeFallbackEvent(requestData);
        }

        if (dmsResponse == null) {
            return createDefaultResult();
        }
        //convert REST model object to the outgoing data object
        return extractOutput(dmsResponse);

    }

    /**
     * Process return code from Observable exception observable
     * @param rxexc
     * @return
     */
    protected X processObservableException(HttpException rxexc)  {

        X result = createDefaultResult();
        //Define block to Observe and parse server error message
        rxexc.getServerMessage().toBlocking().forEach(new Action1<String>() {
            @Override
            public void call(String serverMessage) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map parseServerMessageMap = null;
                try {
                    //Jackson map error json
                    parseServerMessageMap = objectMapper.readValue(serverMessage, HashMap.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Map and parse error json
                if (parseServerMessageMap != null && parseServerMessageMap.containsKey("errorMessage")) {
                    Map error = (Map) parseServerMessageMap.get("errorMessage");
                    ((CommonResponseFieldsData) result).setReturnMessage(error.get("message")+"");
                    if (error.get("code") != null ) {
                        ((CommonResponseFieldsData) result).setReturnCd((Integer)error.get("code"));
                    }

                }
            }
        });

        LOG.error("Time-DmsService (" + urlPath + ") :" + " exception " +rxexc.getMessage());
        return result;
    }

    /**
     * Check the availability status of the DMS system.
     *
     * @return ACTIVE -> true, DISABLED / INACTIVE -> false
     */
    protected boolean shouldFallback() {

        final AmwayInfraAvailabilityModel infraAvailForCode = amwayInfraAvailabilityService.getInfraAvailForCode(serviceAvailCode);
        if (infraAvailForCode != null && AmwayInfraAvlStatus.ACTIVE.equals(infraAvailForCode.getStatus())) {
            return false;
        }
        LOG.error("Fallabck for the service :" + this.getClass() + " because the current infrastructure availablity for :"
                + infraAvailForCode + " is " + (infraAvailForCode != null ? infraAvailForCode.getStatus() : " null "));
        return true;
    }

    protected X extractOutput(final Object result) {
        return (X) getOutputConverter().convert(result, createResultObject());
    }

    protected Map<String, String> buildClientConfig(String appId, String scope) {
        return charonClientConfigService.buildCharonClientConfig(appId, scope);
    }

    /**
     * Build a charon client config map for a client proxy. Use defaultId if required
     * @param input
     * @return
     */
    protected Map<String, String> buildProxyClientConfig(CommonRequestFieldsData input) {

        String appId = input.getClientApplicationId() != null ? input.getClientApplicationId() : this.defaultProxyAppId;
        String scope = buildClientConfigScope(input);
        return charonClientConfigService.buildCharonClientConfig(appId, scope);
    }

    /**
     * Build a charon client config for cron jobs.  Use defaultID if required
     * @param input
     * @return
     */
    protected Map<String, String> buildCronClientConfig(CommonRequestFieldsData input) {

        String appId = input.getClientApplicationId() != null ? input.getClientApplicationId() : this.defaultCronAppId;
        String scope = buildClientConfigScope(input);
        return charonClientConfigService.buildCharonClientConfig(appId, scope);
    }

    /**
     * Build scope header param required by Magic DMS
     * @param input
     * @return
     */
    protected String buildClientConfigScope(CommonRequestFieldsData input) {

        //really scope should be based on logged in account/party ... JWT is coming to settle this
        String aboNumber = input.getLoggedInAccountId();
        String scope = "aboNum=" + aboNumber + " " + "salesPlanAff=" + input.getSalesPlanAff();

        String partyId = input.getLoggedInPartyId();
        if (partyId != null) {
            scope = scope += " partyId=" + partyId;
        }
        return scope;
    }


    /**
     * @return urlPath
     */
    public String getUrlPath() {
        return urlPath;
    }

    /**
     * @param urlPath the urlPath to set
     */
    public void setUrlPath(final String urlPath) {
        this.urlPath = urlPath;
    }

    /**
     * @return xclientRefId
     */
    public String getXclientRefId() {
        return xclientRefId;
    }

    /**
     * @return inputConverter
     */
    public Converter getInputConverter() {
        return inputConverter;
    }

    /**
     * @param inputConverter the inputConverter to set
     */
    public void setInputConverter(final Converter inputConverter) {
        this.inputConverter = inputConverter;
    }

    /**
     * @return outputConverter
     */
    public Converter getOutputConverter() {
        return outputConverter;
    }

    /**
     * @param outputConverter the outputConverter to set
     */
    public void setOutputConverter(final Converter outputConverter) {
        this.outputConverter = outputConverter;
    }

    /**
     * @return serviceAvailCode
     */
    public String getServiceAvailCode() {
        return serviceAvailCode;
    }

    /**
     * @param serviceAvailCode the serviceAvailCode to set
     */
    public void setServiceAvailCode(final String serviceAvailCode) {
        this.serviceAvailCode = serviceAvailCode;
    }

    /**
     * @return amwayInfraAvailabilityService
     */
    public AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> getAmwayInfraAvailabilityService() {
        return amwayInfraAvailabilityService;
    }

    /**
     * @param amwayInfraAvailabilityService the amwayInfraAvailabilityService set
     */
    public void setAmwayInfraAvailabilityService(
            final AmwayInfraAvailabilityService<AmwayInfraAvailabilityModel> amwayInfraAvailabilityService) {
        this.amwayInfraAvailabilityService = amwayInfraAvailabilityService;
    }

    public CharonClientConfigService getCharonClientConfigService() {
        return charonClientConfigService;
    }

    public void setCharonClientConfigService(CharonClientConfigService charonClientConfigService) {
        this.charonClientConfigService = charonClientConfigService;
    }

    public String getDefaultCronAppId() {
        return defaultCronAppId;
    }

    public void setDefaultCronAppId(String defaultCronAppId) {
        this.defaultCronAppId = defaultCronAppId;
    }

    public String getDefaultProxyAppId() {
        return defaultProxyAppId;
    }

    public void setDefaultProxyAppId(String defaultProxyAppId) {
        this.defaultProxyAppId = defaultProxyAppId;
    }



}