
package com.ben.pixcreator.gui.controls.tab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.action.factory.ActionFactoryProducer;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

public class PixTab extends Tab implements Initializable {

	private final String IMAGEPATH = "images/gui/buttons/tab/";

	private SimpleObjectProperty<PixImage> image = new SimpleObjectProperty<PixImage>();

	@FXML
	private ScrollPane	scrollPane;
	@FXML
	private Canvas		canvas;

	public PixTab(PixImage image) {

		super();

		setImage(image);

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixTab.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@FXML
	public void handleClose(Event event) {

		// onClose
		onClose();
	}

	private void onClose() {

		GuiFacade.getInstance().getImagesColors().remove(getUserData());
		Executor.getInstance().getHistoryMap().remove(getUserData());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO initialize
		this.setText(getImage().getName());
		this.setUserData(canvas);

		canvas = new Canvas(getImage().getxSize(), getImage().getySize());

		scrollPane.setContent(canvas);

		canvas.addEventHandler(MouseEvent.ANY, event -> {

			IActionFactory factory = ActionFactoryProducer.getActionFactory(AppContext.getInstance().getCurrTool());

			try {
				Executor.getInstance().executeAction(factory.getAction(event));
				Executor.getInstance().executeAction(new RefreshTabAction(this));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});

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