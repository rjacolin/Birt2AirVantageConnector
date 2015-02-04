package com.birt.airvantage;

import java.util.Date;

public class AVTimestampedData {

	Number value;
	Date datetime;
	AVSystem system;
	
	AVTimestampedData(AVSystem s, Number v, Date d)
	{
		value = v;
		datetime = d;
		system = s;
	}
	
	public Number getValue()
	{
		return value;
	}
	
	public Date getDatetime()
	{
		return datetime;
	}
	
	public AVSystem getSystem()
	{
		return system;
	}
}
