package com.ben.pixcreator.gui.controls.color.box;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * has a color builder method
 *
 */
public class ColorBox extends HBox implements Toggle, Initializable {

	@FXML
	ColorPicker colorPicker;

	private SimpleBooleanProperty				selected	= new SimpleBooleanProperty();
	private SimpleObjectProperty<ToggleGroup>	toggleGroup	= new SimpleObjectProperty<>();
	private SimpleObjectProperty<Color>			color		= new SimpleObjectProperty<>();

	public ColorBox() throws IOException {

		super();

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ColorBox.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	// TODO the color builder and listen to user change (bind the color prop)

	public void setColor(Color color) {
		setColor(color);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		this.color.addListener((obs, oldVal, newVal) -> {
			colorPicker.setValue(newVal);
		});

	}

	public SimpleObjectProperty<Color> colorProperty() {
		return color;
	}

	@Override
	public ToggleGroup getToggleGroup() {

		return toggleGroup.get();
	}

	@Override
	public boolean isSelected() {

		return selected.get();
	}

	@Override
	public BooleanProperty selectedProperty() {

		return selected;
	}

	@Override
	public void setSelected(boolean arg0) {
		selected.set(arg0);

	}

	@Override
	public void setToggleGroup(ToggleGroup arg0) {
		toggleGroup.set(arg0);

	}

	@Override
	public ObjectProperty<ToggleGroup> toggleGroupProperty() {

		return toggleGroup;
	}

}
