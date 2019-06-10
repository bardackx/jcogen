package com.bardackx.sap.jcogen.tests.mockup;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.sap.conn.jco.JCoClassMetaData;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoListMetaData;
import com.sap.conn.jco.JCoRecordMetaData;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoRequest;
import com.sap.conn.jco.monitor.JCoRepositoryMonitor;

public class JCoRepositoryMockup implements JCoRepository {

	private Map<String, JCoFunctionMockup> functions = new HashMap<>();
	private Map<String, JCoRecordMetaData> structureDefinitions = new HashMap<>();

	public void addFunction(JCoFunctionMockup function) {
		this.functions.put(function.getName(), function);
	}
	
	public void addStructureDefinitions(JCoRecordMetaData structureDefinition) {
		this.structureDefinitions.put(structureDefinition.getName(), structureDefinition);
	}

	@Override
	public JCoFunction getFunction(String name) throws JCoException {
		return functions.get(name);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getCachedClassMetaDataNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getCachedFunctionTemplateNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getCachedRecordMetaDataNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoClassMetaData getClassMetaData(String arg0) throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoListMetaData getFunctionInterface(String arg0) throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoFunctionTemplate getFunctionTemplate(String arg0) throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoRepositoryMonitor getMonitor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoRecordMetaData getRecordMetaData(String arg0) throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoRequest getRequest(String arg0) throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoRecordMetaData getStructureDefinition(String structureName) throws JCoException {
		return structureDefinitions.get(structureName);
	}

	@Override
	public boolean isUnicode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void load(Reader arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeClassMetaDataFromCache(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFunctionTemplateFromCache(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRecordMetaDataFromCache(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Writer arg0) throws IOException {
		// TODO Auto-generated method stub

	}

}
