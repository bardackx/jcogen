package com.bardackx.jcogen.source;

public enum AccessModifier {

	PUBLIC("public"), PROTECTED("protected"), PRIVATE("private");

	private final String code;

	private AccessModifier(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
