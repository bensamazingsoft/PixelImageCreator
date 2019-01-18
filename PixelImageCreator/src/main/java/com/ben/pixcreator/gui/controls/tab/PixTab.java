
package com.ben.pixcreator.gui.controls.tab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.image.PixImage;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class PixTab extends Tab implements Initializable {

	private final String IMAGEPATH = "images/gui/buttons/tab/";

	private SimpleObjectProperty<PixImage> image;

	@FXML
	private ScrollPane	scrollPane;
	@FXML
	private Canvas		canvas;

	public PixTab(PixImage image) {

		super();
		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixTab.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		setImage(image);

	}

	@FXML
	public void handleOnClose(Event event) {

		// onClose
		onClose();
	}

	private void onClose() {
		// TODO implement onClose

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO initialize
		this.setText(getImage().getName());
		this.setUserData(canvas);

		canvas = new Canvas(getImage().getxSize(), getImage().getySize());

		scrollPane.setContent(canvas);

	}

	public Canvas getCanvas() {

		return canvas;
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