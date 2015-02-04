package com.birt.airvantage;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class LastDataPointConnector {
	private AVRequest request = null;
	private LastDataPoint lastDataPoints = null;
	private int index = 0;
	
	public void setUser(LastDataPoint l)
	{
		lastDataPoints = l;
	}
	
	public LastDataPoint getLastDataPoint() {
		return lastDataPoints;
	}
	
	public LastDataPoint next() {
		if(index >= 1) return null;
		index++;
		return lastDataPoints;
	}
	
	public void open(Object appContext, Map<String, Object> dataSetParamValues) {
		Parameters params = new Parameters(dataSetParamValues);
		
		request = new AVRequest(params);
		request.connect();

		loadLastDataPoints(request, dataSetParamValues);
		index = 0;
	}
	
	public void loadLastDataPoints(AVRequest request, Map<String, Object> params)
	{
		String lastDataPointsStr = request.getLastDataPoints((String) params.get("system"));
		if(Parameters.DEBUG)
			System.out.println("LastData Point:\n" + lastDataPointsStr);
		JSONObject result;
		lastDataPoints = new LastDataPoint();
		lastDataPoints.systemUID = (String) params.get("system");
		try {
			result = new JSONObject(lastDataPointsStr);
			if(!result.isNull(AVSystems.PRINTER_COLOR_INK_DATANAME))
				lastDataPoints.colorInkLevel = result.getJSONArray(AVSystems.PRINTER_COLOR_INK_DATANAME).getJSONObject(0).getInt("value");
			if(!result.isNull(AVSystems.PRINTER_BLACK_INK_DATANAME))
				lastDataPoints.blackInkLevel = result.getJSONArray(AVSystems.PRINTER_BLACK_INK_DATANAME).getJSONObject(0).getInt("value");
			if(!result.isNull(AVSystems.PRINTER_A4PAGE_DATANAME))
				lastDataPoints.a4Pages = result.getJSONArray(AVSystems.PRINTER_A4PAGE_DATANAME).getJSONObject(0).getInt("value");
			if(!result.isNull(AVSystems.PRINTER_A6PAGE_DATANAME))
				lastDataPoints.a6Pages = result.getJSONArray(AVSystems.PRINTER_A6PAGE_DATANAME).getJSONObject(0).getInt("value");
			if(!result.isNull(AVSystems.PRINTER_COLOR_CART_DATANAME))
				lastDataPoints.colorCartridgeSN = result.getJSONArray(AVSystems.PRINTER_COLOR_CART_DATANAME).getJSONObject(0).getString("value");
			if(!result.isNull(AVSystems.PRINTER_BLACK_CART_DATANAME))
				lastDataPoints.blackCartridgeSN = result.getJSONArray(AVSystems.PRINTER_BLACK_CART_DATANAME).getJSONObject(0).getString("value");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		request.disconnect();
	}
}
