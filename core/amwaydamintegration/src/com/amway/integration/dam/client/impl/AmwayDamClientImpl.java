package com.amway.integration.dam.client.impl;

import static com.amway.integration.dam.constants.AmwayDamConstants.BASIC_AUTH_HEADER;
import static com.amway.integration.dam.constants.AmwayDamConstants.TAGS_SEPARATOR;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.amway.integration.dam.client.AmwayDamClient;
import com.amway.core.annotations.AmwayBean;

@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamClientImpl implements AmwayDamClient
{
	@Autowired
	private RestTemplate amwayDamRestTemplate;

	@Value("${amway.dam.client.host}")
	private String host;
	@Value("${amway.dam.client.username}")
	private String username;
	@Value("${amway.dam.client.password}")
	private String password;

	@Override
	public Map<String, Object> executeRestRequest(final String relativePath, final String urlPath)
	{
		URI targetUrl = URI.create(host + relativePath + urlPath);
		//@formatter:off
		RequestEntity<Void> request = RequestEntity.get(targetUrl)
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, createAuthHeader())
				.build();
		//@formatter:on
		ResponseEntity<HashMap<String, Object>> response = amwayDamRestTemplate.exchange(request, new JsonAsHashMapResponseType());
		return response.getBody();
	}

	@Override
	public InputStream executeBinaryRequest(String relativePath, String urlPath, String renditionId)
	{
		URI targetUrl = URI.create(host + relativePath + urlPath + renditionId);
		//@formatter:off
		RequestEntity<Void> request = RequestEntity.get(targetUrl)
				.header(HttpHeaders.AUTHORIZATION, createAuthHeader())
				.build();
		//@formatter:on
		ResponseEntity<byte[]> response = amwayDamRestTemplate.exchange(request, byte[].class);
		return new BufferedInputStream(new ByteArrayInputStream(response.getBody()));
	}

	private static class JsonAsHashMapResponseType extends ParameterizedTypeReference<HashMap<String, Object>>
	{
		private JsonAsHashMapResponseType()
		{
		}
	}

	private String createAuthHeader()
	{
		final String auth = username + TAGS_SEPARATOR + password;
		return BASIC_AUTH_HEADER + new String(Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8)));
	}
}
