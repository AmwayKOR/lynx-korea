package com.amway.amwayordermanagementwebservices.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Search criteria used by {@link AmwayOrderSearchService}
 */
public class OrderSearchCriteria {

    public final static String DEFAULT_CURRENT_PAGE = "0";
    public final static String DEFAULT_PAGE_SIZE = "10";
    public final static String DEFAULT_SORT = "asc";

    public final static String ABO_NUM = "account";
    public final static String ORDER_NUM = "code";
    public final static String START_DATE = "startDate";
    public final static String END_DATE = "endDate";
    public final static String ORDER_STATUS = "status";
    public final static String PRODUCT_CODE = "productCode";
    public final static String COUNTRY = "country";

    private String account;
    private String code;
    private String startDateString;
    private String endDateString;
    private String status;
    private String productCode;
    private String country;
    private Date startDate;
    private Date endDate;
    private PageableData pageableData;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PageableData getPageableData() {
        return pageableData;
    }

    public void setPageableData(PageableData pageableData) {
        this.pageableData = pageableData;
    }
}
