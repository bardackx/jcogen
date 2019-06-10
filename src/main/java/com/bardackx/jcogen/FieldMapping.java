package com.bardackx.jcogen;

import com.bardackx.jcogen.source.StringUtil;
import com.bardackx.jcogen.source.TypeSource;

public class FieldMapping {

	private String mappingName;
	private TypeSource mappingType;

	private String sapName;

	private String description;

	private String sapRecordTypeName;
	private String sapTypeAsString;
	private int sapLength;
	
	private Mapping structureDefinition;

	public FieldMapping() {
	}

	public FieldMapping(String mappingName, TypeSource mappingType, String sapName) {
		super();
		this.mappingName = mappingName;
		this.mappingType = mappingType;
		this.sapName = sapName;
	}

	public String getSetterMethodName() {
		return "set" + StringUtil.capitalize(mappingName);
	}
	
	public String getGetterMethodName() {
		return "get" + StringUtil.capitalize(mappingName);
	}

	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public TypeSource getMappingType() {
		return mappingType;
	}

	public void setMappingType(TypeSource sapType) {
		this.mappingType = sapType;
	}

	public String getSapName() {
		return sapName;
	}

	public void setSapName(String sapName) {
		this.sapName = sapName;
	}

	public String getSapRecordTypeName() {
		return sapRecordTypeName;
	}

	public void setSapRecordTypeName(String sapRecordTypeName) {
		this.sapRecordTypeName = sapRecordTypeName;
	}

	public String getSapTypeAsString() {
		return sapTypeAsString;
	}

	public void setSapTypeAsString(String sapTypeAsString) {
		this.sapTypeAsString = sapTypeAsString;
	}

	public int getSapLength() {
		return sapLength;
	}

	public void setSapLength(int sapLength) {
		this.sapLength = sapLength;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Mapping getStructureDefinition() {
		return structureDefinition;
	}

	public void setStructureDefinition(Mapping structureDefinition) {
		this.structureDefinition = structureDefinition;
	}

}
