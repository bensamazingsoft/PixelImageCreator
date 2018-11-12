package com.ben.pixcreator.application.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class AppContext  {

	private  AppContext instance; 
	
	private PropertiesContext properties;
	
	
	private AppContext(){
		
	properties = PropertiesContext.getInstance();
		
	}

	
public AppContext getInstance(){
	
	if (instance == null){
		instance = new AppContext();
	}
	return instance;
	
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}
