package com.common.file.exception;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = -1155630339831787681L;

	public FileException(Throwable cause) {
		super(cause);
	}

	public FileException(String message) {
		super(message);
	}

}
