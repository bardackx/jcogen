package com.bardackx.jcogen.source;

public class VariableSource {

	private JavadocSource javadocSource;
	private AccessModifier accessModifier;
	private boolean isStatic;
	private boolean isFinal;
	private TypeSource type;
	private String[] typeGenerics;
	private String name;

	public VariableSource() {
	}

	public VariableSource(String name, String type, String typePackage, String... typeGenerics) {
		this.name = name;
		this.type = new TypeSource(type, typePackage);
		if (typeGenerics != null) this.typeGenerics = typeGenerics;
	}

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public TypeSource getType() {
		return type;
	}

	public void setType(TypeSource type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public JavadocSource getJavadocSource() {
		return javadocSource;
	}

	public void setJavadocSource(JavadocSource javadocSource) {
		this.javadocSource = javadocSource;
	}

	public String getTypePackage() {
		return type.getPackageName();
	}

	public String[] getTypeGenerics() {
		return typeGenerics;
	}

	public void setTypeGenerics(String... typeGenerics) {
		this.typeGenerics = typeGenerics;
	}

	public String getTypeImport() {
		return type.getPackageName() + "." + type.getName();
	}

	public String getTypeForVariableDeclaration() {
		if (typeGenerics == null || typeGenerics.length == 0) return type.getName();
		return type.getName() + "<" + StringUtil.characterSeparatedValues(typeGenerics, ", ") + ">";
	}

}
