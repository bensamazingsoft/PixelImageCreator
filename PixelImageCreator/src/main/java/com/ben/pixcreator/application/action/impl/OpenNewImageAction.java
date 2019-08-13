
package com.ben.pixcreator.application.action.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.creator.PixImageCreator;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class OpenNewImageAction implements IAction {

	@Override
	public void execute() throws Exception {

		try {
			PixImageCreator creator = new PixImageCreator();
			PixImage newImage = creator.getNewImage();

			Set<SimpleObjectProperty<Color>> colorProps = AppContext.getInstance().propertyContext()
					.getStartRosterColors().stream()
					.map(color -> {
						SimpleObjectProperty<Color> prop = new SimpleObjectProperty<Color>();
						prop.set(color);
						return prop;
					})
					.collect(Collectors.toSet());

			GuiFacade.getInstance().getImagesColors().put(newImage, colorProps);

			Executor.getInstance().executeAction(new OpenTabAction(newImage));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

}
