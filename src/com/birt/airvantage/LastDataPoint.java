package com.birt.airvantage;


public class LastDataPoint {
	public int colorInkLevel;
	public int blackInkLevel;
	public int a4Pages;
	public int a6Pages;
	public String colorCartridgeSN;
	public String blackCartridgeSN;
	public String systemUID;


	public String getSystemUID()
	{
		return systemUID;
	}

	public int getColorInkLevel() {
		return colorInkLevel;
	}
	
	public int getBlackInkLevel() {
		return blackInkLevel;
	}
	
	public int getA4Pages() {
		return a4Pages;
	}
	
	public int getA6Pages() {
		return a6Pages;
	}
	
	public String getColorCartridgeSN()
	{
		return colorCartridgeSN;
	}
	
	public String getBlackCartridgeSN()
	{
		return blackCartridgeSN;
	}

}