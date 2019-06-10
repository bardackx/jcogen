package com.bardackx.jcogen;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class PropertyBasedDestinationProvider implements DestinationDataProvider {

	private final Map<String, Properties> destinationData = new HashMap<>();

	public void registerDestinationData(String name, Properties properties) {
		this.destinationData.put(name, properties);
	}
	
	public void unregisterDestinationData(String name) {
		this.destinationData.remove(name);
	}

	public boolean supportsEvents() {
		return false;
	}

	public void setDestinationDataEventListener(DestinationDataEventListener arg0) {
	}

	public Properties getDestinationProperties(String name) throws DataProviderException {
		return destinationData.containsKey(name) ? destinationData.get(name) : null;
	}

}