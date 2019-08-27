
package com.ben.pixcreator.gui.controls.color.box;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
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
public class ColorBox extends VBox implements Toggle, Initializable, Comparable<ColorBox> {

	private final static Comparator<ColorBox> COMPARATOR = Comparator.comparingInt(ColorBox::getLuma)
			.thenComparingDouble(box -> box.getColor().getRed())
			.thenComparingDouble(box -> box.getColor().getBlue())
			.thenComparingDouble(box -> box.getColor().getGreen());

	@FXML
	ColorPicker colorPicker;

	@FXML
	Label label;

	@FXML
	Button closeBt;

	private SimpleBooleanProperty				selected	= new SimpleBooleanProperty();
	private SimpleObjectProperty<ToggleGroup>	toggleGroup	= new SimpleObjectProperty<>();
	private SimpleObjectProperty<Color>			pickedColor	= new SimpleObjectProperty<>();

	private ColorRoster roster;

	@FXML
	private HBox topContainer;

	@FXML
	private StackPane topStack;

	public ColorBox(SimpleObjectProperty<Color> color, ColorRoster roster) {

		super();
		this.roster = roster;
		this.pickedColor = color;
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

	public ColorBox color(Color color) {

		setColor(color);

		return this;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle bundle) {

		HBox.setHgrow(topStack, Priority.ALWAYS);

		setAlignment(Pos.CENTER);

		setLabelTextToColorToString();

		label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			toggleGroup.get().selectToggle(this);
		});

		colorPicker.valueProperty().bindBidirectional(pickedColor);

		pickedColor.addListener((obs, oldVal, newColor) -> {
			if (null != toggleGroup.get() && toggleGroup.get().getSelectedToggle() == this) {
				GuiFacade.getInstance().setActiveColor(newColor);
			}

			toggleGroup.get().selectToggle(this);

			setLabelTextToColorToString();
		});

		selected.addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				setStyle("-fx-background-color:" + AppContext.getInstance().propertyContext().get("selectColor1"));
			} else {
				setStyle("-fx-background-" + AppContext.getInstance().propertyContext().get("selectColor2"));
			}
		});

	}

	private void setLabelTextToColorToString() {

		label.setText(pickedColor.get().toString());

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

		return this.pickedColor;
	}

	public final Color getColor() {

		return this.colorProperty().get();
	}

	public final void setColor(final Color color) {

		this.colorProperty().set(color);
	}

	private int getLuma() {

		return (int) (this.getColor().getBrightness() * 255);

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((pickedColor == null) ? 0 : pickedColor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorBox other = (ColorBox) obj;
		if (pickedColor.get() == null) {
			if (other.pickedColor != null)
				return false;
		} else if (!pickedColor.equals(other.pickedColor))
			return false;
		return true;
	}

	@Override
	public int compareTo(ColorBox o) {

		return COMPARATOR.compare(this, o);
	}

	@Override
	public String toString() {

		return "ColorBox [color=" + pickedColor.get() + "]";
	}

}
