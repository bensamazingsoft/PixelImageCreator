package com.ben.pixcreator.gui.controls.color.roster;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

import com.ben.pixcreator.application.context.AppContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ColorRoster extends HBox {

	private ToggleGroup toggleGroup;

	public ColorRoster() {

		super();

		// TODO get the colors in properties

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ColorRoster.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		populate();

	}

	private void populate() {
		// TODO add the colorBoxes (and in the togglegroup)

		Set<Color> colors = AppContext.getInstance().getProperties().getStartRosterColors();

	}

	public Set<Color> getColors() {
		// TODO return roster colors
	}

	public ToggleGroup getToggleGroup() {
		return toggleGroup;
	}

	public void setToggleGroup(ToggleGroup toggleGroup) {
		this.toggleGroup = toggleGroup;
	}

}
