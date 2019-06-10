package com.bardackx.jcogen.source;

import java.util.ArrayList;
import java.util.List;

public class JavadocSource {

	public static class ParamEntry {
		private String name;
		private String comment;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}

	private String comment;
	private String returnComment;
	private List<ParamEntry> params = new ArrayList<>();
	private List<String> throwsExceptionList = new ArrayList<>();
	
	public void addThrows(String exceptionTyppe) {
		throwsExceptionList.add(exceptionTyppe);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void addParameter(String name, String comment) {
		ParamEntry p = new ParamEntry();
		p.name = name;
		p.comment = comment;
		params.add(p);
	}

	public List<ParamEntry> getParams() {
		return params;
	}

	public void setParams(List<ParamEntry> params) {
		this.params = params;
	}

	public String getReturnComment() {
		return returnComment;
	}

	public void setReturnComment(String returnComment) {
		this.returnComment = returnComment;
	}

	public List<String> getThrowsExceptionList() {
		return throwsExceptionList;
	}

	public void setThrowsExceptionList(List<String> throwsExceptionList) {
		this.throwsExceptionList = throwsExceptionList;
	}

	
}
