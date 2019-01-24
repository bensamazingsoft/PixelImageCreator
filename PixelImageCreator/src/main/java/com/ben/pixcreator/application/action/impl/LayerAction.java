package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.controls.layer.panel.actions.LayerListAction;

public class LayerAction implements IAction {

	public LayerAction(ALayer layer, PixImage image, LayerListAction action) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws Exception {
		// TODO unlock layer if locked & delete layer from image layerList

	}

	@Override
	public void cancel() throws Exception {

	}

}
