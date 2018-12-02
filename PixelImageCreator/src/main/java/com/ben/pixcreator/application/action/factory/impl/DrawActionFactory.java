
package com.ben.pixcreator.application.action.factory.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.AActionFactory;
import com.ben.pixcreator.application.action.impl.ActionNull;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class DrawActionFactory extends AActionFactory
{

      @Override
      public IAction getAction(Event event)
      {

	    // TODO need a nullAction
	    IAction action = new ActionNull();

	    if (event instanceof MouseEvent)
	    {

		  switch (event.getEventType().getName())
		  {

		  case ("MOUSE_CLICKED"):
		  {
			// TODO impl mouse clicked action
			break;
		  }
		  // TODO all other cases

		  }
	    }

	    return action;
      }

}
