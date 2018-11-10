package com.ben.pixcreator.action.singletons.executor;

import java.util.LinkedList;

import com.ben.pixcreator.action.IAction;
import com.ben.pixcreator.action.singletons.ASingleton;

public class Executor extends ASingleton<Executor>{

	
	
	private IAction currOperation;
	private LinkedList<IAction> history;
	
	
	private Executor(){
	
	}
	
	public void executeAction(IAction action){
		//TODO
	}
	
	public void startOperation(IAction action){
		//TODO
	}
	
	public void continueOperation(IAction action){
		//TODO
	}
	
	public void endOperation(IAction action){
		//TODO
	}
}
