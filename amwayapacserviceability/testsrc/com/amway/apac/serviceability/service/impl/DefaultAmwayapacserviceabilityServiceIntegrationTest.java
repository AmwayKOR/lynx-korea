/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.apac.serviceability.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;

import javax.annotation.Resource;


/**
 * This is an example of how the integration test should look like. {@link ServicelayerBaseTest} bootstraps platform so
 * you have an access to all Spring beans as well as database connection. It also ensures proper cleaning out of items
 * created during the test after it finishes. You can inject any Spring service using {@link Resource} annotation. Keep
 * in mind that by default it assumes that annotated field name matches the Spring Bean ID.
 */
@IntegrationTest
public class DefaultAmwayapacserviceabilityServiceIntegrationTest extends ServicelayerBaseTest
{
	/*
	 * @Resource private AmwayapacserviceabilityService amwayapacserviceabilityService;
	 * 
	 * @Resource private FlexibleSearchService flexibleSearchService;
	 * 
	 * @Before public void setUp() throws Exception { amwayapacserviceabilityService.createLogo(PLATFORM_LOGO_CODE); }
	 * 
	 * @Test public void shouldReturnProperUrlForLogo() throws Exception { // given final String logoCode =
	 * "amwayapacserviceabilityPlatformLogo";
	 * 
	 * // when final String logoUrl = amwayapacserviceabilityService.getHybrisLogoUrl(logoCode);
	 * 
	 * // then assertThat(logoUrl).isNotNull(); assertThat(logoUrl).isEqualTo(findLogoMedia(logoCode).getURL()); }
	 * 
	 * private MediaModel findLogoMedia(final String logoCode) { final FlexibleSearchQuery fQuery = new
	 * FlexibleSearchQuery("SELECT {PK} FROM {Media} WHERE {code}=?code"); fQuery.addQueryParameter("code", logoCode);
	 * 
	 * return flexibleSearchService.searchUnique(fQuery); }
	 * 
	 */}
