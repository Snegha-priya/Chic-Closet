package com.chiccloset;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.chiccloset.config.MultitenantSchemaManager;
import com.chiccloset.config.TenantContext;
import com.chiccloset.config.TenantIdentifierResolver;
import com.chiccloset.exception.ChicClosetException;
import com.chiccloset.repository.ConfigServiceRepository;


@Component
@EnableAsync
public class ApiCaller {

	@Autowired
	ConfigServiceRepository configServiceRepository;

	@Autowired
	MultitenantSchemaManager multitenantSchemaManager;

	private final Logger logger = LoggerFactory.getLogger(ApiCaller.class);
	private static String activeTenant = "";
	private static String serviceURL = "";

	public String invokeGetEndpoint(int hour, String fileName) throws SQLException {

		setServiceURL("fileurlget");
		String finalUrl = UriComponentsBuilder.fromUriString(serviceURL).queryParam("hour", hour)
				.queryParam("fileName", fileName).build().toUriString();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create((finalUrl))).GET().build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			throw new ChicClosetException("Unable to fetch Image :: " + fileName);
		}
	}

	public String invokeSaveEndpoint(String fileName, byte[] file) {

		setServiceURL("filesave");
		JSONObject requestJson = new JSONObject();
		requestJson.put("fileName", fileName);
		requestJson.put("file", file);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/json")
				.uri(URI.create(serviceURL)).POST(HttpRequest.BodyPublishers.ofString(requestJson.toString())).build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			throw new ChicClosetException("Save Failed");
		}
	}
@Async
	public String invokeRemoveEndpoint(String fileName) {
		setServiceURL("fileremove");
		try {
			String finalUrl = UriComponentsBuilder.fromUriString(serviceURL).queryParam("fileName", fileName).build()
					.toUriString();

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalUrl)).GET().build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			throw new ChicClosetException("Unable to delete Image :: " + fileName);
		}
	}
@Async
	public String invokeTrashEndpoint(String fileName) {
		setServiceURL("filetrash");
		try {
			String finalUrl = UriComponentsBuilder.fromUriString(serviceURL).queryParam("fileName", fileName).build()
					.toUriString();

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(finalUrl))
					.POST(HttpRequest.BodyPublishers.noBody()).build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				return response.body();
			}

		} catch (Exception e) {
			logger.error("Error in invokeTrashEndpoint: " + e.getMessage(), e);
		}
		return "Success";
	}

	private void setServiceURL(String method) {

		activeTenant = TenantContext.getCurrentTenant();
		multitenantSchemaManager.changeSchema(TenantIdentifierResolver.DEFAULT_SCHEMA);
		serviceURL = configServiceRepository.getServiceURL(true, "aws", method);
		multitenantSchemaManager.changeSchema(activeTenant);

	}
}