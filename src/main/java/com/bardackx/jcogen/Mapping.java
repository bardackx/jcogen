package com.bardackx.jcogen;

import java.util.ArrayList;
import java.util.List;

public class Mapping {

	private String className;
	private List<FieldMapping> fieldMappings = new ArrayList<>();
	private String sapRecordTypeName;
	
	public void addFieldMapping(FieldMapping e) {
		this.fieldMappings.add(e);
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<FieldMapping> getFieldMappings() {
		return fieldMappings;
	}

	public void setFieldMappings(List<FieldMapping> fieldMappings) {
		this.fieldMappings = fieldMappings;
	}

	public String getSapRecordTypeName() {
		return sapRecordTypeName;
	}

	public void setSapRecordTypeName(String sapRecordTypeName) {
		this.sapRecordTypeName = sapRecordTypeName;
	}

}
