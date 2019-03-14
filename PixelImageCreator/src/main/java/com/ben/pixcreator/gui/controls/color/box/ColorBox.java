
package com.ben.pixcreator.gui.controls.color.box;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.controls.color.roster.ColorRoster;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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

	@FXML
	Button closeBt;

	private SimpleBooleanProperty				selected	= new SimpleBooleanProperty();
	private SimpleObjectProperty<ToggleGroup>	toggleGroup	= new SimpleObjectProperty<>();
	private SimpleObjectProperty<Color>			color		= new SimpleObjectProperty<>();

	private ColorRoster roster;

	@FXML
	private HBox topContainer;

	@FXML
	private StackPane topStack;

	public ColorBox(SimpleObjectProperty<Color> color, ColorRoster roster) {
		super();
		this.roster = roster;
		this.color = color;
		getStylesheets().add("/styles/styles.css");
		getStyleClass().add("colorbox");
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

		HBox.setHgrow(topStack, Priority.ALWAYS);

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
				setStyle("-fx-background-" + AppContext.getInstance().propertyContext().get("selectColor2"));
			}
		});

	}

	@FXML
	public void close(ActionEvent event) {
		roster.remove(this);
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

	public final SimpleObjectProperty<Color> colorProperty() {

		return this.color;
	}

	public final Color getColor() {

		return this.colorProperty().get();
	}

	public final void setColor(final Color color) {

		this.colorProperty().set(color);
	}

}
