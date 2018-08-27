package com.ksh.core.exception;

public class EmployeeEvaluationConfirmedException extends KSHException {


	private static final long serialVersionUID = 1L;

	public EmployeeEvaluationConfirmedException() {
	}

	public EmployeeEvaluationConfirmedException(String msg) {
		super(msg);
	}

	public EmployeeEvaluationConfirmedException(Exception e) {
		super(e);
	}
}
