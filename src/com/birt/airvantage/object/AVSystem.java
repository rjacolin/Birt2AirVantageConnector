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

import java.util.ArrayList;
import java.util.List;


public class AVSystem {
	public String name;
	public String uid;
	public String state;
	public String commStatus;
	public String activewifi;
	public boolean alarmOn = false;
	public int inkLevel;
	public double longitude;
	public double latitude;
	public long lastComDate = 0;
	public String serial;
	public List<String> labels = new ArrayList<String>();
	public List<AVTimestampedData> historicalBlackInkLevel = new ArrayList<AVTimestampedData>();
	public List<AVTimestampedData> historicalColorInkLevel;

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
	
	public String getSerial()
	{
		return serial;
	}
	
	public long getLastCom()
	{
		return lastComDate;
	}
	
	public boolean isAlarmOn()
	{
		return alarmOn;
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
	
	public void addLabel(String label)
	{
		labels.add(label);
	}
	
	public String getLabel()
	{
		if(labels.contains("EU"))
			return "EU";
		else if(labels.contains("NA"))
			return "NA";
		return "Others";
	}
	
	public List<AVTimestampedData> getHistoricalBlackInkLevel() {
		return historicalBlackInkLevel;
	}
	
	public List<AVTimestampedData> getHistoricalColorInkLevel() {
		return historicalColorInkLevel;
	}
	
	public void setHistoricalBlackInkLevel(List<AVTimestampedData> list)
	{
		historicalBlackInkLevel = list;
	}
	
	public void setHistoricalColorInkLevel(List<AVTimestampedData> list)
	{
		historicalColorInkLevel = list;
	}
}