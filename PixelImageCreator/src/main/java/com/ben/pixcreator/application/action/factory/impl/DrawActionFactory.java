package com.ben.pixcreator.application.action.factory.impl;

import java.awt.Event;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.AActionFactory;

public class DrawActionFactory extends AActionFactory {

	
	

	
	
	@Override
	public IAction getAction(Event event) {
//TODO need a nullAction
		IAction action = new NullAction();
		
		if (event instanceof MouseEvent){
			
			switch (event.getEventType()){
			
			case (EventType.MOUSE_CLICKED) : {
				//TODO impl mouse clicked action
				break;
			}
			//TODO all other cases
			
			
			
			}
		}
		
		
		
		return action;
	}

}
