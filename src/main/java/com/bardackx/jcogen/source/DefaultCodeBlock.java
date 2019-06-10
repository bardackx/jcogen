package com.bardackx.jcogen.source;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DefaultCodeBlock implements CodeBlock {

	private String text;
	private String[] imports;

	public DefaultCodeBlock() {
	}

	public DefaultCodeBlock(String text) {
		this.text = text;
	}

	public DefaultCodeBlock(String text, String... imports) {
		this.text = text;
		this.imports = imports;
	}

	@Override
	public String render(String lineEnding, String tab) {
		return getText();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Collection<String> getRequiredImports() {
		Set<String> set = new HashSet<>();
		if (imports != null) for (String i : imports)
			set.add(i);
		return set;
	}

}
