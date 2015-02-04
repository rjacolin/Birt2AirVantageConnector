package com.birt.airvantage;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AirVantage {
	private AVRequest request = null;
	private AVSystems avsys = null;
	
	/////////////////////////////////////////////:
	// Compatibilité BIRT
	
	Iterator<AVSystem> iterator;

	public void open(Object appContext, Map<String, Object> dataSetParamValues) {
		Parameters params = new Parameters(dataSetParamValues);
		request = new AVRequest(params);
		request.connect();
		String content = request.getSystems();
		avsys  = new AVSystems(content);
		avsys.parse(request);
		List<AVSystem> system = avsys.getListSystems();
		iterator = system.iterator();
	}

	public Object next() {
		if (iterator.hasNext()) return iterator.next();
		return null;
	}

	public void close() {
		request.disconnect();
		iterator = null;
	}
}
