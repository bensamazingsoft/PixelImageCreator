
package com.ben.pixcreator.application.action.factory;

import com.ben.pixcreator.application.action.factory.impl.DrawActionFactory;
import com.ben.pixcreator.application.action.factory.impl.SelectActionFactory;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class ActionFactoryProducer {

	public static IActionFactory getActionFactory(PixTool tool) {

		switch (tool) {
		case DRAW:
			if (GuiFacade.getInstance().getActiveLayer() instanceof PixLayer) {
				return new DrawActionFactory();
			}
		case MOVE:
			break;
		case PAN:
			break;
		case PICK:
			break;
		case RESIZE:
			break;
		case SELECT:
			return new SelectActionFactory();
		case ZOOMIN:
			break;
		case ZOOMOUT:
			break;
		default:
			break;

		}

		return null;

	}

}
