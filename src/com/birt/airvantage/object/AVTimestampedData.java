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

import java.util.Date;

/**
 * Contains a single value for a data with the timestamp.
 * 
 * @author rjacolin
 *
 */
public class AVTimestampedData {

	Number value;
	Date datetime;
	AVSystem system;
	
	public AVTimestampedData(AVSystem s, Number v, Date d)
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
