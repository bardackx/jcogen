package com.bardackx.sap.jcogen.tests.mockup;

import com.sap.conn.jco.AbapClassException.Mode;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoParameterList;

public class JCoFunctionMockup implements JCoFunction {

	private static final long serialVersionUID = 1L;

	private String name;

	private JCoParameterList tableParameterList;
	private JCoParameterList importParameterList;
	private JCoParameterList exportParameterList;

	public void setName(String name) {
		this.name = name;
	}

	public void setImportParameterList(JCoParameterList importParameterList) {
		this.importParameterList = importParameterList;
	}
	
	public void setExportParameterList(JCoParameterListMockup exportParameterList) {
		this.exportParameterList = exportParameterList;
	}

	@Override
	public JCoFunction clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(JCoDestination arg0) throws JCoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(JCoDestination arg0, String arg1) throws JCoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(JCoDestination arg0, String arg1, String arg2) throws JCoException {
		// TODO Auto-generated method stub

	}

	@Override
	public JCoParameterList getChangingParameterList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbapException getException(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbapException[] getExceptionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoParameterList getExportParameterList() {
		return exportParameterList;
	}

	@Override
	public JCoFunctionTemplate getFunctionTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoParameterList getImportParameterList() {
		return importParameterList;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public JCoParameterList getTableParameterList() {
		return tableParameterList;
	}

	@Override
	public boolean isAbapClassExceptionEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAbapClassExceptionMode(Mode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTableParameterList(JCoParameterList tableParameterList) {
		this.tableParameterList = tableParameterList;
	}



}
