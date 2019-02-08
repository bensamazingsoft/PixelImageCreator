package com.ben.pixcreator.application.action.factory.impl;

import java.util.Set;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionTranslateLayer;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.grouplock.manager.GroupLockManager;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class MoveActionFactory implements IActionFactory {

	public static boolean	moveStarted;
	public static Coord		startCoord;

	@Override
	public IAction getAction(Event event) {

		if (event instanceof MouseEvent) {

			switch (event.getEventType().getName()) {

			case ("MOUSE_PRESSED"): {
				Executor.getInstance().startOperation();
				moveStarted = true;
				startCoord = eventCoord((MouseEvent) event);
			}

			case ("MOUSE_DRAGGED"): {

				PixImage image = GuiFacade.getInstance().getActiveImage();
				ALayer activeLayer = GuiFacade.getInstance().getActiveLayer();

				Set<ALayer> layers = GroupLockManager.getInstance().getGroupLock(activeLayer);

				Selection selection = GuiFacade.getInstance().getSelections().get(image);

				Coord eventCoord = eventCoord((MouseEvent) event);

				int translateX = startCoord.getX() - eventCoord.getX();
				int translateY = startCoord.getY() - eventCoord.getY();

				for (ALayer layer : layers) {

					Executor.getInstance()
							.executeAction(new ActionTranslateLayer(translateX, translateY, layer, selection));

				}

			}

			case ("MOUSE_RELEASED"): {
				Executor.getInstance().endOperation();
				moveStarted = false;
				startCoord = null;
			}

			}

		}

		return null;
	}

}
