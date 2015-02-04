package com.birt.airvantage;

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