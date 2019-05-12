
package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionNoOp;
import com.ben.pixcreator.application.action.impl.PickColorAction;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PickActionFactory implements IActionFactory
{

      @SuppressWarnings("unused")
      private static final Logger log = LoggerFactory.getLogger(PickActionFactory.class);


      @Override
      public IAction getAction(Event event)
      {

	    // log.debug(event.getEventType().getName());

	    GuiFacade gui = GuiFacade.getInstance();
	    if (event.getEventType().getName().equals("MOUSE_PRESSED"))
	    {

		  return new PickColorAction(gui.getActiveTab(), (MouseEvent) event);

	    }

	    if (event.getEventType().getName().equals("MOUSE_MOVED"))
	    {

		  PixTab tab = gui.getActiveTab();
		  Color color = PickColorAction.readColorFromEventCoordinates(tab,
			      (MouseEvent) event);
		  gui.setHoverColor(color);

	    }
	    return new ActionNoOp();
      }

}
