package com.ben.pixcreator.application.action.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class LoadRecentFilesAction implements IAction {

	private static final Logger log = LoggerFactory.getLogger(LoadRecentFilesAction.class);

	@Override
	public void execute() throws Exception {

		File recentFiles = new File("data/rctfl.inf");

		if (recentFiles.exists()) {
			loadfile(recentFiles);
		}

		GuiFacade.getInstance().getPixMenuBar().loadRecentFiles();

	}

	private void loadfile(File file) {

		try (FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);) {

			@SuppressWarnings("unchecked")
			Pile<String> recentFiles = (Pile<String>) in.readObject();

			GuiFacade.getInstance().setRecentFiles(recentFiles);

			log.debug(recentFiles.getAllItems().size() + " recent files loaded");

		} catch (FileNotFoundException e) {
			new ExceptionPopUp(e);
		} catch (IOException e) {
			new ExceptionPopUp(e);
		} catch (ClassNotFoundException e) {
			new ExceptionPopUp(e);
		}

	}

}
