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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.birt.airvantage.object.AVSystem;

/**
 * This connector loads all the systems with basic information.
 * 
 * @author rjacolin
 *
 */
public class SystemConnector {
	private AVRequest request = null;
	private AVSystems avsys = null;
	
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
