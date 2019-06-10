package com.bardackx.jcogen.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bardackx.jcogen.source.JavadocSource.ParamEntry;

public class SourceRenderer {

	private int commentMaxLength = 80;
	private String commentLineStart = " * ";
	private String lineEnding = "\r\n";
	private String tab = "    ";

	public String render(ClassSource e) {
		return render(e, false);
	}
	
	public String render(ClassSource e, boolean isNested) {

		if (e.getName() == null) throw new SourceException("ClassSource name is missing");
		for (VariableSource i : e.getFields()) {
			if (i.getName() == null) throw new SourceException("ClassSource field name is missing");
			if (i.getType() == null) throw new SourceException("ClassSource field type is missing");
		}

		StringBuilder b = new StringBuilder();

		if (e.getPackageName() != null)
			b.append("package ").append(e.getPackageName()).append(";").append(lineEnding).append(lineEnding);

		if (!isNested) {
			Set<String> imports = e.getAccumulatedImports();
			if (!imports.isEmpty()) {
				List<String> sortedImports = new ArrayList<String>();
				sortedImports.addAll(imports);
				Collections.sort(sortedImports);

				for (String i : sortedImports)
					b.append("import ").append(i).append(";").append(lineEnding);

				b.append(lineEnding);
			}
		}

		if (e.getAccessModifier() != null) b.append(e.getAccessModifier().getCode()).append(" ");

		if (e.isStatic()) b.append("static ");

		b.append("class ").append(e.getName()).append(" ");

		// TODO extends

		// TODO implements

		b.append("{").append(lineEnding);

		// TODO nested classes
		for (ClassSource n : e.getNestedClasses()) {
			b.append(lineEnding);
			b.append(StringUtil.indent(render(n, true), tab, lineEnding));
			b.append(lineEnding);
		}

		for (VariableSource f : e.getFields()) {

			if (f.getJavadocSource() != null) {
				b.append(lineEnding);
				b.append(StringUtil.indent(render(f.getJavadocSource()), tab, lineEnding));
			}

			b.append(lineEnding);
			b.append(tab);
			if (f.getAccessModifier() != null) b.append(f.getAccessModifier().getCode()).append(" ");
			if (f.isStatic()) b.append("static ");
			if (f.isFinal()) b.append("final ");
			b.append(f.getTypeForVariableDeclaration()).append(" ").append(f.getName()).append(";");
			b.append(lineEnding);
		}

		// TODO methods
		for (MethodSource m : e.getMethods()) {
			b.append(lineEnding);
			b.append(StringUtil.indent(render(m), tab, lineEnding));
			b.append(lineEnding);
		}

		b.append("}");

		return b.toString();
	}

	public String render(MethodSource e) {

		if (e.getName() == null) throw new SourceException("MethodSource name is missing");
		if (e.getReturnType() == null) throw new SourceException("MethodSource returnType is missing");
		for (VariableSource i : e.getParameters()) {
			if (i.getName() == null) throw new SourceException("MethodSource parameter name is missing");
			if (i.getType() == null) throw new SourceException("MethodSource parameter type is missing");
		}

		StringBuilder b = new StringBuilder();

		if (e.getJavadocSource() != null) b.append(render(e.getJavadocSource())).append(lineEnding);

		if (e.getAccessModifier() != null) b.append(e.getAccessModifier().getCode()).append(" ");

		if (e.isStatic()) b.append("static ");

		if (e.isFinal()) b.append("final ");

		b.append(e.getReturnType().getName()).append(" ");

		b.append(e.getName());

		b.append("(");
		Iterator<VariableSource> p = e.getParameters().iterator();
		while (p.hasNext()) {
			VariableSource pi = p.next();
			b.append(pi.getTypeForVariableDeclaration()).append(" ").append(pi.getName());
			if (p.hasNext()) b.append(", ");
		}
		b.append(") ");

		if (!e.getThrowsExceptionSet().isEmpty()) {
			b.append("throws ");
			Iterator<String> i = e.getThrowsExceptionSet().iterator();
			while (i.hasNext())
				b.append(i.next()).append(i.hasNext() ? ", " : " ");
		}

		b.append("{").append(lineEnding);

		for (CodeBlock c : e.getCodeBlocks()) {
			if (e.getCodeBlocks().size() != 1) b.append(lineEnding);
			b.append(StringUtil.indent(c.render(lineEnding, tab), tab, lineEnding));
			b.append(lineEnding);
		}

		b.append("}");

		return b.toString();
	}

	/**
	 * Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
	 * tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
	 * quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
	 * consequat
	 * 
	 * @param LM  Sed ut perspiciatis unde omnis iste natus error sit voluptatem
	 *            accusantium doloremque laudantium.
	 * @param NF1 Sed quia consequuntur magni dolores eos qui ratione voluptatem
	 *            sequi nesciunt
	 * 
	 * @return The standard chunk of Lorem Ipsum used since the 1500s is reproduced
	 *         below for those interested. Sections 1.10.32 and 1.10.33 from "de
	 *         Finibus Bonorum et Malorum" by Cicero are also reproduced in their
	 *         exact original form, accompanied by English versions from the 1914
	 *         translation by H. Rackham.
	 */

	public String render(JavadocSource e) {

		StringBuilder b = new StringBuilder();
		b.append("/**").append(lineEnding);

		if (e.getComment() != null) {
			Iterator<String> lines = StringUtil.splitByLineLength(e.getComment(), commentMaxLength).iterator();
			while (lines.hasNext()) {
				b.append(commentLineStart).append(lines.next());
				b.append(lineEnding);
			}
		}

		if (!e.getParams().isEmpty()) b.append(getCommentLineStart()).append(lineEnding);
		for (ParamEntry p : e.getParams()) {
			b.append(commentLineStart).append("@param ").append(p.getName()).append(" ");
			String padding = StringUtil.generateWhiteSpaces(6 + 1 + p.getName().length() + 1);
			writePaddedCommentText(b, p.getComment(), padding);
		}

		if (e.getReturnComment() != null && !e.getReturnComment().trim().isEmpty()) {
			b.append(commentLineStart);
			b.append(lineEnding);
			b.append(commentLineStart).append("@return ");
			writePaddedCommentText(b, e.getReturnComment(), "        ");
		}

		if (e.getThrowsExceptionList() != null && !e.getThrowsExceptionList().isEmpty()) {
			b.append(commentLineStart);
			b.append(lineEnding);
			for (String ex : e.getThrowsExceptionList()) {
				b.append(commentLineStart).append("@throws ").append(ex).append(lineEnding);
			}
		}

		b.append(" */");

		return b.toString();
	}

	private void writePaddedCommentText(StringBuilder b, String text, String padding) {
		Iterator<String> c = StringUtil.splitByLineLength(text, commentMaxLength - padding.length()).iterator();
		if (c.hasNext()) {
			b.append(c.next()).append(lineEnding);
			while (c.hasNext()) {
				b.append(commentLineStart).append(padding).append(c.next());
				b.append(lineEnding);
			}
		}
	}

	public String getLineEnding() {
		return lineEnding;
	}

	public void setLineEnding(String lineEnding) {
		this.lineEnding = lineEnding;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public int getCommentMaxLength() {
		return commentMaxLength;
	}

	public void setCommentMaxLength(int commentMaxLength) {
		this.commentMaxLength = commentMaxLength;
	}

	public String getCommentLineStart() {
		return commentLineStart;
	}

	public void setCommentLineStart(String commentLineStart) {
		this.commentLineStart = commentLineStart;
	}

}
