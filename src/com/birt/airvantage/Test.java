package com.birt.airvantage;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		UserConnector uc = new UserConnector();
		Map<String, Object> dataSetParamValues = new HashMap<String, Object>();
		dataSetParamValues.put("login", "rja@sierrawireless.com");
		dataSetParamValues.put("password", "test1234!");
		dataSetParamValues.put("clientSecret", "fe86cdee28b94822b478f110305ba0ce");
		dataSetParamValues.put("clientId", "51044a27ece44abbaa97170e78e0c92f");
		uc.open(null, dataSetParamValues);
		
		User user = uc.next();
		System.out.println("User: " + user.getName() + "(" + user.getEmail() + ") - " + user.getProfile() + "\n");
		System.out.println("Company: " + user.getCompany() + "\n");
		System.out.println("icon: " + user.getIcon() + "\n");
		
		AirVantage a = new AirVantage();
		a.open(null, dataSetParamValues);
		
		AVSystem s = (AVSystem) a.next();
		System.out.println("Name          Uid                              State  CommStatus  Wifi State  Alarm    LastComDate  Labels");
		while (s != null) {
			System.out.println(s.getName() + ", " + s.getUid() + ", " + s.getState() + ", " + s.getCommStatus() + ", " + s.getActiveWifi() + ", " + 
								s.isAlarmOn() + ", " + s.getLastCom() + ", " + s.getLabel());
			LastDataPointConnector ldtconnector = new LastDataPointConnector();
			dataSetParamValues.put("system", s.uid);
			ldtconnector.open(null, dataSetParamValues);
			System.out.println(ldtconnector.getLastDataPoint().getColorInkLevel() + ", " + ldtconnector.getLastDataPoint().getBlackInkLevel() + ", " + 
						ldtconnector.getLastDataPoint().getA4Pages() + ", " + ldtconnector.getLastDataPoint().getA6Pages() + ", " + ldtconnector.getLastDataPoint().getColorCartridgeSN() + ", " + 
						ldtconnector.getLastDataPoint().getBlackCartridgeSN());
			s = (AVSystem) a.next();			
		}

		a.close();
		
		HistoricalData h = new HistoricalData();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login", "rja@sierrawireless.com");
		map.put("password", "test1234!");
		map.put("clientSecret", "fe86cdee28b94822b478f110305ba0ce");
		map.put("clientId", "51044a27ece44abbaa97170e78e0c92f");
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
