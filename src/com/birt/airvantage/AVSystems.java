package com.birt.airvantage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AVSystems {

	private String content;
	private List<AVSystem> ltAVSystem = new ArrayList<AVSystem>();
	private int index = -1;
	
	public static String PRINTER_COLOR_INK_DATANAME   = "phone.custom.down.2";
	public static String PRINTER_BLACK_INK_DATANAME   = "phone.custom.down.1";
	public static String PRINTER_A4PAGE_DATANAME      = "phone.custom.up.2";
	public static String PRINTER_A6PAGE_DATANAME      = "phone.custom.up.1";
	public static String PRINTER_COLOR_CART_DATANAME  = "phone.custom.str.2";
	public static String PRINTER_BLACK_CART_DATANAME  = "phone.custom.str.1";
	private static String PRINTER_ACTIVEWIFI_DATANAME = "phone.activewifi";
	private static String PRINTER_LATITUDE_DATANAME   = "phone.latitude";
	private static String PRINTER_LONGITUDE_DATANAME  = "phone.longitude";
	private static String PRINTER_ALARM_DATANAME      = "phone.alarm";

	public AVSystems(String content) {
		this.content = content;
	}

	public AVSystem next() {
		index++;
		if (index >= ltAVSystem.size()) return null;
		return ltAVSystem.get(index);
	}

	public boolean parse(AVRequest request) {
		JSONObject result;
		try {
			result = new JSONObject(content);
			JSONArray ltNameSystem = result.getJSONArray("items");

			for (int i=0;i< ltNameSystem.length();i++) {
				JSONObject system = (JSONObject)ltNameSystem.get(i);
				AVSystem d = new AVSystem();
				d.name = system.getString("name");
				d.uid = system.getString("uid");
				if(!system.isNull("gateway"))
					if(!system.getJSONObject("gateway").isNull("serialNumber"))
						d.serial = system.getJSONObject("gateway").getString("serialNumber");
				
				d.state = system.getString("state");
				d.commStatus = system.getString("commStatus");
				
				/* System Details */
				String details = request.getSystemDetail(d.uid);
				JSONObject detailsResult = new JSONObject(details);
				JSONArray labels = detailsResult.getJSONArray("labels");
				for (int l = 0; l< labels.length(); l++) {
					String value = null;
					value = labels.getString(l);

					d.addLabel(value);
				}
				
				if(!detailsResult.isNull("lastCommDate"))
					d.lastComDate = detailsResult.getLong("lastCommDate");
				
				/* Datapoint */
				String lastDatapoint = request.getLastDatapoints(d.uid, PRINTER_ACTIVEWIFI_DATANAME + ","+ PRINTER_LATITUDE_DATANAME +
													"," + PRINTER_LONGITUDE_DATANAME + "," + PRINTER_ALARM_DATANAME);
				
				JSONObject lastDatapointresult = new JSONObject(lastDatapoint);
				
				if(!lastDatapointresult.isNull(PRINTER_ACTIVEWIFI_DATANAME))
						d.activewifi = ((JSONObject)lastDatapointresult.getJSONArray(PRINTER_ACTIVEWIFI_DATANAME).get(0)).getString("value");

				if(!lastDatapointresult.isNull(PRINTER_LONGITUDE_DATANAME))
					d.longitude = ((JSONObject)lastDatapointresult.getJSONArray(PRINTER_LONGITUDE_DATANAME).get(0)).getDouble("value");
				if(!lastDatapointresult.isNull(PRINTER_LATITUDE_DATANAME))
					d.latitude = ((JSONObject)lastDatapointresult.getJSONArray(PRINTER_LATITUDE_DATANAME).get(0)).getDouble("value");
				if(!lastDatapointresult.isNull(PRINTER_ALARM_DATANAME))
					d.alarmOn = ((JSONObject)lastDatapointresult.getJSONArray(PRINTER_ALARM_DATANAME).get(0)).getBoolean("value");
				loadHistoricalData(d, request);
				ltAVSystem.add(d);
			}

			return true;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void loadHistoricalData(AVSystem d, AVRequest request)
	{
		String historicalData = request.getMultiAgregatedDatapoints(PRINTER_BLACK_INK_DATANAME + "," + PRINTER_COLOR_INK_DATANAME, d.uid);
		if(Parameters.DEBUG)
			System.out.println("historical:\n" + historicalData);
		JSONObject result;
		try {
			result = new JSONObject(historicalData);
			JSONArray datapoints = result.getJSONObject(d.uid).getJSONArray(PRINTER_BLACK_INK_DATANAME);

			List<AVTimestampedData> hBlackInkLevel = new ArrayList<AVTimestampedData>();
			for (int i = 0; i< datapoints.length(); i++) {
				Integer value = null;
				if(!datapoints.getJSONObject(i).isNull("v"))
					value = new Integer(datapoints.getJSONObject(i).getInt("v"));
				int timestamp = datapoints.getJSONObject(i).getInt("ts");
				
				AVTimestampedData data = new AVTimestampedData(d, value, new Date(timestamp));
				hBlackInkLevel.add(data);
			}
			d.setHistoricalBlackInkLevel(hBlackInkLevel);
			datapoints = result.getJSONObject(d.uid).getJSONArray(PRINTER_COLOR_INK_DATANAME);

			List<AVTimestampedData> hColorInkLevel = new ArrayList<AVTimestampedData>();
			for (int i = 0; i< datapoints.length(); i++) {
				Integer value = null;
				if(!datapoints.getJSONObject(i).isNull("v"))
					value = new Integer(datapoints.getJSONObject(i).getInt("v"));
				int timestamp = datapoints.getJSONObject(i).getInt("ts");
				
				AVTimestampedData data = new AVTimestampedData(d, value, new Date(timestamp));
				hColorInkLevel.add(data);
			}
			d.setHistoricalColorInkLevel(hColorInkLevel);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public List<AVSystem> getListSystems() {
		return ltAVSystem;
	}
}
