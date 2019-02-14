package com.ben.pixcreator.application.action.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

public class SaveAction implements IAction {

	private final PixImage image;

	public SaveAction(PixImage image) {
		this.image = image;
	}

	@Override
	public void execute() throws Exception {

		File file = AppContext.getInstance().getFiles().get(image);

		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);) {

			out.writeObject(image);

		} catch (IOException e) {
			new ExceptionPopUp(e);
		}

	}

}
