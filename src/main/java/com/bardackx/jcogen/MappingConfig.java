package com.bardackx.jcogen;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mapping")
@XmlAccessorType(XmlAccessType.FIELD)
public class MappingConfig {

	@XmlElement(name = "function")
	private Set<FunctionMappingConfig> functionMappings = new HashSet<>();

	public Set<FunctionMappingConfig> getFunctionMappings() {
		return functionMappings;
	}

	public void setFunctionMappings(Set<FunctionMappingConfig> importTables) {
		this.functionMappings = importTables;
	}
}
