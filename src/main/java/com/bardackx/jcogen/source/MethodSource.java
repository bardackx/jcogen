package com.bardackx.jcogen.source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MethodSource {

	private JavadocSource javadocSource;
	private AccessModifier accessModifier;
	private boolean isStatic;
	private boolean isFinal;
	private TypeSource returnType;
	private String name;
	private List<VariableSource> parameters = new ArrayList<>();
	private Set<String> throwsExceptionSet = new HashSet<>();
	private List<CodeBlock> codeBlocks = new ArrayList<>();

	public void addThrowsException(String exceptionClass) {
		throwsExceptionSet.add(exceptionClass);
	}

	public void addParameter(VariableSource parameter) {
		parameters.add(parameter);
	}

	public void addCodeBlock(CodeBlock codeBlock) {
		this.codeBlocks.add(codeBlock);
	}

	public Collection<? extends String> getAccumulatedImports() {
		Set<String> s = new HashSet<>();

		if (this.codeBlocks != null) for (CodeBlock e : codeBlocks)
			s.addAll(e.getRequiredImports());

		if (this.parameters != null) for (VariableSource e : parameters) {
			if (e.getTypePackage() != null) {
				s.add(e.getTypeImport());
			}
		}

		return s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public TypeSource getReturnType() {
		return returnType;
	}

	public void setReturnType(TypeSource returnType) {
		this.returnType = returnType;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public Set<String> getThrowsExceptionSet() {
		return throwsExceptionSet;
	}

	public void setThrowsExceptionSet(Set<String> throwsExceptionSet) {
		this.throwsExceptionSet = throwsExceptionSet;
	}

	public List<VariableSource> getParameters() {
		return parameters;
	}

	public void setParameters(List<VariableSource> parameters) {
		this.parameters = parameters;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public List<CodeBlock> getCodeBlocks() {
		return codeBlocks;
	}

	public void setCodeBlock(List<CodeBlock> codeBlock) {
		this.codeBlocks = codeBlock;
	}

	public JavadocSource getJavadocSource() {
		return javadocSource;
	}

	public void setJavadocSource(JavadocSource javadocSource) {
		this.javadocSource = javadocSource;
	}

}
