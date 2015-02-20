/*******************************************************************************
 * Copyright (c) 2015 Sierra Wireless.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    RJA - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package com.birt.airvantage;

import java.util.Map;

public class Parameters {

	private String login = null;
	private String password = null;
	private String clientSecret = null;
	private String clientId = null;
	public static boolean DEBUG = true;

	public Parameters(Map<String, Object> dataSetParamValues) {
		if (dataSetParamValues != null) {
			this.login = (String) dataSetParamValues.get("login");
			this.password = (String) dataSetParamValues.get("password");
			this.clientSecret = (String) dataSetParamValues.get("clientSecret");
			this.clientId = (String) dataSetParamValues.get("clientId");
		}
		System.out.println("\n\n\n\n\n****************************\n\n\n\n\n");
		System.out.println(login);
		System.out.println(password);
		System.out.println(clientSecret);
		System.out.println(clientId);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public String getApiUrl() {
		return "https://eu.airvantage.net/api";
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getClientId() {
		return clientId;
	}
}
