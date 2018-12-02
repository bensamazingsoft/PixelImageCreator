
package com.ben.pixcreator.application.action.factory;

import com.ben.pixcreator.application.action.IAction;

import javafx.event.Event;

public abstract class AActionFactory
{

      public abstract IAction getAction(Event event);

}
