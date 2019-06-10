package com.bardackx.jcogen.source;

import java.util.Collection;

public interface CodeBlock {

	public Collection<String> getRequiredImports();
	
	public String render(String lineEnding, String tab);

}
