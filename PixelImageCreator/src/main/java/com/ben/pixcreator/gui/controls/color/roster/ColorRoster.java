
package com.ben.pixcreator.gui.controls.color.roster;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.context.PropertiesContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.color.box.ColorBox;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ColorRoster extends HBox implements Initializable {

	private static final Logger log = LoggerFactory.getLogger(ColorRoster.class);

	private ToggleGroup toggleGroup = new ToggleGroup();

	private Set<ColorBox>					colorBoxes	= new HashSet<>();
	private SimpleObjectProperty<PixImage>	imageProp;

	/**
	 * encapsulates a PixImage color roster.
	 */
	public ColorRoster() {

		super();

		imageProp = new SimpleObjectProperty<>();
		imageProp.addListener((obs, oldVal, newVal) -> {
			reload(newVal);
		});

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

		GuiFacade.getInstance().setColorRoster(this);

	}

	/**
	 * reloads the image colors.
	 * 
	 * @param image
	 */
	public void reload(PixImage image) {
		colorBoxes = makeColorBoxes(image);
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

		this.getChildren().add(plusButton());
	}

	private Button plusButton() {

		Button butt = new Button("+");
		butt.setId("rosterPlusButton");
		butt.setOnAction(Event -> {

			if (null != imageProp.get()) {
				SimpleObjectProperty<Color> prop = new SimpleObjectProperty<Color>();
				prop.set(Color.BLACK);
				GuiFacade.getInstance().getImagesColors().get(getImage()).add(prop);

				addColorToRoster(prop);
			}
		});
		return butt;
	}

	private void addColorToRoster(SimpleObjectProperty<Color> prop) {

		colorBoxes = makeColorBoxes(getImage());

		populate();

		selectColor(prop.get());

	}

	/**
	 * selects the colorbox holding the color property.
	 * 
	 * @param prop
	 */
	public void selectColor(Color color) {
		toggleGroup.selectToggle(colorBoxOfColor(color));
	}

	private Toggle colorBoxOfColor(Color color) {

		for (ColorBox box : colorBoxes) {

			if (box.getColor().equals(color)) {
				return box;
			}

		}

		return null;
	}

	private Set<ColorBox> makeColorBoxes(PixImage image) {

		Set<ColorBox> tempBoxes = new HashSet<>();
		Set<SimpleObjectProperty<Color>> colorProps = GuiFacade.getInstance().getImagesColors().get(image);

		for (SimpleObjectProperty<Color> prop : colorProps) {

			ColorBox box = new ColorBox(prop, this).color(prop.get());
			tempBoxes.add(box);
			box.setToggleGroup(toggleGroup);

			// prop.bindBidirectional(box.colorProperty());

		}

		return tempBoxes;

	}

	private Set<ColorBox> makeDefaultColorBoxes(PropertiesContext propertyContext) {

		Set<ColorBox> tempBoxes = new HashSet<>();
		for (Color col : propertyContext.getStartRosterColors()) {

			ColorBox box = new ColorBox(new SimpleObjectProperty<Color>(col), this);
			box.setToggleGroup(toggleGroup);
			tempBoxes.add(box);

		}
		return tempBoxes;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setSpacing(5.0);

	}

	public ToggleGroup getToggleGroup() {

		return toggleGroup;
	}

	public void setToggleGroup(ToggleGroup toggleGroup) {

		this.toggleGroup = toggleGroup;
	}

	public final SimpleObjectProperty<PixImage> imageProperty() {

		return this.imageProp;
	}

	public final PixImage getImage() {

		return this.imageProperty().get();
	}

	public final void setImage(final PixImage image) {

		this.imageProperty().set(image);
	}

	public void remove(ColorBox colorBox) {

		final Set<SimpleObjectProperty<Color>> imageColors = GuiFacade.getInstance().getImagesColors().get(getImage());
		final SimpleObjectProperty<Color> colorProperty = colorBox.colorProperty();

		for (SimpleObjectProperty<Color> prop : imageColors) {

			log.debug("prop.equals(colorProperty) : " + prop.equals(colorProperty));

		}
		log.debug("imageColors.remove(colorProperty) " + imageColors.remove(colorProperty));

		reload(imageProp.get());

	}

}
