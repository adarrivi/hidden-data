package com.hidden.data.nosql.model.discovery;

public class Line {

	private int lineNumber;
	private String content;

	public Line(int lineNumber, String content) {
		super();
		this.lineNumber = lineNumber;
		this.content = content;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getContent() {
		return content;
	}

}
