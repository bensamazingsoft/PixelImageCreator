
package com.ben.pixcreator.application.action.factory;

import com.ben.pixcreator.application.action.factory.impl.DrawActionFactory;
import com.ben.pixcreator.application.tools.PixTool;

public class ActionFactoryProducer
{

      public AActionFactory getActionFactory(PixTool tool)
      {

	    switch (tool)
	    {
	    case DRAW:
		  return new DrawActionFactory();
	    // TODO all other tool cases
	    case MOVE:
		  break;
	    case PAN:
		  break;
	    case PICK:
		  break;
	    case RESIZE:
		  break;
	    case SELECT:
		  break;
	    case ZOOMIN:
		  break;
	    case ZOOMOUT:
		  break;
	    default:
		  break;

	    }

	    return null;

      }

}
