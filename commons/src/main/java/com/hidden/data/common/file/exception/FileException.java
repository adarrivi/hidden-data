package com.hidden.data.common.file.exception;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = -1155630339831787681L;

	public FileException(String message) {
		super(message);
	}

	public FileException(Throwable cause) {
		super(cause);
	}
}
