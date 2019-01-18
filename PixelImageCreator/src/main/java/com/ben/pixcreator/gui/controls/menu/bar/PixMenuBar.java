
package com.ben.pixcreator.gui.controls.menu.bar;

import java.io.IOException;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.action.impl.OpenTabAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;

public class PixMenuBar extends MenuBar {

	public PixMenuBar() {

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixMenuBar.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@FXML
	private void newAction(final ActionEvent event) {

		handleNewAction();
	}

	private void handleNewAction() {
		try {
			PixImage newImage = new PixImage();
			Executor.getInstance().executeAction(new OpenTabAction(newImage));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	private void openAction(final ActionEvent event) {

		handleOpenAction();
	}

	private void handleOpenAction() {
		// TODO handle Open action

	}

}
