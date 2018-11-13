
package com.ben.pixcreator.application.context;

import java.io.IOException;

import javafx.scene.paint.Color;

public class AppContext
{

      private static AppContext	instance;

      private PropertiesContext	properties;

      private Color		gridColor;


      private AppContext() throws IOException
      {

	    // TODO initialize color
	    properties = PropertiesContext.getInstance();

      }


      public static AppContext getInstance() throws IOException
      {

	    if (instance == null)
	    {
		  instance = new AppContext();
	    }
	    return instance;

      }


      public Color getGridColor()
      {

	    return gridColor;
      }

}
