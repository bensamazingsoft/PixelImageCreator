
package com.ben.pixcreator.gui.controls.color.roster;

import java.io.IOException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.context.PropertiesContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.color.box.ColorBox;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ColorRoster extends HBox {

	private ToggleGroup						toggleGroup;
	private Set<ColorBox>					colorBoxes	= new HashSet<>();
	private SimpleObjectProperty<PixImage>	image;

	public ColorRoster() {

		super();

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ColorRoster.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		image = new SimpleObjectProperty<>();
		image.addListener((obs, oldVal, newVal) -> {
			makeColorBoxes(newVal);
		});

		populate();

	}

	private void populate() {

		this.getChildren().clear();

		if (colorBoxes.isEmpty()) {
			colorBoxes = makeDefaultColorBoxes(AppContext.getInstance().propertyContext());
		}

		for (ColorBox box : colorBoxes) {

			this.getChildren().add(box);

		}

		Button butt = new Button("+");
		butt.setId("rosterPlusButton");
		butt.setOnAction(Event -> {
			SimpleObjectProperty<Color> prop = new SimpleObjectProperty<Color>();
			prop.set(Color.BLACK);
			GuiFacade.getInstance().getImagesColors().get(getImage()).add(prop);

			colorBoxes = makeColorBoxes(getImage());

			populate();
		});
		this.getChildren().add(butt);
	}

	private Set<ColorBox> makeColorBoxes(PixImage image) {

		Set<ColorBox> tempBoxes = new HashSet<>();
		Set<SimpleObjectProperty<Color>> colorProps = GuiFacade.getInstance().getImagesColors().get(image);

		for (SimpleObjectProperty<Color> prop : colorProps) {

			ColorBox box = new ColorBox().color(prop.get());
			tempBoxes.add(box);
			box.setToggleGroup(toggleGroup);

			prop.bindBidirectional(box.colorProperty());

		}

		return tempBoxes;

	}

	private Set<ColorBox> makeDefaultColorBoxes(PropertiesContext propertyContext) {
		Set<ColorBox> tempBoxes = new HashSet<>();
		for (String prop : propertyContext.getProps("color_")) {

			tempBoxes.add(new ColorBox().color(Color.valueOf(prop)));

		}
		return tempBoxes;
	}

	public ToggleGroup getToggleGroup() {

		return toggleGroup;
	}

	public void setToggleGroup(ToggleGroup toggleGroup) {

		this.toggleGroup = toggleGroup;
	}

	public final SimpleObjectProperty<PixImage> imageProperty() {

		return this.image;
	}

	public final PixImage getImage() {

		return this.imageProperty().get();
	}

	public final void setImage(final PixImage image) {

		this.imageProperty().set(image);
	}

}