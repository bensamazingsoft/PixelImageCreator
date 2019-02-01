
package com.ben.pixcreator.gui.pane.tabpane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

public class PixTabPane extends TabPane implements Initializable {

	private final String IMAGEPATH = "images/gui/buttons/tabpane/";

	public PixTabPane() {

		super();
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixTabPane.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		GuiFacade.getInstance().setPixTabPane(this);

		getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			PixTab tab = (PixTab) newVal;
			PixImage image = tab.getImage();
			GuiFacade.getInstance().setActiveImage(image);

		});

	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO initialize
		populate();

	}

	private void populate() {

	}

}