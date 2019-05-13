
package com.ben.pixcreator.application.exception;

public class ClosedImageException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = -8673777083834864327L;

	private String message = "image is closed";

	@Override
	public String getMessage() {

		return message;
	}

}
