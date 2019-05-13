
package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.NoOpAction;

import javafx.event.Event;

public class PanActionFactory implements IActionFactory
{

      @SuppressWarnings("unused")
      private static final Logger log = LoggerFactory.getLogger(PanActionFactory.class);


      @Override
      public IAction getAction(Event event)
      {

	    // log.debug(event.getEventType().getName());

	    return new NoOpAction();
      }

}
