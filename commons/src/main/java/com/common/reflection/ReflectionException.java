package com.common.reflection;

public class ReflectionException extends RuntimeException {

	private static final long serialVersionUID = -1331852814245482362L;

	public ReflectionException(Throwable throwable) {
		super(throwable);
	}

	public ReflectionException(String message) {
		super(message);
	}

}
