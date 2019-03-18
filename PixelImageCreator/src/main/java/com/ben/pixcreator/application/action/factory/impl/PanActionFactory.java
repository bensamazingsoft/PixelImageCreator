
package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionNoOp;

import javafx.event.Event;

public class PanActionFactory implements IActionFactory
{

      @SuppressWarnings("unused")
      private static final Logger log = LoggerFactory.getLogger(PanActionFactory.class);


      @Override
      public IAction getAction(Event event)
      {

	    log.debug(event.getEventType().getName());

	    // GuiFacade gui = GuiFacade.getInstance();
	    // if (event instanceof MouseEvent && gui.getActiveLayer() instanceof PixLayer)
	    // {
	    //
	    // setTabScrollPaneCursor(gui);
	    //
	    // }
	    return new ActionNoOp();
      }

      // private void setTabScrollPaneCursor(GuiFacade gui)
      // {
      //
      // PixTab tab = gui.getActiveTab();
      // tab.getScrollPane().setCursor(new CanvasCursorFactory().getCursor(tab.getCanvas().isMouseTransparent()));
      // }
}
