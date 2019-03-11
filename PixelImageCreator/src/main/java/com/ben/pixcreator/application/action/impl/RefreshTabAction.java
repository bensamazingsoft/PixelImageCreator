
package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.draw.factory.DrawImageFactory;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.canvas.Canvas;

public class RefreshTabAction implements IAction {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(RefreshTabAction.class);

	private final PixImage	image;
	private final Canvas	canvas;

	public RefreshTabAction(PixTab pxTab) {

		canvas = pxTab.getCanvas();
		image = pxTab.getImage();
	}

	@Override
	public void execute() throws Exception {

		// log.debug("Refresh tab : " + image.toString());
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		DrawImageFactory.getDrawImage(image).draw(canvas);

		for (ALayer layer : image.getLayerList().getAllItems()) {

			GuiFacade.getInstance().getMiniatureManager().update(layer);

		}

	}

}
