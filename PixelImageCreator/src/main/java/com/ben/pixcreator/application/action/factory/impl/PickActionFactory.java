package com.ben.pixcreator.application.action.factory.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionNoOp;
import com.ben.pixcreator.application.action.impl.PickColorAction;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class PickActionFactory implements IActionFactory {

	@Override
	public IAction getAction(Event event) {

		if (event.getEventType().getName().equals("MOUSE_PRESSED")) {

			return new PickColorAction(GuiFacade.getInstance().getActiveTab(), (MouseEvent) event);

		}

		return new ActionNoOp();
	}

}
