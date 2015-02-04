package com.birt.airvantage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HistoricalData {
//	private static String PRINTER_COLOR_INK_DATANAME  = "phone.custom.down.2";
//	private static String PRINTER_BLACK_INK_DATANAME  = "phone.custom.down.1";
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

		AVSystem system = new AVSystem();
		system.uid = (String) dataSetParamValues.get("system");
		dataId = (String) dataSetParamValues.get("dataId");
		loadHistoricalData(system, request, dataId);
		iterator = history.iterator();
	}
	
	public void loadHistoricalData(AVSystem d, AVRequest request, String dataId)
	{
		String historicalData = request.getMultiAgregatedDatapoints(dataId, d.uid);
		if(Parameters.DEBUG)
			System.out.println("historical:\n" + historicalData);
		JSONObject result;
		try {
			result = new JSONObject(historicalData);
			JSONArray datapoints = result.getJSONObject(d.uid).getJSONArray(dataId);

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
