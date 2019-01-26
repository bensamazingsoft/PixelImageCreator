
package com.ben.pixcreator.application.action.impl;

import java.io.IOException;

import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

public class ActionChangeLayerVisibility implements IAction, ICancelable {

	private PixImage image;

	private ALayer	layer;
	private boolean	state	= layer.isVisible();

	public ActionChangeLayerVisibility(PixImage image, ALayer layer)
			throws IOException, ClosedImageException, InexistantLayerException {

		super();
		this.image = image;
		this.layer = layer;

		if (!AppContext.getInstance().getOpenImages().contains(image)) {
			throw new ClosedImageException();
		} else if (!image.getLayerList().getItems().contains(layer)) {
			throw new InexistantLayerException();
		}

	}

	@Override
	public void execute() throws Exception {

		if (AppContext.getInstance().getOpenImages().contains(image)) {
			if (image.getLayerList().getItems().contains(layer)) {
				layer.setVisible(!state);
			} else {
				throw new InexistantLayerException();
			}
		} else {
			throw new ClosedImageException();
		}

	}

	@Override
	public void cancel() throws Exception {

		if (AppContext.getInstance().getOpenImages().contains(image)) {
			if (image.getLayerList().getItems().contains(layer)) {
				layer.setVisible(state);
			} else {
				throw new InexistantLayerException();
			}
		} else {
			throw new ClosedImageException();
		}

	}

}
