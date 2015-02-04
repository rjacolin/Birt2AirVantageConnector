package com.birt.airvantage;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class UserConnector {
	private AVRequest request = null;
	private User currentUser = null;
	private int index = 0;
	
	public void setUser(User u)
	{
		currentUser = u;
	}
	
	public User getUser() {
		return currentUser;
	}
	
	public User next() {
		if(index >= 1) return null;
		index++;
		return currentUser;
	}
	
	public void open(Object appContext, Map<String, Object> dataSetParamValues) {
		Parameters params = new Parameters(dataSetParamValues);
		
		request = new AVRequest(params);
		request.connect();

		loadUser(request);
		index = 0;
	}
	
	public void loadUser(AVRequest request)
	{
		String userData = request.getCurrentUser();
		if(Parameters.DEBUG)
			System.out.println("User Info:\n" + userData);
		JSONObject result;
		currentUser = new User();
		try {
			result = new JSONObject(userData);
			currentUser.name = result.getString("name");
			currentUser.company = result.getJSONObject("company").getString("name");
			currentUser.companyUid = result.getJSONObject("company").getString("uid");
			
			currentUser.profile = result.getJSONObject("profile").getString("name");
			currentUser.email = result.getString("email");
			if(!result.isNull("picture"))
				currentUser.icon = result.getJSONObject("picture").getString("normal");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		request.disconnect();
	}
}
