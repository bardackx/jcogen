package com.bardackx.jcogen.source.jco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bardackx.jcogen.FieldMapping;
import com.bardackx.jcogen.source.CodeBlock;
import com.bardackx.jcogen.source.StringUtil;

public class ReadExportParametersBlock implements CodeBlock {

	private List<FieldMapping> fieldMappings = new ArrayList<>();

	@Override
	public String render(String lineEnding, String tab) {

		StringBuilder b = new StringBuilder();

		b.append("JCoParameterList epl = function.getExportParameterList();").append(lineEnding);
		Iterator<FieldMapping> i = fieldMappings.iterator();
		while (i.hasNext()) {
			FieldMapping f = i.next();

			if (f.getStructureDefinition() == null) {

				b.append("out.");
				b.append(f.getMappingName());
				b.append(" = epl.get");
				b.append(StringUtil.capitalize(f.getMappingType().getName()));
				b.append("(\"");
				b.append(f.getSapName());
				b.append("\");");

			} else {

				b.append("{");
				b.append(lineEnding);

				b.append(tab);
				b.append("JCoStructure s = epl.getStructure(\"");
				b.append(f.getMappingName());
				b.append("\");");
				b.append(lineEnding);

				b.append(tab);
				b.append("out");
				b.append(".");
				b.append(f.getMappingName());
				b.append(" = ");
				b.append("new ");
				b.append(f.getStructureDefinition().getClassName());
				b.append("();");
				b.append(lineEnding);

				for (FieldMapping m : f.getStructureDefinition().getFieldMappings()) {
					b.append(tab);
					b.append("out");
					b.append(".");
					b.append(f.getMappingName());
					b.append(".");
					b.append(m.getSetterMethodName());
					b.append("(s.get");
					b.append(StringUtil.capitalize(m.getMappingType().getName()));
					b.append("(\"");
					b.append(m.getSapName());
					b.append("\"));");
					b.append(lineEnding);
				}

				b.append("}");

			}

			if (i.hasNext()) b.append(lineEnding);
		}

		return b.toString();
	}

	@Override
	public Collection<String> getRequiredImports() {
		Collection<String> list = new ArrayList<String>();
		list.add("com.sap.conn.jco.JCoParameterList");
		for (FieldMapping f : fieldMappings) {
			if (f.getStructureDefinition() != null) {
				list.add("com.sap.conn.jco.JCoStructure");
				break;
			}
		}
		return list;
	}

	public List<FieldMapping> getFieldMappings() {
		return fieldMappings;
	}

	public void setFieldMappings(List<FieldMapping> fieldMappings) {
		this.fieldMappings = fieldMappings;
	}

}
