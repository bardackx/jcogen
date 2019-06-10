package com.bardackx.jcogen.source.jco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bardackx.jcogen.FieldMapping;
import com.bardackx.jcogen.Mapping;
import com.bardackx.jcogen.source.CodeBlock;
import com.bardackx.jcogen.source.StringUtil;

public class ReadTableRowsBlock implements CodeBlock {

	private String outputListName;
	private String temporalJcoTableName;
	private String tableNameFromParameterList;
	private Mapping mapping;

	public String render(String lineEnding, String tab) {
		StringBuilder b = new StringBuilder();

		// list initialization
		b.append("List<");
		b.append(mapping.getClassName());
		b.append("> ");
		b.append(outputListName);
		b.append(" = new ArrayList<");
		b.append(mapping.getClassName());
		b.append(">();");
		b.append(lineEnding);

		// table initialization
		b.append("JCoTable ");
		b.append(temporalJcoTableName);
		b.append(" = function.getTableParameterList().getTable(\"");
		b.append(tableNameFromParameterList);
		b.append("\");");
		b.append(lineEnding);

		// if not empty do block
		b.append("if (!");
		b.append(temporalJcoTableName);
		b.append(".isEmpty()) do {");
		b.append(lineEnding);

		// pojo initialization
		b.append(tab);
		b.append(mapping.getClassName());
		b.append(" e = new ");
		b.append(mapping.getClassName());
		b.append("();");
		b.append(lineEnding);

		// mapping
		for (FieldMapping f : mapping.getFieldMappings()) {
			b.append(tab);
			b.append("e.").append(f.getSetterMethodName());
			b.append("(");
			b.append(temporalJcoTableName);
			b.append(".get");
			b.append(StringUtil.capitalize(f.getMappingType().getName()));
			b.append("(\"");
			b.append(f.getSapName());
			b.append("\")");
			b.append(");");
			b.append(lineEnding);
		}

		// add pojo to list
		b.append(tab);
		b.append(outputListName);
		b.append(".add(e);");
		b.append(lineEnding);

		// do while condition
		b.append("} while (");
		b.append(temporalJcoTableName);
		b.append(".nextRow());");
		b.append(lineEnding);

		// save into the out object
		b.append("out.");
		b.append(outputListName);
		b.append(" = ");
		b.append(outputListName);
		b.append(";");

		return b.toString();
	}

	@Override
	public Collection<String> getRequiredImports() {
		List<String> list = new ArrayList<String>();
		list.add("com.sap.conn.jco.JCoTable");
		list.add("java.util.ArrayList");
		list.add("java.util.List");
		return list;
	}

	public String getOutputListName() {
		return outputListName;
	}

	public void setOutputListName(String outputListName) {
		this.outputListName = outputListName;
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

}
