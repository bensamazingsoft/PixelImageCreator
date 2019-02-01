
package com.ben.pixcreator.application.action.factory.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionChangeCellColor;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawActionFactory implements IActionFactory {

	@Override
	public IAction getAction(Event event) {

		IAction action = null;

		if (event instanceof MouseEvent) {

			switch (event.getEventType().getName()) {

			case ("MOUSE_CLICKED"): {
				int x = new Double(((MouseEvent) event).getX()).intValue();
				int y = new Double(((MouseEvent) event).getY()).intValue();

				Color color = GuiFacade.getInstance().getActiveColor();

				ALayer layer = GuiFacade.getInstance().getActiveLayer();
				if (GuiFacade.getInstance().getActiveLayer() instanceof PixLayer) {
					PixLayer pxLayer = (PixLayer) layer;
					return new ActionChangeCellColor(pxLayer, new Coord(x, y), color);
				}
				break;
			}
			// TODO all other cases

			}
		}

		return action;
	}

}
