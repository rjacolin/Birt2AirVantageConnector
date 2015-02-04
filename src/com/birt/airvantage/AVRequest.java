package com.birt.airvantage;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class AVRequest {

	private Parameters params = null;
	private String token = null;

	public AVRequest(Parameters params) {
		this.params  = params;
	}

	public boolean connect() {
		String urlConnect = params.getApiUrl()
				+ "/oauth/token?grant_type=password&username=" + params.getLogin()
				+ "&password=" + params.getPassword() + "&client_id=" + params.getClientId()
				+ "&client_secret=" + params.getClientSecret();

		String content = getRequest(urlConnect);
		if (content==null) return false;
		
		try {
			JSONObject result = new JSONObject(content);
			token  = result.getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}

		if (token==null) return false;
		if(Parameters.DEBUG)
			System.out.println("Token = " + token);
		return true;
	}
	
	public boolean disconnect() {
		return true;
	}
	
	public String getSystems() {
		String url = params.getApiUrl() + "/v1/systems?&access_token=" + token;
		if(Parameters.DEBUG)
			System.out.println("URL system : " + url);
		return getRequest(url);
	}
	
	public String getSystemDetail(String uid) {
		String url = params.getApiUrl() + "/v1/systems/" + uid + "?access_token=" + token;
		if(Parameters.DEBUG)
			System.out.println("URL system detail: " + url);
		return getRequest(url);
	}
	
	public String getLastDataPoints(String uid) {
		String url = params.getApiUrl() + "/v1/systems/" + uid + "/data?access_token=" + token;
		if(Parameters.DEBUG)
			System.out.println("URL Last Data Points: " + url);
		return getRequest(url);
	}

	public String getMultiAgregatedDatapoints(String dataIds, String systemIds) {
		String url = params.getApiUrl() + "/v1/systems/data/aggregated?dataIds=" + dataIds +
				"&interval=1day&targetIds="+ systemIds + 
				"&access_token=" + token;
		if(Parameters.DEBUG)
			System.out.println("URL multiAggregatedDatapoints: " + url);
		return getRequest(url);
	}
	
	public String getFleetAgregatedDatapoints(String dataIds, String systemIds) {
		String url = params.getApiUrl() + "/v1/systems/data/fleet?dataIds=" + dataIds +
				"&interval=1day&targetIds="+ systemIds + 
				"&access_token=" + token;
		if(Parameters.DEBUG)
			System.out.println("URL FleetAggregatedDatapoints: " + url);
		return getRequest(url);
	}
	
	public String getLastDatapoints(String systemId, String dataIds) {
		String url = params.getApiUrl() + "/v1/systems/" + systemId + 
				"/data?ids=" + dataIds + "&access_token=" + token;
		if(Parameters.DEBUG)
			System.out.println("URL lastDdatapoints: " + url);
		return getRequest(url);
	}
	
//	public String getSystem(String uid) {
//		String url = params.getApiUrl() + "/v1/systems/" + uid + "?access_token=" + token;
//		return getRequest(url);
//	}

	private String getRequest(String url) {
		try {
			Response ex = Request.Get(url).execute();
			return ex.handleResponse(new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws IOException {
					StatusLine statusLine = response.getStatusLine();
					HttpEntity entity = response.getEntity();
					if (statusLine.getStatusCode() >= 300) {
						throw new HttpResponseException(statusLine
								.getStatusCode(), statusLine
								.getReasonPhrase());
					}
					if (entity == null) throw new ClientProtocolException("Pas de données");
					return IOUtils.toString(entity.getContent());
				}
			});						
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getCurrentUser()
	{
		String url = params.getApiUrl() + "/v1/users/current?" +
				"access_token=" + token;
		if(Parameters.DEBUG)
			System.out.println("URL getUser : \n" + url);
		return getRequest(url);
	}
}
