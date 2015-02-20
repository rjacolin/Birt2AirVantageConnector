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
package com.birt.airvantage.object;

/**
 * Contains the information about a user.
 * 
 * @author rjacolin
 *
 */
public class User {
	public String name;
	public String uid;
	public String company;
	public String companyUid;
	public String icon;
	public String email;
	public String profile;


	public String getName() {
		return name;
	}
	
	public String getUid() {
		return uid;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getCompanyUid() {
		return companyUid;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getIcon()
	{
		return icon;
	}
	
	public String getProfile()
	{
		return profile;
	}

}