package com.ben.pixcreator.application.action.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class LoadSerializedRecentFilesAction implements IAction {

	private final GuiFacade gui = GuiFacade.getInstance();

	@Override
	public void execute() throws Exception {

		File serializedRecentFiles = new File("data/rctfl.inf");

		if (serializedRecentFiles.exists()) {
			loadfile(serializedRecentFiles);
			gui.updateRecentFilesMenu();
		}

	}

	private void loadfile(File file) {

		try (FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);) {

			@SuppressWarnings("unchecked")
			Pile<String> recentFiles = (Pile<String>) in.readObject();

			gui.setRecentFiles(recentFiles);

		} catch (FileNotFoundException e) {
			new ExceptionPopUp(e);
		} catch (IOException e) {
			new ExceptionPopUp(e);
		} catch (ClassNotFoundException e) {
			new ExceptionPopUp(e);
		}

	}

}
