package com.bardackx.sap.jcogen.tests.mockup;

import java.util.Properties;

import com.sap.conn.jco.JCoAttributes;
import com.sap.conn.jco.JCoCustomDestination;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunctionUnitState;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoRuntimeException;
import com.sap.conn.jco.JCoThroughput;
import com.sap.conn.jco.JCoUnitIdentifier;
import com.sap.conn.jco.monitor.JCoDestinationMonitor;

public class JCoDestinationMockup implements JCoDestination {

	private JCoRepository repository;
	
	@Override
	public void changePassword(String arg0, String arg1) throws JCoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmFunctionUnit(JCoUnitIdentifier arg0) throws JCoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmTID(String arg0) throws JCoException {
		// TODO Auto-generated method stub

	}

	@Override
	public JCoCustomDestination createCustomDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createTID() throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAliasUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getApplicationServerHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoAttributes getAttributes() throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDestinationID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDestinationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getExpirationCheckPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getExpirationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getExternalIDData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExternalIDType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoFunctionUnitState getFunctionUnitState(JCoUnitIdentifier arg0) throws JCoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGatewayHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGatewayService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogonCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogonGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getMaxGetClientTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMessageServerHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessageServerService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoDestinationMonitor getMonitor() throws JCoRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPeakLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPoolCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getR3Name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoRepository getRepository() throws JCoException {
		return repository;
	}

	@Override
	public JCoDestinationMonitor getRepositoryDestinationMonitor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepositoryUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSAPRouterString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSncLibrary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSncMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSncMyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSncPartnerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSncQOP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSncSSO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSystemNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTPHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTPName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JCoThroughput getThroughput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ping() throws JCoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeThroughput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setThroughput(JCoThroughput arg0) {
		// TODO Auto-generated method stub

	}

	public void setRepository(JCoRepository repository) {
		this.repository = repository;
	}

}
