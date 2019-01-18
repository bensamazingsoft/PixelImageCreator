package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class OpenTabAction implements IAction {

	private final PixImage image;

	public OpenTabAction(PixImage image) {
		this.image = image;
	}

	@Override
	public void execute() throws Exception {

		GuiFacade.getInstance().getPixTabPane().getTabs().add(new PixTab(image));

	}

	@Override
	public void cancel() throws Exception {
		// TODO Auto-generated method stub

	}

}
