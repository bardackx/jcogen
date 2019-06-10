package com.bardackx.jcogen.source;

public class TypeSource {

	private String packageName;
	private String name;

	public TypeSource() {
		// TODO Auto-generated constructor stub
	}

	public TypeSource(String name) {
		this.name = name;
	}

	public TypeSource(String name, String packageName) {
		this.packageName = packageName;
		this.name = name;
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

	@Override
	public String toString() {
		throw new RuntimeException("DOH!");
	}
}
