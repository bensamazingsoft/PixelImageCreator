
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class ActionChangeLayerVisibility implements IAction, ICancelable {

	private PixImage image;

	private ALayer	layer;
	private boolean	state	= layer.isVisible();

	public ActionChangeLayerVisibility(PixImage image, ALayer layer) {

		super();
		this.image = image;
		this.layer = layer;

	}

	@Override
	public void execute() throws Exception {

		if (GuiFacade.getInstance().getActiveimage() == image) {
			if (image.getLayerList().getItems().contains(layer)) {
				layer.setVisible(!state);
			}
		}

	}

	@Override
	public void cancel() throws Exception {

		if (GuiFacade.getInstance().getActiveimage() == image) {
			if (image.getLayerList().getItems().contains(layer)) {
				layer.setVisible(state);
			}
		}

	}

}
