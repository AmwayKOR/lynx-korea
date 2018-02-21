package com.amway.amwaydms.client;

import javax.ws.rs.*;

import com.amway.amwaydms.model.AccountBalanceRequest;
import com.amway.amwaydms.model.AddressResponse;
import com.amway.amwaydms.model.CommonResponse;

import com.hybris.charon.annotations.Control;
import com.hybris.charon.annotations.Http;
import com.hybris.charon.annotations.OAuth;


import com.amway.amwaydms.model.AccountResponse;
import com.amway.amwaydms.model.AccountBalanceResponse;
import com.amway.amwaydms.model.BlockPrivilegeResponse;
import com.amway.amwaydms.model.BusinessNatureChangeRequest;
import com.amway.amwaydms.model.OrderRequest;
import com.amway.amwaydms.model.SubscriptionRequest;


/**
 */
@Http("dms")
@OAuth
public interface DmsClient
{

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    AccountResponse getAmwayProfile(
                                    @PathParam(value = "affiliateId") final String affiliateId,
                                    @PathParam(value = "accountId") final String accountId,
                                    @QueryParam(value = "detailLevelCd") final String level);
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/user-profile-for-group-order")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    AccountResponse getAmwayGroupProfile(
                                    @PathParam(value = "affiliateId") final String affiliateId,
                                    @PathParam(value = "accountId") final String accountId,
                                    @QueryParam(value = "accntBalType") final String balanceType);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/parties/{partyId}/addresses")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    AddressResponse getAddresses(
                                    @PathParam(value = "affiliateId") final String affiliateId,
                                    @PathParam(value = "accountId") final String accountId,
                                    @PathParam(value = "partyId") final String partyId);

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/renew")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    AccountResponse renew(
                                 @PathParam(value = "affiliateId") final String affiliateId,
                                 @PathParam(value = "accountId") final String accountId,
                                 @QueryParam(value = "renewalDate") final String renewalDate,
                                 @QueryParam(value = "renewalCd") final String renewalCd,
                                 @QueryParam(value = "legalConsentFlg") final String legalConsentFlg,
                                 @QueryParam(value = "invoiceNum") final String invoiceNum,
                                 @QueryParam(value = "renewalWithGroupFlg") final String renewalWithGroupFlg);

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/account-balances")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    CommonResponse createBalance(
                                 @PathParam(value = "affiliateId") final String affiliateId,
                                 @PathParam(value = "accountId") final String accountId,
                                 AccountBalanceRequest accountBalanceRequest);

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/parties/{partyId}/orders")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    CommonResponse notfiyOrder(
                                 @PathParam(value = "affiliateId") final String affiliateId,
                                 @PathParam(value = "accountId") final String accountId,
                                 @PathParam(value = "partyId") final String partyId,
                                 OrderRequest orderRequest);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/account-balances")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    AccountBalanceResponse getAccountBalance(
                                    @PathParam(value = "affiliateId") final String affiliateId,
                                    @PathParam(value = "accountId") final String accountId);

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/block-privileges")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    BlockPrivilegeResponse getBlocksPrivileges(
                                             @PathParam(value = "affiliateId") final String affiliateId,
                                             @PathParam(value = "accountId") final String accountId);

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/business-nature")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    CommonResponse updateBusinessNature(
                                    @PathParam(value = "affiliateId") final String affiliateId,
                                    @PathParam(value = "accountId") final String accountId,
                                        BusinessNatureChangeRequest  businessNatureChangeRequest);
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/accounts/{affiliateId}-{accountId}/parties/{partyId}/subscriptions")
    @Control(retries = "${retries}", retriesInterval = "${retriesInterval}", timeout = "${timeout}")
    CommonResponse updateSubscriptions(
                                        @PathParam(value = "affiliateId") final String affiliateId,
                                        @PathParam(value = "accountId") final String accountId,
                                        @PathParam(value = "partyId") final String partyId,
                                       SubscriptionRequest  SubscriptionRequest);


}
