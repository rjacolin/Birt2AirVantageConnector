package com.birt.airvantage;

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
	public HistoricalData historicalBlackInkLevel = new HistoricalData();
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
	
	public HistoricalData getHistoricalBlackInkLevel() {
		return historicalBlackInkLevel;
	}
	
	public List<AVTimestampedData> getHistoricalColorInkLevel() {
		return historicalColorInkLevel;
	}
	
	public void setHistoricalBlackInkLevel(List<AVTimestampedData> list)
	{
		historicalBlackInkLevel.setHistory(list);
	}
	
	public void setHistoricalColorInkLevel(List<AVTimestampedData> list)
	{
		historicalColorInkLevel = list;
	}
}