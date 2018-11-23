package com.ben.pixcreator.application.action.impl;

public class InexistantLayerException extends Exception {

	private String message = "layer does not exist";
	
	
	@Override
	public String getMessage() {
		return message;
	}

}
