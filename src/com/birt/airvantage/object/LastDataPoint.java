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
 * Contains the last value for a defined list of data.
 * 
 * @author rjacolin
 *
 */
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