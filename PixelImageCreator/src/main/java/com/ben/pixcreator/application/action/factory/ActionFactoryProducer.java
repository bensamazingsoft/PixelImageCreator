
package com.ben.pixcreator.application.action.factory;

import com.ben.pixcreator.application.action.factory.impl.DrawActionFactory;
import com.ben.pixcreator.application.action.factory.impl.MoveActionFactory;
import com.ben.pixcreator.application.action.factory.impl.PanActionFactory;
import com.ben.pixcreator.application.action.factory.impl.PickActionFactory;
import com.ben.pixcreator.application.action.factory.impl.SelectActionFactory;
import com.ben.pixcreator.application.tools.PixTool;

public class ActionFactoryProducer
{

      public static IActionFactory getActionFactory(PixTool tool)
      {

	    switch (tool)
	    {
	    case DRAW:

		  return new DrawActionFactory();

	    case MOVE:
		  return new MoveActionFactory();
	    case PAN:
		  return new PanActionFactory();
	    case PICK:
		  return new PickActionFactory();
	    case RESIZE:
		  break;
	    case SELECT:
		  return new SelectActionFactory();
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
