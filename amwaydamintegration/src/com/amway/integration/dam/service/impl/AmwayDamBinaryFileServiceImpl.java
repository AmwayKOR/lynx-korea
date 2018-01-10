package com.amway.integration.dam.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;

import com.amway.integration.dam.data.AmwayBinaryFileRequest;
import com.amway.integration.dam.service.AmwayAbstractDamService;
import com.amway.integration.dam.service.AmwayDamService;
import com.amway.core.annotations.AmwayBean;

/**
 * Executes request to DAM, retrieves binary data as input stream and delegates it to caller.
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamBinaryFileServiceImpl extends AmwayAbstractDamService<InputStream, AmwayBinaryFileRequest, InputStream>
		implements AmwayDamService<AmwayBinaryFileRequest, InputStream>
{
	@Value("${amway.dam.assetbinary.urlPath}")
	private String urlPath;

	@Override
	protected InputStream extractOutput(InputStream response)
	{
		return response;
	}

	@Override
	protected InputStream executeEvent(AmwayBinaryFileRequest requestData)
	{
		return getAmwayDamClient().executeBinaryRequest(requestData.getPath(), urlPath, requestData.getRenditionId());
	}
}
