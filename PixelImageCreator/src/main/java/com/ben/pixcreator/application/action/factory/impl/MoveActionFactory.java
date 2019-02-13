
package com.ben.pixcreator.application.action.factory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionNoOp;
import com.ben.pixcreator.application.action.impl.ActionTranslateLayer;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.grouplock.manager.GroupLockManager;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class MoveActionFactory implements IActionFactory {

	private static final Logger log = LoggerFactory.getLogger(MoveActionFactory.class);

	public static boolean						moveStarted;
	public static Coord							startCoord;
	public static Map<ALayer, ALayer.Memento>	startState;

	@Override
	public IAction getAction(Event event) {

		if (event instanceof MouseEvent) {

			switch (event.getEventType().getName()) {

			case ("MOUSE_PRESSED"): {
				Executor.getInstance().startOperation();
				moveStarted = true;
				startCoord = eventCoord((MouseEvent) event);
				startState = new HashMap<>();

				ALayer activeLayer = GuiFacade.getInstance().getActiveLayer();
				Set<ALayer> layers = GroupLockManager.getInstance().getGroupLock(activeLayer);
				layers.add(activeLayer);

				for (ALayer layer : layers) {
					startState.put(layer, layer.getMemento());
				}
				// log.debug(event.getEventType().getName());
				// log.debug(startCoord.toString());
				event.consume();
				break;
			}

			case ("MOUSE_DRAGGED"): {
				// log.debug(event.getEventType().getName());
				PixImage image = GuiFacade.getInstance().getActiveImage();
				ALayer activeLayer = GuiFacade.getInstance().getActiveLayer();

				Set<ALayer> layers = GroupLockManager.getInstance().getGroupLock(activeLayer);
				layers.add(activeLayer);

				Selection selection = GuiFacade.getInstance().getSelections().get(image);

				Coord eventCoord = eventCoord((MouseEvent) event);

				int translateX = eventCoord.getX() - startCoord.getX();
				int translateY = eventCoord.getY() - startCoord.getY();
				// log.debug("" + translateX);
				for (ALayer layer : layers) {
					// log.debug(startState.toString());
					try {
						startState.get(layer).restore();
						Executor.getInstance()
								.continueOperation(new ActionTranslateLayer(translateX, translateY, layer, selection));
					} catch (Exception e) {
						new ExceptionPopUp(e);
					}

				}
				event.consume();
				break;
			}

			case ("MOUSE_RELEASED"): {
				Executor.getInstance().endOperation();
				moveStarted = false;
				startCoord = null;
				// log.debug(event.getEventType().getName());
				event.consume();
				break;
			}
			case ("MOUSE_ENTERED"): {

				event.consume();
				return new ActionNoOp();
			}
			case ("MOUSE_MOVED"): {
				event.consume();
				return new ActionNoOp();
			}
			case ("MOUSE_EXITED"): {
				event.consume();
				return new ActionNoOp();
			}
			}

		}

		return null;
	}

}
