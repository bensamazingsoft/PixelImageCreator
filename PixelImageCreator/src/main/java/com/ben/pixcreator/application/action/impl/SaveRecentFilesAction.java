package com.ben.pixcreator.application.action.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class SaveRecentFilesAction implements IAction {

	@Override
	public void execute() throws Exception {

		File file = new File("data/rctfl.inf");
		file.getParentFile().mkdirs();

		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);) {

			out.writeObject(GuiFacade.getInstance().getRecentFiles());
		}
	}

}
