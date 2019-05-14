
package com.ben.pixcreator.application.action.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger log = LoggerFactory.getLogger(OpenNewImageAction.class);

	@Override
	public void execute() throws Exception {

		try {
			PixImageCreator creator = new PixImageCreator();
			PixImage newImage = creator.get();

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

			log.debug("new image opened");

		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

}
