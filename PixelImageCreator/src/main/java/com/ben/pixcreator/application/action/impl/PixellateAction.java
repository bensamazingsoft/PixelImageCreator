package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;

public class PixellateAction implements IAction {

	PixImage image;

	public PixellateAction(PixImage activeImage) {
		this.image = activeImage;
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub

	}

}
