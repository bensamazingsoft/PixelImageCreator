package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionUpdateSelection;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SelectActionFactory implements IActionFactory {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(SelectActionFactory.class);

	@Override
	public IAction getAction(Event event) {
		IAction action = null;

		if (event instanceof MouseEvent) {

			switch (event.getEventType().getName()) {

			case ("MOUSE_PRESSED"): {

				if (((MouseEvent) event).getButton() == MouseButton.PRIMARY) {

					try {
						if (((MouseEvent) event).getButton() == MouseButton.PRIMARY) {

							GuiFacade.getInstance().getSelections().put(GuiFacade.getInstance().getActiveImage(),
									new Selection(eventCoord((MouseEvent) event), eventCoord((MouseEvent) event)));

							return new ActionUpdateSelection(GuiFacade.getInstance().getActiveImage());

						}

					} catch (Exception e) {
						new ExceptionPopUp(e);
					}
				}
				event.consume();
				break;
			}

			case ("MOUSE_DRAGGED"): {

				if (((MouseEvent) event).getButton() == MouseButton.PRIMARY) {

					if (null != GuiFacade.getInstance().getSelections().get(GuiFacade.getInstance().getActiveImage())) {

						Coord start = GuiFacade.getInstance().getSelections()
								.get(GuiFacade.getInstance().getActiveImage()).getStart();

						GuiFacade.getInstance().getSelections().put(GuiFacade.getInstance().getActiveImage(),
								new Selection(start, eventCoord((MouseEvent) event)));

						return new ActionUpdateSelection(GuiFacade.getInstance().getActiveImage());

					}

				}
				event.consume();
				break;
			}

			}
		}
		return action;
	}
}
