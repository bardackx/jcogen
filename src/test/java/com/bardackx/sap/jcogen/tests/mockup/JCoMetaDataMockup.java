package com.bardackx.sap.jcogen.tests.mockup;

import com.sap.conn.jco.JCoExtendedFieldMetaData;
import com.sap.conn.jco.JCoMetaData;
import com.sap.conn.jco.JCoRecordMetaData;

public class JCoMetaDataMockup implements JCoMetaData {

	private static final long serialVersionUID = 1L;

	private String name;
	private FieldMockup[] fields = {};

	@Override
	public int getByteLength(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getByteLength(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public String getClassNameOfField(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public String getClassNameOfField(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getDecimals(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getDecimals(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public String getDescription(int i) {
		return fields[i].getDescription();
	}

	@Override
	public String getDescription(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public JCoExtendedFieldMetaData getExtendedFieldMetaData(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public JCoExtendedFieldMetaData getExtendedFieldMetaData(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getFieldCount() {
		return fields.length;
	}

	@Override
	public int getLength(int i) {
		return fields[i].getLength();
	}

	@Override
	public int getLength(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getName(int i) {
		return fields[i].getName();
	}

	@Override
	public JCoRecordMetaData getRecordMetaData(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public JCoRecordMetaData getRecordMetaData(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public String getRecordTypeName(int i) {
		return fields[i].getRecordTypeName();
	}

	@Override
	public String getRecordTypeName(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getType(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getType(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public String getTypeAsString(int i) {
		return fields[i].getTypeAsString();
	}

	@Override
	public String getTypeAsString(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getUnicodeByteLength(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int getUnicodeByteLength(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean hasField(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public int indexOf(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isAbapObject(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isAbapObject(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isLocked() {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isNestedType1Structure(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isNestedType1Structure(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isStructure(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isStructure(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isTable(int arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public boolean isTable(String arg0) {
		throw new RuntimeException("TODO");

	}

	@Override
	public void lock() {
		throw new RuntimeException("TODO");

	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public FieldMockup[] getFields() {
		return fields;
	}

	public void setFields(FieldMockup[] fields) {
		this.fields = fields;
	}

}
