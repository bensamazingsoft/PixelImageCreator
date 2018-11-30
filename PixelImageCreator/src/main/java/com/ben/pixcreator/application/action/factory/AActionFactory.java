package com.ben.pixcreator.application.action.factory;

import java.awt.Event;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.application.action.IAction;

public abstract class AActionFactory {

	
	
	public abstract IAction getAction(Event event);


	
	
}
