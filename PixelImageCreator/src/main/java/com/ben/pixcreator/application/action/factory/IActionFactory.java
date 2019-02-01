
package com.ben.pixcreator.application.action.factory;

import com.ben.pixcreator.application.action.IAction;

import javafx.event.Event;

public interface  IActionFactory
{

      public  IAction getAction(Event event);

}
