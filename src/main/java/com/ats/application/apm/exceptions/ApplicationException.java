package com.ats.application.apm.exceptions;

import org.apache.log4j.Logger;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = -9129076662856627496L;
	private static final Logger logger = Logger.getLogger(ApplicationException.class);

	public ApplicationException() {
		super();
		logger.error("\r\nApplication exception Occured.");
	}

	public ApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		logger.error("Error occured. Error " + message, cause);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		logger.error("Error occured. Error " + message, cause);
	}

	public ApplicationException(String message) {
		super(message);
		logger.error("Error occured. Error " + message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
		logger.error("Error occured.", cause);
	}

}
