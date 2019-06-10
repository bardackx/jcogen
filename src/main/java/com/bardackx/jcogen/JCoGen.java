package com.bardackx.jcogen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bardackx.jcogen.source.AccessModifier;
import com.bardackx.jcogen.source.ClassSource;
import com.bardackx.jcogen.source.DefaultCodeBlock;
import com.bardackx.jcogen.source.JavadocSource;
import com.bardackx.jcogen.source.MethodSource;
import com.bardackx.jcogen.source.StringUtil;
import com.bardackx.jcogen.source.TypeSource;
import com.bardackx.jcogen.source.VariableSource;
import com.bardackx.jcogen.source.jco.ReadExportParametersBlock;
import com.bardackx.jcogen.source.jco.ReadTableRowsBlock;
import com.bardackx.jcogen.source.jco.WriteImportParametersBlock;
import com.bardackx.jcogen.source.jco.WriteTableRowsBlock;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoMetaData;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRecordMetaData;

public class JCoGen {

	private String functionPackageName;
	private String modelPackageName;

	private final Map<String, FunctionMappingConfig> functionMappingConfigurations = new HashMap<>();
	private final Map<String, Mapping> mappings = new HashMap<>();

	private JCoDestination destination;
	private Map<String, ClassSource> classSources = new HashMap<>();

	public JCoGen() {
	}

	public JCoGen(JCoDestination destination) {
		this.destination = destination;
	}

	public void clear() {
		mappings.clear();
		classSources.clear();
		functionMappingConfigurations.clear();
	}

	public ClassSource generateFunctionExecutionClass(String functionName) throws JCoException {
		JCoFunction function = getDestination().getRepository().getFunction(functionName);

		if (function == null) {
			System.err.println("ERROR - There's no JCoFunction by the name \"" + functionName + "\"");
			return null;
		}

		if (function.getTableParameterList() != null) {
			JCoMetaData m = function.getTableParameterList().getMetaData();
			for (int i = 0; i < m.getFieldCount(); i++) {
				String tableName = m.getName(i);
				if (!isExportTable(functionName, tableName) && !isImportTable(functionName, tableName)) {
					System.err.println(
							"WARNIGN - The table parameter \"" + tableName + "\" from the function \"" + functionName
									+ "\" is not being mapped, add it to the corresponding FunctionMappingConfig");
				}
			}
		}

		ClassSource c = new ClassSource();
		c.setPackageName(functionPackageName);
		c.setAccessModifier(AccessModifier.PUBLIC);
		c.setName(function.getName());
		c.addNestedClass(generateOutputClass(function));
		c.addMethod(generateExecuteMethod(function));
		c.addImport("com.sap.conn.jco.JCoException");
		classSources.put(c.getCanonicalName(), c);
		return c;
	}

	public ClassSource generateOutputClass(JCoFunction function) throws JCoException {

		ClassSource c = new ClassSource();
		c.setAccessModifier(AccessModifier.PUBLIC);
		c.setStatic(true);
		c.setName("Output");

		if (function.getExportParameterList() != null) {
			JCoMetaData m = function.getExportParameterList().getMetaData();

			for (int i = 0; i < m.getFieldCount(); i++) {

				JavadocSource d = new JavadocSource();
				d.setComment(m.getDescription(i));

				VariableSource v = new VariableSource();
				v.setAccessModifier(AccessModifier.PUBLIC);
				v.setJavadocSource(d);
				v.setName(m.getName(i));

				String sapTypeAsString = m.getTypeAsString(i);
				if ("STRUCTURE".contentEquals(sapTypeAsString)) {
					Mapping mapping = getMapping(m.getRecordTypeName(i));
					v.setType(new TypeSource(mapping.getClassName(), modelPackageName));
				} else {
					v.setType(inferType(sapTypeAsString));
				}

				c.addField(v);
			}
		}

		if (function.getTableParameterList() != null) {

			JCoMetaData m = function.getTableParameterList().getMetaData();

			for (int i = 0; i < m.getFieldCount(); i++) {

				if (!isExportTable(function.getName(), m.getName(i))) continue;

				JavadocSource d = new JavadocSource();
				d.setComment(m.getDescription(i));

				VariableSource v = new VariableSource();
				v.setAccessModifier(AccessModifier.PUBLIC);

				v.setType(new TypeSource("List", "java.util"));
				v.setTypeGenerics(getMapping(m.getRecordTypeName(i)).getClassName());

				v.setName(m.getName(i));
				v.setJavadocSource(d);
				c.addField(v);
			}
		}

		return c;
	}

	public ClassSource generateTableToObjectMapping(Mapping m) {

		ClassSource c = new ClassSource();
		c.setPackageName(modelPackageName);
		c.setAccessModifier(AccessModifier.PUBLIC);
		c.setName(m.getClassName());

		for (FieldMapping f : m.getFieldMappings()) {

			MethodSource[] ptr = new MethodSource[2];
			c.addProperty(f.getMappingType(), f.getMappingName(), null, ptr);

			String comment = "The record type name is " + f.getSapRecordTypeName() + ", it is a "
					+ f.getSapTypeAsString() + "(" + f.getSapLength() + ")";

			JavadocSource getterJavadoc = new JavadocSource();
			getterJavadoc.setComment(comment);
			getterJavadoc.setReturnComment(f.getDescription());

			JavadocSource setterJavadoc = new JavadocSource();
			setterJavadoc.setComment(comment);
			setterJavadoc.addParameter(f.getMappingName(), f.getDescription());

			ptr[0].setJavadocSource(getterJavadoc);
			ptr[1].setJavadocSource(setterJavadoc);

		}

		this.classSources.put(c.getCanonicalName(), c);

		return c;
	}

	public MethodSource generateExecuteMethod(JCoFunction function) throws JCoException {

		JCoParameterList ipl = function.getImportParameterList();
		JCoParameterList epl = function.getExportParameterList();
		JCoParameterList tpl = function.getTableParameterList();

		JavadocSource javadoc = new JavadocSource();
		javadoc.setComment("This method executes the function \"" + function.getName()
				+ "\" from the given JCoDestination repository and maps the import, export and table paremeters from the given parameters and to the output object accordingly.");
		javadoc.setReturnComment(
				"An object with the function values of the export parameters and tables what were meant to hold export values.");
		javadoc.addThrows("JCoException");

		MethodSource e = new MethodSource();
		e.setJavadocSource(javadoc);
		e.setAccessModifier(AccessModifier.PUBLIC);
		e.setStatic(true);
		e.setName("execute");
		e.setReturnType(new TypeSource("Output"));
		e.addThrowsException("JCoException");
		e.addParameter(new VariableSource("destination", "JCoDestination", "com.sap.conn.jco"));

		// get function from repository
		e.addCodeBlock(new DefaultCodeBlock(
				"JCoFunction function = destination.getRepository().getFunction(\"" + function.getName() + "\");",
				"com.sap.conn.jco.JCoFunction"));

		// write import parameters
		if (ipl != null) {
			WriteImportParametersBlock b = new WriteImportParametersBlock();
			JCoMetaData m = ipl.getMetaData();
			for (int i = 0; i < m.getFieldCount(); i++) {
				FieldMapping f = new FieldMapping();
				f.setDescription("Descripción del campo " + i);
				f.setMappingName(m.getName(i));
				f.setSapLength(m.getLength(i));
				f.setSapName(m.getName(i));
				f.setSapRecordTypeName(m.getRecordTypeName(i));
				f.setSapTypeAsString(m.getTypeAsString(i));

				String typeAsString = m.getTypeAsString(i);
				if ("STRUCTURE".contentEquals(typeAsString)) {
					Mapping mapping = getMapping(m.getRecordTypeName(i));
					f.setMappingType(new TypeSource(mapping.getClassName(), modelPackageName));
					f.setStructureDefinition(mapping);
				} else {
					f.setMappingType(inferType(typeAsString));
				}

				b.addFieldMapping(f);

				VariableSource v = new VariableSource();
				v.setName(f.getMappingName());
				v.setType(f.getMappingType());
				e.addParameter(v);

				// MODIFICAR JAVADOC DEL METODO PARA IMPORTS
				javadoc.addParameter(v.getName(),
						f.getDescription() + ". Its record type name is " + f.getSapRecordTypeName() + ", it is a "
								+ f.getSapTypeAsString() + "(" + f.getSapLength() + ").");
			}
			e.addCodeBlock(b);
		}

		// write import tables
		if (tpl != null) {
			JCoMetaData m = tpl.getMetaData();
			for (int i = 0; i < m.getFieldCount(); i++) {
				if (!isImportTable(function.getName(), m.getName(i))) continue;

				WriteTableRowsBlock b = new WriteTableRowsBlock();
				b.setInputListName(m.getName(i));
				b.setMapping(getMapping(m.getRecordTypeName(i)));
				b.setTableNameFromParameterList(m.getName(i));
				b.setTemporalJcoTableName(m.getName(i) + "_TABLE");
				e.addCodeBlock(b);

				e.addParameter(new VariableSource( //
						b.getInputListName(), //
						"List", "java.util", //
						b.getMapping().getClassName()));

				// TODO MODIFICAR JAVADOC DEL METODO PARA TABLAS IMPORTS
			}
		}

		// execute function
		e.addCodeBlock(new DefaultCodeBlock("function.execute(destination);"));

		e.addCodeBlock(new DefaultCodeBlock("Output out = new Output();"));

		// read export parameters
		if (epl != null) {
			ReadExportParametersBlock b = new ReadExportParametersBlock();
			JCoMetaData m = epl.getMetaData();
			List<FieldMapping> fm = new ArrayList<FieldMapping>();
			for (int i = 0; i < m.getFieldCount(); i++) {
				FieldMapping f = new FieldMapping();
				f.setDescription("Descripción del campo " + i);
				f.setMappingName(m.getName(i));
				f.setSapLength(m.getLength(i));
				f.setSapName(m.getName(i));
				f.setSapRecordTypeName(m.getRecordTypeName(i));
				f.setSapTypeAsString(m.getTypeAsString(i));

				String sapTypeAsString = m.getTypeAsString(i);
				if ("STRUCTURE".equals(sapTypeAsString)) {
					Mapping mapping = getMapping(m.getRecordTypeName(i));
					f.setMappingType(new TypeSource(mapping.getClassName(), modelPackageName));
					f.setStructureDefinition(mapping);
				} else {
					f.setMappingType(inferType(sapTypeAsString));
				}

				fm.add(f);
			}
			b.setFieldMappings(fm);
			e.addCodeBlock(b);
		}

		// read output tables
		if (tpl != null) {
			JCoMetaData m = tpl.getMetaData();
			for (int i = 0; i < m.getFieldCount(); i++) {
				if (!isExportTable(function.getName(), m.getName(i))) continue;

				ReadTableRowsBlock b = new ReadTableRowsBlock();
				b.setOutputListName(m.getName(i));
				b.setMapping(getMapping(m.getRecordTypeName(i)));
				b.setTableNameFromParameterList(m.getName(i));
				b.setTemporalJcoTableName(m.getName(i) + "_TABLE");
				e.addCodeBlock(b);
			}
		}

		// return out object
		e.addCodeBlock(new DefaultCodeBlock("return out;"));

		return e;
	}

	private static final TypeSource INT = new TypeSource("int");
	private static final TypeSource STRING = new TypeSource("String");
	private static final TypeSource BIG_DECIMAL = new TypeSource("BigDecimal", "java.math");

	public TypeSource inferType(String typeAsString) {

		if (typeAsString == null) throw new RuntimeException("Can't infer null as a type");
		switch (typeAsString) {
		case "STRUCTURE":
			throw new RuntimeException("STRUCTURE should not be infered");
		case "INT":
		case "NUM": // mini int?
			return INT;
		case "BCD":
			return BIG_DECIMAL;
		case "STRING":
		case "CHAR":
			return STRING;
		default:
			System.out.println("Default infer, handle " + typeAsString + " as a String");
			return STRING;
		}
	}

	/**
	 * Generates a proper mapping for the JCoFunction with the given name in this
	 * JCoGen instance's JCoRepository and stores that mapping in case is needed
	 * again.
	 * <p>
	 * If you call this method twice with the same valid structureName it will
	 * return the same instance of the generated mapping and not a new mapping each
	 * time you call it.
	 * 
	 * @param structureName the name of the structure or table for which the
	 *                      metadatais being returned structureName
	 * @return a Mapping corresponding a structure in the repository with the given
	 *         name
	 * @throws JCoException
	 */
	private Mapping getMapping(String structureName) throws JCoException {

		if (structureName == null) throw new RuntimeException("RecordTypeName for a mapping should not be null");

		if (mappings.containsKey(structureName)) return mappings.get(structureName);

		JCoRecordMetaData m = getDestination().getRepository().getStructureDefinition(structureName);

		Mapping mapping = new Mapping();
		mapping.setClassName(StringUtil.capitalize(structureName));
		mapping.setSapRecordTypeName(structureName);
		for (int i = 0; i < m.getFieldCount(); i++) {

			FieldMapping f = new FieldMapping();
			f.setDescription(m.getDescription(i));
			f.setMappingName(m.getName(i));
			f.setMappingType(this.inferType(m.getTypeAsString(i)));
			f.setSapLength(m.getLength(i));
			f.setSapName(m.getName(i));
			f.setSapRecordTypeName(m.getRecordTypeName(i));
			f.setSapTypeAsString(m.getTypeAsString(i));

			mapping.addFieldMapping(f);
		}

		generateTableToObjectMapping(mapping);

		return mapping;
	}

	public void addConfig(FunctionMappingConfig config) {
		this.functionMappingConfigurations.put(config.getFunctionName(), config);
	}

	private boolean isExportTable(String functionName, String tableName) {
		if (!functionMappingConfigurations.containsKey(functionName)) return false; // false por default
		return functionMappingConfigurations.get(functionName).getExportTables().contains(tableName);
	}

	private boolean isImportTable(String functionName, String tableName) {
		if (!functionMappingConfigurations.containsKey(functionName)) return false; // false por default
		return functionMappingConfigurations.get(functionName).getImportTables().contains(tableName);
	}

	// ====================================================================================================

	public String getFunctionPackageName() {
		return functionPackageName;
	}

	public void setFunctionPackageName(String functionPackageName) {
		this.functionPackageName = functionPackageName;
	}

	public String getModelPackageName() {
		return modelPackageName;
	}

	public void setModelPackageName(String modelPackageName) {
		this.modelPackageName = modelPackageName;
	}

	public Map<String, ClassSource> getClassSources() {
		return classSources;
	}

	public JCoDestination getDestination() {
		return destination;
	}

	public void setDestination(JCoDestination destination) {
		this.destination = destination;
	}
}
