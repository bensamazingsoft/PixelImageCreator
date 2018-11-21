package com.ben.pixcreator.gui.facade;

import javax.tools.Tool;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.controls.tool.toolbar.ToolBar;

public class GuiFacade {

	private GuiFacade instance;
	
	private Scene scene;
	private PixMenuBar pixMenuBar;
	private ToolBar toolBar;
	
	
	
	
	private GuiFacade(){
		
	}
	
	
	public GuiFacade getInstance(){
		
		if (instance == null){
			
			instance = new GuiFacade();
		}
		
		return instance;
	}
	
	
	
	public void toggleToolTo(Tool tool){
		//TODO  toggle tool in Context and Gui Controls
	Tool tool =	(Tool) toolBar.getToggleGroup().getSelectedToggle.getUserData();
	AppContext.getInstance().setCurrTool(tool);
	
	}
	
	
}
