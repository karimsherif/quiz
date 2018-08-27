package com.ksh.core.exception;

public class NoneUniquException extends KSHException {


	private static final long serialVersionUID = 1L;

	public NoneUniquException() {
	}

	public NoneUniquException(String msg) {
		super(msg);
	}

	public NoneUniquException(Exception e) {
		super(e);
	}
}
