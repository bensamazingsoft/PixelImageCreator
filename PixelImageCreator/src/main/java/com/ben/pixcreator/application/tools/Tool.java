package com.ben.pixcreator.application.tools;


public enum Tool {

	DRAW("DRAW"),
	SELECT("SELECT"),
	MOVE("MOVE"),
	PAN("PAN"),
	PICK("PICK"),
	RESIZE("RESIZE"),
	ZOOMIN("ZOOMIN"),
	ZOOMOUT("ZOOMOUT");	
	
	private final String name;
	
	Tool(String name){
		this.name = name;
	}
	
	private String name(){
		return naùme;
	}
	
	public static Tool getTool(String name){
		
		Tool tool = Tool.SELECT;
		
		switch (name){
		
		case "DRAW" : tool = Tool.DRAW;
		break;
		case "MOVE" : tool = Tool.MOVE;
		break;
		case "PAN" : tool = Tool.PAN;
		break;
		case "PICK" : tool = Tool.PICK;
		break;
		case "RESIZE" : tool = Tool.RESIZE;
		break;
		case "ZOOMIN" : tool = Tool.ZOOMIN;
		break;
		case "ZOOMOUT" : tool = Tool.ZOOMOUT;
		break;
		}
		
		return tool;
		
	}
	
	
}
