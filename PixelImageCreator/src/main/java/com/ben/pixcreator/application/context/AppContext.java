
package com.ben.pixcreator.application.context;

import java.io.IOException;

import javax.tools.Tool;

import javafx.scene.paint.Color;

public class AppContext
{

      private static AppContext	instance;

      private PropertiesContext	properties;

      private Color		gridColor, currDrawColor;

	private Tool currTool;


      private AppContext() throws IOException
      {

	
	    properties = PropertiesContext.getInstance();
	    
	    
	    currTool = Tool.getTool(properties.get("startTool"));
	    
	    gridColor = getColor(properties.get("gridColor"));
	    currDrawColor = getColor(properties.get("drawColor"));

      }





	//convert color string property to color object
      private Color getColor(String string) {
    	  Color color = Color.BLACK;
    	  String[] rgbValues = string.split(",");
    	  
    	  
    	  if (rgbValues.length == 3){
    		  color = Color.rgb(Integer.valueOf(rgbValues[0]), Integer.valueOf(rgbValues[1]), Integer.valueOf(rgbValues[2]));
    	  } else if (rgbValues.length ==4){
    		  color = Color.rgb(Integer.valueOf(rgbValues[0]), Integer.valueOf(rgbValues[1]), Integer.valueOf(rgbValues[2]), Double.valueOf(rgbValues[3]));
    	  }
    	  
		return color;
	}


	public static AppContext getInstance() throws IOException
      {

	    if (instance == null)
	    {
		  instance = new AppContext();
	    }
	    return instance;

      }


	public PropertiesContext getProperties() {
		return properties;
	}


	public void setProperties(PropertiesContext properties) {
		this.properties = properties;
	}


	public Color getGridColor() {
		return gridColor;
	}


	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}


	public Color getCurrDrawColor() {
		return currDrawColor;
	}


	public void setCurrDrawColor(Color currDrawColor) {
		this.currDrawColor = currDrawColor;
	}


	public void setCurrTool(Tool tool) {
		
		properties.set("startTool", tool.getName());
		
		currTool = tool;
		
	}


     

}
