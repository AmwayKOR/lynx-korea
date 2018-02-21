package com.amway.integration.dam.controllers.stub;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stub-dam")
public class AmwayStubDamController
{
	private static final String RENDITION_ID = "rendition_id";

	@Value("classpath:/amwaydamintegration/test/test-asset-info.json")
	private Resource mockAssetInfoResponse;

	@Value("classpath:/amwaydamintegration/test/test-asset-renditions.json")
	private Resource mockAssetRenditionsResponse;

	@Value("classpath:/amwaydamintegration/test/test-image.png")
	private Resource mockBinaryFileResponse;

	@RequestMapping(value = "/path/to/asset/_jcr_content/metadata.2.json",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAssetInfo() throws IOException
	{
		return ResponseEntity.ok(FileUtils.readFileToString(mockAssetInfoResponse.getFile(), CharEncoding.UTF_8));
	}

	@RequestMapping(value = "/path/to/asset/_jcr_content/renditions.2.json",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAssetRenditions() throws IOException
	{
		return ResponseEntity.ok(FileUtils.readFileToString(mockAssetRenditionsResponse.getFile(), CharEncoding.UTF_8));
	}

	@RequestMapping(value = "/path/to/asset/_jcr_content/renditions/{" + RENDITION_ID + "}",
			method = RequestMethod.GET)
	public void getBinaryFile(HttpServletResponse response) throws IOException
	{
		InputStream is = new FileInputStream(mockBinaryFileResponse.getFile());
		long length = IOUtils.copyLarge(is, response.getOutputStream());
		response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE);
		response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(length));
		response.flushBuffer();
		is.close();
	}
}
