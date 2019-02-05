
package com.ben.pixcreator.gui.controls.color.box;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * has a color builder method
 *
 */
public class ColorBox extends VBox implements Toggle, Initializable {

	@FXML
	ColorPicker colorPicker;

	@FXML
	Label label;

	private SimpleBooleanProperty				selected	= new SimpleBooleanProperty();
	private SimpleObjectProperty<ToggleGroup>	toggleGroup	= new SimpleObjectProperty<>();
	private ObjectProperty<Color>				color		= new SimpleObjectProperty<>();

	public ColorBox() {

		super();
		getStylesheets().add("/styles/styles.css");
		getStyleClass().add("color"
				+ "box");
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

	// builder
	public ColorBox color(Color color) {

		setColor(color);

		return this;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle bundle) {

		setAlignment(Pos.CENTER);

		label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			toggleGroup.get().selectToggle(this);
		});

		colorPicker.valueProperty().bindBidirectional(color);

		// added so activecolor is changed while this is selected and
		// colorpicker color is changed
		color.addListener((obs, oldVal, newVal) -> {
			if (null != toggleGroup.get() && toggleGroup.get().getSelectedToggle() == this) {
				GuiFacade.getInstance().setActiveColor(newVal);
			}
		});

		selected.addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				setStyle("-fx-background-color:" + AppContext.getInstance().propertyContext().get("selectColor1"));
			} else {
				setStyle("-fx-background-color:gray");
			}
		});

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
		toggleGroup.get().getToggles().add(this);
		toggleGroup.get().selectToggle(this);

	}

	@Override
	public ObjectProperty<ToggleGroup> toggleGroupProperty() {

		return toggleGroup;
	}

	public final ObjectProperty<Color> colorProperty() {

		return this.color;
	}

	public final Color getColor() {

		return this.colorProperty().get();
	}

	public final void setColor(final Color color) {

		this.colorProperty().set(color);
	}

	@Override
	public Object getUserData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserData(Object arg0) {
		// TODO Auto-generated method stub

	}

}
