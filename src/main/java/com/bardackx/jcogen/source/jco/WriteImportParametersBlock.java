package com.bardackx.jcogen.source.jco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bardackx.jcogen.FieldMapping;
import com.bardackx.jcogen.source.CodeBlock;

public class WriteImportParametersBlock implements CodeBlock {

	private List<FieldMapping> fieldMappings = new ArrayList<>();

	@Override
	public String render(String lineEnding, String tab) {

		StringBuilder b = new StringBuilder();
		b.append("JCoParameterList ipl = function.getImportParameterList();").append(lineEnding);
		Iterator<FieldMapping> i = fieldMappings.iterator();
		while (i.hasNext()) {
			FieldMapping f = i.next();
			
			if (f.getStructureDefinition() == null) {
				
				b.append("ipl.setValue(\"");
				b.append(f.getSapName());
				b.append("\", ");
				b.append(f.getMappingName());
				b.append(");");
				
			} else {

				b.append("{");
				b.append(lineEnding);

				b.append(tab);
				b.append("JCoStructure s = ipl.getStructure(\"");
				b.append(f.getMappingName());
				b.append("\");");
				b.append(lineEnding);

				for (FieldMapping m : f.getStructureDefinition().getFieldMappings()) {
					b.append(tab);
					b.append("s.setValue(\"");
					b.append(m.getSapName());
					b.append("\", ");
					b.append(f.getMappingName());
					b.append(".");
					b.append(m.getGetterMethodName());
					b.append("());");
					b.append(lineEnding);
				}

				b.append("}");
				
			}
			
			if (i.hasNext()) b.append(lineEnding);
		}
		return b.toString();
	}

	public void addFieldMapping(FieldMapping e) {
		this.fieldMappings.add(e);
	}

	public List<FieldMapping> getFieldMappings() {
		return fieldMappings;
	}

	public void setFieldMappings(List<FieldMapping> fieldMappings) {
		this.fieldMappings = fieldMappings;
	}

	@Override
	public Collection<String> getRequiredImports() {
		Collection<String> list = new ArrayList<String>();
		list.add("com.sap.conn.jco.JCoParameterList");
		return list;
	}

}
