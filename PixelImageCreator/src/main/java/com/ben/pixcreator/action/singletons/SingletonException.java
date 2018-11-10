package com.ben.pixcreator.action.singletons;

public class SingletonException extends Exception {

	

	private static final long serialVersionUID = 1L;
	private final String message = "An exception occured while attempting to instanciate a singleton"; 
	
	
	public SingletonException(){
		super();
	}
	
	public SingletonException(String s){
		super(s);
	}
	
	public SingletonException(String s,Throwable t){
		super(s,t);
	}
	
	@Override
	public String getMessage() {		
		return message;
	}

}
