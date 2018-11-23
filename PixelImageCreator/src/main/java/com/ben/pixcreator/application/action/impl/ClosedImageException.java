package com.ben.pixcreator.application.action.impl;

public class ClosedImageException extends Exception {

	//TODO implement exception correctly
	private String message = "image is closed";
	
	
	@Override
	public String getMessage() {
		
		return message;
	}

	
}
