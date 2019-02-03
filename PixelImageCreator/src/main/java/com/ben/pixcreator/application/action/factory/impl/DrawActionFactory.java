
package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionChangeCellColor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawActionFactory implements IActionFactory
{

      private static final Logger log = LoggerFactory.getLogger(DrawActionFactory.class);


      @Override
      public IAction getAction(Event event)
      {

	    // log.debug(event.getEventType().getName());

	    IAction action = null;

	    if (event instanceof MouseEvent)
	    {

		  switch (event.getEventType().getName())
		  {

		  case ("MOUSE_CLICKED"):
		  {

			int x = new Double(((MouseEvent) event).getX()).intValue();
			int y = new Double(((MouseEvent) event).getY()).intValue();

			Color color = GuiFacade.getInstance().getActiveColor();

			ALayer layer = GuiFacade.getInstance().getActiveLayer();
			PixImage image = GuiFacade.getInstance().getActiveimage();
			if (GuiFacade.getInstance().getActiveLayer() instanceof PixLayer)
			{
			      PixLayer pxLayer = (PixLayer) layer;
			      return new ActionChangeCellColor(image, pxLayer, new Coord(x, y), color);
			}
			break;
		  }
		  // TODO all other cases

		  }
	    }

	    return action;
      }

}
