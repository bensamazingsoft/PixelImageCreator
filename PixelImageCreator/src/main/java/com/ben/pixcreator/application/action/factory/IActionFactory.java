
package com.ben.pixcreator.application.action.factory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public interface IActionFactory {

	public IAction getAction(Event event);

	default Coord eventCoord(MouseEvent event) {

		PixImage image = GuiFacade.getInstance().getActiveimage();

		double width = GuiFacade.getInstance().getActiveTab().getCanvas().getWidth();
		double height = GuiFacade.getInstance().getActiveTab().getCanvas().getHeight();

		int x = new Double(((MouseEvent) event).getX()).intValue();
		int y = new Double(((MouseEvent) event).getY()).intValue();
		int cellX = (int) Math.floor(x / (width / image.getxGridResolution()));
		int cellY = (int) Math.floor(y / (height / image.getyGridResolution()));

		return new Coord(cellX, cellY);
	}

}
