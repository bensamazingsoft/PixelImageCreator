package com.ben.pixcreator.gui.controls.infoline;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Infoline extends HBox implements Initializable {

	@FXML
	Label txtContent;

	public Infoline() {
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Infoline.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		txtContent.textProperty().bindBidirectional(GuiFacade.getInstance().getInfolineTxtProperty());
		txtContent.setText("Bienvenue dans PixelCreator");
	}

}
