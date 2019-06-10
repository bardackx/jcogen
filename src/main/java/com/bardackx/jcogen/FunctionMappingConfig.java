package com.bardackx.jcogen;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jcofun")
@XmlAccessorType(XmlAccessType.FIELD)
public class FunctionMappingConfig {

	@XmlAttribute(name = "name")
	private String functionName;

	@XmlAttribute(name = "ignore")
	private boolean ignore;

	@XmlElement(name = "import")
	private Set<String> importTables = new HashSet<>();

	@XmlElement(name = "export")
	private Set<String> exportTables = new HashSet<>();

	public void addImportTable(String table) {
		importTables.add(table);
	}

	public void addExportTable(String table) {
		exportTables.add(table);
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Set<String> getImportTables() {
		return importTables;
	}

	public void setImportTables(Set<String> importTables) {
		this.importTables = importTables;
	}

	public Set<String> getExportTables() {
		return exportTables;
	}

	public void setExportTables(Set<String> exportTables) {
		this.exportTables = exportTables;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

}
