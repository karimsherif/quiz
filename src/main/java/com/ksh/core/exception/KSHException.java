package com.ksh.core.exception;

public class KSHException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public KSHException() {
	}

	public KSHException(String msg) {
		super(msg);
	}

	public KSHException(Exception e) {
		super(e);
	}
}
