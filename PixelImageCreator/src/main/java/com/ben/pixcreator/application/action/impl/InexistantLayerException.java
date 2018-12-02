
package com.ben.pixcreator.application.action.impl;

public class InexistantLayerException extends Exception
{

      /**
      * 
      */
      private static final long	serialVersionUID = -185500405643880079L;
      private String		message		 = "layer does not exist";


      @Override
      public String getMessage()
      {

	    return message;
      }

}
