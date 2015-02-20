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

public class AVSystemDetails {
	public String name;
	public String uid;
	public String state;
	public String commStatus;
	public String activewifi;
	public int inkLevel;
	public double longitude;
	public double latitude;

	public String getName() {
		return name;
	}
	
	public String getUid() {
		return uid;
	}
	
	public String getState() {
		return state;
	}
	
	public String getCommStatus() {
		return commStatus;
	}
	
	public String getActiveWifi()
	{
		return activewifi;
	}
	
	public int getInkLevel()
	{
		return inkLevel;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
}