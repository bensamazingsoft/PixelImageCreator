package com.ben.pixcreator.gui.controls.menu.bar;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.tbee.javafx.scene.layoutilisateurfxml.MigPane;

public class PixMenuBar extends MigPane {

	public PixMenuBar() {

		ResourceBundle bundle = ResourceBundle.getBundle("trad.properties");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"PixMenuBar.fxml"),bundle);
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
		// TODO handle New action

	}

	@FXML
	private void openAction(final ActionEvent event) {
		handleOpenAction();
	}

	private void handleOpenAction() {
		// TODO handle Open action

	}

	@FXML
	private void handleKeyInput(InputEvent event) {
		if (event instanceof KeyEvent) {
			final KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.N) {
				handleNewAction();
			}
			if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.O) {
				handleOpenAction();
			}
		}
	}
}
