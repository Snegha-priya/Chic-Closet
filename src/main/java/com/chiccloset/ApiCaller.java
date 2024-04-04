package com.chiccloset;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.chiccloset.config.DatasourceConfigUtil;
import com.chiccloset.exception.VaruvaaiException;

@Component
public class ApiCaller  {


	@Autowired
	DatasourceConfigUtil datasourceConfigUtil;

	private final Logger logger = LoggerFactory.getLogger(ApiCaller.class);

	public String invokeGetEndpoint(int hour, String fileName, String tenant) {
		String finalUrl = UriComponentsBuilder.fromUriString(datasourceConfigUtil.getAwsbaseurl() + "fileurlget")
				.queryParam("hour", hour).queryParam("fileName", fileName).build().toUriString();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().header("X-TenantID", tenant).uri(URI.create((finalUrl))).GET()
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			throw new VaruvaaiException("Unable to fetch Image :: " + fileName);
		}
	}

	public String invokeSaveEndpoint(String fileName, byte[] file, String tenant) {

		JSONObject requestJson = new JSONObject();
		requestJson.put("fileName", fileName);
		requestJson.put("file", file);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/json")
				.header("X-TenantID", tenant).uri(URI.create(datasourceConfigUtil.getAwsbaseurl() + "filesave"))
				.POST(HttpRequest.BodyPublishers.ofString(requestJson.toString())).build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			throw new VaruvaaiException("Save Failed");
		}
	}

	public String invokeRemoveEndpoint(String fileName, String tenant) {
        try {
            String finalUrl = UriComponentsBuilder
                .fromUriString(datasourceConfigUtil.getAwsbaseurl() + "fileurlget")
                .build().toUriString();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .header("X-TenantID", tenant)
                .uri(URI.create(finalUrl))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new VaruvaaiException("Unable to delete Image :: " + fileName);
        }
    }
	public String invokeTrashEndpoint(String fileName) {
		try {
			/*
			 * String finalUrl =
			 * UriComponentsBuilder.fromUriString(url).queryParam("fileName",
			 * fileName).build() .toUriString();
			 * 
			 * HttpClient client = HttpClient.newHttpClient(); HttpRequest request =
			 * HttpRequest.newBuilder().uri(new URI(finalUrl))
			 * .POST(HttpRequest.BodyPublishers.noBody()).build();
			 * 
			 * HttpResponse<String> response = client.send(request,
			 * HttpResponse.BodyHandlers.ofString());
			 * 
			 * if (response.statusCode() == 200) { return response.body(); }
			 */
		} catch (Exception e) {
			logger.error("Error in invokeTrashEndpoint: " + e.getMessage(), e);
		}
		return "Success";
	}
}