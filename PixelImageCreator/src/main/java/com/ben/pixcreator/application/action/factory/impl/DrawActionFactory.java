
package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionChangeCellColor;
import com.ben.pixcreator.application.action.impl.ActionDeleteCell;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawActionFactory implements IActionFactory {

	private static final Logger log = LoggerFactory.getLogger(DrawActionFactory.class);

	@Override
	public IAction getAction(Event event) {

		// log.debug(event.getEventType().getName());

		IAction action = null;

		if (event instanceof MouseEvent && GuiFacade.getInstance().getActiveLayer() instanceof PixLayer) {

			switch (event.getEventType().getName()) {

			case ("MOUSE_PRESSED"): {
				Executor.getInstance().startOperation();
				try {
					if (((MouseEvent) event).getButton() == MouseButton.PRIMARY) {
						Executor.getInstance().continueOperation((ICancelable) getChangeCellColorAction(event));
					}
					if (((MouseEvent) event).getButton() == MouseButton.SECONDARY) {
						Executor.getInstance().continueOperation((ICancelable) getDeleteCellAction(event));
					}
				} catch (Exception e) {
					new ExceptionPopUp(e);
				}
				event.consume();
				break;
			}

			case ("MOUSE_DRAGGED"): {

				try {
					if (((MouseEvent) event).getButton() == MouseButton.PRIMARY) {
						Executor.getInstance().continueOperation((ICancelable) getChangeCellColorAction(event));
					}
					if (((MouseEvent) event).getButton() == MouseButton.SECONDARY) {
						event.consume();
						Executor.getInstance().continueOperation((ICancelable) getDeleteCellAction(event));
					}
				} catch (Exception e) {
					new ExceptionPopUp(e);
				}

				break;
			}

			case ("MOUSE_RELEASED"): {
				Executor.getInstance().endOperation();
				event.consume();
				break;
			}

			// TODO all other cases

			}
		}

		return action;
	}

	private IAction getDeleteCellAction(Event event) {

		ALayer layer = GuiFacade.getInstance().getActiveLayer();
		PixImage image = GuiFacade.getInstance().getActiveimage();

		Coord coord = eventCoord(event, image);

		return new ActionDeleteCell(image, (PixLayer) layer, coord);
	}

	private IAction getChangeCellColorAction(Event event) {

		ALayer layer = GuiFacade.getInstance().getActiveLayer();
		PixImage image = GuiFacade.getInstance().getActiveimage();
		Color color = GuiFacade.getInstance().getActiveColor();

		if (GuiFacade.getInstance().getActiveLayer() instanceof PixLayer) {

			PixLayer pxLayer = (PixLayer) layer;
			Coord eventCoord = eventCoord(event, image);

			return new ActionChangeCellColor(image, pxLayer, eventCoord, color);
		}
		return null;
	}

	private Coord eventCoord(Event event, PixImage image) {

		int x = new Double(((MouseEvent) event).getX()).intValue();
		int y = new Double(((MouseEvent) event).getY()).intValue();
		int cellX = (int) Math.floor(x / (image.getxSize() / image.getxGridResolution()));
		int cellY = (int) Math.floor(y / (image.getySize() / image.getyGridResolution()));

		return new Coord(cellX, cellY);
	}

}
