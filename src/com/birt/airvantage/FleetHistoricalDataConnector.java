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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.birt.airvantage.object.AVSystem;
import com.birt.airvantage.object.AVTimestampedData;

public class FleetHistoricalDataConnector {
	private String dataId;
	private AVRequest request = null;
	List<AVTimestampedData> history;
	private int index = -1;
	Iterator<AVTimestampedData> iterator;
	
	public void setHistory(List<AVTimestampedData> list)
	{
		history = list;
	}
	
	public List<AVTimestampedData> getHistoricalData() {
		return history;
	}
	
	public AVTimestampedData next() {
		index++;
		if (index >= history.size()) return null;
		return history.get(index);
	}
	
	public void open(Object appContext, Map<String, Object> dataSetParamValues) {
		Parameters params = new Parameters(dataSetParamValues);
		
		request = new AVRequest(params);
		request.connect();

		AVSystem d = new AVSystem();
		d.uid = (String) dataSetParamValues.get("system");
		dataId = (String) dataSetParamValues.get("dataId");
		
		UserConnector uc = new UserConnector();
		uc.open(appContext, dataSetParamValues);
		String companyUid = uc.getUser().getCompanyUid();

		loadFleetHistoricalData(d, companyUid, request, dataId);
		iterator = history.iterator();
	}
	
	public void loadFleetHistoricalData(AVSystem d, String companyUid, AVRequest request, String dataId)
	{
		String historicalData = request.getFleetAgregatedDatapoints(dataId, companyUid);
		if(Parameters.DEBUG)
			System.out.println("historical:\n" + historicalData);
		JSONObject result;
		try {
			result = new JSONObject(historicalData);
			JSONArray datapoints = result.getJSONObject(companyUid).getJSONArray(dataId);

			history = new ArrayList<AVTimestampedData>();
			for (int i = 0; i< datapoints.length(); i++) {
				Long value = null;
				if(!datapoints.getJSONObject(i).isNull("v"))
					value = new Long(datapoints.getJSONObject(i).getLong("v"));
				long timestamp = datapoints.getJSONObject(i).getLong("ts");
				AVTimestampedData data = new AVTimestampedData(d, value, new Date(timestamp));
				history.add(data);
			}
			//d.setHistoricalBlackInkLevel(dataHistory);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		request.disconnect();
		iterator = null;
	}
}
