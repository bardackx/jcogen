package com.bardackx.jcogen.source.jco;

import java.util.ArrayList;
import java.util.Collection;

import com.bardackx.jcogen.FieldMapping;
import com.bardackx.jcogen.Mapping;
import com.bardackx.jcogen.source.CodeBlock;

public class WriteTableRowsBlock implements CodeBlock {

	private String inputListName;
	private String temporalJcoTableName;
	private String tableNameFromParameterList;
	private Mapping mapping;

	public String render(String lineEnding, String tab) {
		StringBuilder b = new StringBuilder();

		// table initialization
		b.append("JCoTable ");
		b.append(temporalJcoTableName);
		b.append(" = function.getTableParameterList().getTable(\"");
		b.append(tableNameFromParameterList);
		b.append("\");");
		b.append(lineEnding);

		// foreach block
		b.append("for (");
		b.append(mapping.getClassName());
		b.append(" e : ");
		b.append(inputListName);
		b.append(") {");
		b.append(lineEnding);
		
		// add row to jco table
		b.append(tab);
		b.append(temporalJcoTableName);
		b.append(".appendRow();");
		b.append(lineEnding);
		
		// mapping
		for (FieldMapping f : mapping.getFieldMappings()) {
			b.append(tab);
			b.append(temporalJcoTableName);
			b.append(".setValue(\"");
			b.append(f.getSapName());
			b.append("\", e.get");
			b.append(f.getMappingName());
			b.append("());");
			b.append(lineEnding);
		}
		
		b.append("}");

		return b.toString();
	}
	
	@Override
	public Collection<String> getRequiredImports() {
		Collection<String> list = new ArrayList<String>();
		list.add("com.sap.conn.jco.JCoTable");
		return list ;
	}

	public String getTemporalJcoTableName() {
		return temporalJcoTableName;
	}

	public void setTemporalJcoTableName(String temporalJcoTableName) {
		this.temporalJcoTableName = temporalJcoTableName;
	}

	public String getTableNameFromParameterList() {
		return tableNameFromParameterList;
	}

	public void setTableNameFromParameterList(String tableNameFromParameterList) {
		this.tableNameFromParameterList = tableNameFromParameterList;
	}

	public Mapping getMapping() {
		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	public String getInputListName() {
		return inputListName;
	}

	public void setInputListName(String inputListName) {
		this.inputListName = inputListName;
	}

}
