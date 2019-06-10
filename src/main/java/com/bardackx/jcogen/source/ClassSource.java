package com.bardackx.jcogen.source;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassSource {

	private String packageName;
	private String name;
	private AccessModifier accessModifier;
	private boolean isStatic;

	private List<ClassSource> nestedClasses = new ArrayList<>();
	private List<MethodSource> methods = new ArrayList<>();
	private Set<String> imports = new HashSet<>();
	private List<VariableSource> fields = new ArrayList<>();

	public void addImport(String importedClass) {
		imports.add(importedClass);
	}

	public void addField(VariableSource field) {
		fields.add(field);
	}

	public void addMethod(MethodSource method) {
		methods.add(method);
	}

	public void addNestedClass(ClassSource e) {
		nestedClasses.add(e);
	}

	public void addProperty(TypeSource type, String name, VariableSource[] variablePtr, MethodSource[] methodPtr) {

		String capitalizedName = StringUtil.capitalize(name);

		VariableSource f = new VariableSource();
		f.setAccessModifier(AccessModifier.PRIVATE);
		f.setType(type);
		f.setName(name);

		MethodSource getter = new MethodSource();
		getter.setAccessModifier(AccessModifier.PUBLIC);
		getter.setName("get" + capitalizedName);
		getter.setReturnType(type);
		getter.addCodeBlock(new DefaultCodeBlock("return " + name + ";"));

		MethodSource setter = new MethodSource();
		setter.setAccessModifier(AccessModifier.PUBLIC);
		setter.setName("set" + capitalizedName);
		setter.setReturnType(new TypeSource("void"));
		setter.addCodeBlock(new DefaultCodeBlock("this." + name + " = " + name + ";"));
		setter.addParameter(f);

		addField(f);
		addMethod(getter);
		addMethod(setter);

		if (variablePtr != null) variablePtr[0] = f;
		if (methodPtr != null) {
			methodPtr[0] = getter;
			methodPtr[1] = setter;
		}
	}

	public void addProperty(TypeSource type, String name) {
		addProperty(type, name, null, null);
	}

	public Set<String> getAccumulatedImports() {
		Set<String> s = new HashSet<>();
		if (this.imports != null) s.addAll(this.imports);
		if (this.methods != null) for (MethodSource e : methods)
			s.addAll(e.getAccumulatedImports());
		if (this.fields != null) for (VariableSource e : fields) {
			TypeSource t = e.getType();
			if (t.getPackageName() == null) continue;
			s.add(t.getPackageName() + "." + t.getName());
		}
		if (this.nestedClasses != null) for (ClassSource e : nestedClasses)
			s.addAll(e.getAccumulatedImports());
		return s;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MethodSource> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodSource> methods) {
		this.methods = methods;
	}

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void setImports(Set<String> imports) {
		this.imports = imports;
	}

	public List<VariableSource> getFields() {
		return fields;
	}

	public void setFields(List<VariableSource> fields) {
		this.fields = fields;
	}

	public List<ClassSource> getNestedClasses() {
		return nestedClasses;
	}

	public void setNestedClasses(List<ClassSource> nestedClasses) {
		this.nestedClasses = nestedClasses;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public String getCanonicalName() {
		if (packageName != null) return packageName + "." + name;
		return name;
	}

}
