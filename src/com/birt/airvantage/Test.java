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

import java.util.HashMap;
import java.util.Map;

import com.birt.airvantage.object.AVSystem;
import com.birt.airvantage.object.AVTimestampedData;
import com.birt.airvantage.object.User;

/**
 * This class illustrates how to use the connectors and AirVantage REST API.
 * 
 * Use each connector in the data source:
 *  - properties > POJO Data class name filled by the connector class name (eg. SystemConnector)
 *  - Column Mapping > POJO Class Name filled by the matching object (eg. AVSystem)
 * 
 * @author rjacolin
 *
 */
public class Test {

	public static void main(String[] args) {
		
		Map<String, Object> dataSetParamValues = new HashMap<String, Object>();
		dataSetParamValues.put("login", "rja@sierrawireless.com");
		dataSetParamValues.put("password", "test1234!");
		dataSetParamValues.put("clientSecret", "fe86cdee28b94822b478f110305ba0ce");
		dataSetParamValues.put("clientId", "51044a27ece44abbaa97170e78e0c92f");
		
		/***************************************/
		/* Get the current user                */
		/***************************************/
		UserConnector uc = new UserConnector();
		uc.open(null, dataSetParamValues);
		
		User user = uc.next();
		System.out.println("User: " + user.getName() + "(" + user.getEmail() + ") - " + user.getProfile() + "\n");
		System.out.println("Company: " + user.getCompany() + "\n");
		System.out.println("icon: " + user.getIcon() + "\n");
		
		/***************************************/
		/* Get the system fleet                */
		/***************************************/
		SystemConnector a = new SystemConnector();
		a.open(null, dataSetParamValues);
		
		AVSystem s = (AVSystem) a.next();
		System.out.println("Name          Uid                              State  CommStatus  Wifi State  Alarm    LastComDate  Labels");
		while (s != null) {
			System.out.println(s.getName() + ", " + s.getUid() + ", " + s.getState() + ", " + s.getCommStatus() + ", " + s.getActiveWifi() + ", " + 
								s.isAlarmOn() + ", " + s.getLastCom() + ", " + s.getLabel());
			/*****************************************/
			/* Get the lastdatapoint for each system */
			/*****************************************/
			LastDataPointConnector ldtconnector = new LastDataPointConnector();
			dataSetParamValues.put("system", s.uid);
			ldtconnector.open(null, dataSetParamValues);
			System.out.println(ldtconnector.getLastDataPoint().getColorInkLevel() + ", " + ldtconnector.getLastDataPoint().getBlackInkLevel() + ", " + 
						ldtconnector.getLastDataPoint().getA4Pages() + ", " + ldtconnector.getLastDataPoint().getA6Pages() + ", " + ldtconnector.getLastDataPoint().getColorCartridgeSN() + ", " + 
						ldtconnector.getLastDataPoint().getBlackCartridgeSN());
			s = (AVSystem) a.next();			
		}

		a.close();
		
		/****************************************/
		/* Get the historical Data for a system */
		/****************************************/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login", "rja@sierrawireless.com");
		map.put("password", "test1234!");
		map.put("clientSecret", "fe86cdee28b94822b478f110305ba0ce");
		map.put("clientId", "51044a27ece44abbaa97170e78e0c92f");
		
		HistoricalDataConnector h = new HistoricalDataConnector();
		map.put("system", "2efd9d71e441456fa45e36f269a37a5a");
		map.put("dataId", "phone.custom.down.1");
		h.open(null, map);
		
		AVTimestampedData t = (AVTimestampedData) h.next();
		System.out.println("Name                             Value            Datetime");
		while (t != null) {
			System.out.println(t.getSystem().getUid() + ", " + t.getValue() + ", " + t.getDatetime());
			t = (AVTimestampedData) h.next();
		}

		h.close();
		
		/************************************************************/
		/* Get the historical data for 2 systems (or for the fleet) */
		/************************************************************/
		FleetHistoricalDataConnector fh = new FleetHistoricalDataConnector();
		map.put("systems", "2efd9d71e441456fa45e36f269a37a5a,0bccdbea4eb84b579e72e8b720381bb6");
		map.put("dataId", "phone.custom.down.1");
		fh.open(null, map);
		
		AVTimestampedData ft = (AVTimestampedData) fh.next();
		System.out.println("Name                             Value            Datetime");
		while (ft != null) {
			System.out.println(ft.getSystem().getUid() + ", " + ft.getValue() + ", " + ft.getDatetime());
			ft = (AVTimestampedData) fh.next();
		}

		fh.close();
	}
}
