
package com.ben.pixcreator.gui.controls.menu.bar;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.ActionUpdateSelection;
import com.ben.pixcreator.application.action.impl.OpenTabAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.action.impl.SaveAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PixMenuBar extends MenuBar {

	private static final Logger log = LoggerFactory.getLogger(PixMenuBar.class);

	public PixMenuBar() {

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixMenuBar.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@FXML
	private void newAction(final ActionEvent event) {

		handleNewAction();
	}

	private void handleNewAction() {

		try {
			PixImage newImage = new PixImage();

			Set<SimpleObjectProperty<Color>> colorProps = AppContext.getInstance().propertyContext()
					.getStartRosterColors().stream()
					.map(color -> {
						SimpleObjectProperty<Color> prop = new SimpleObjectProperty<Color>();
						prop.set(color);
						return prop;
					})
					.collect(Collectors.toSet());

			GuiFacade.getInstance().getImagesColors().put(newImage, colorProps);

			Executor.getInstance().executeAction(new OpenTabAction(newImage));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	private void openAction(final ActionEvent event) {

		handleOpenAction();
	}

	private void handleOpenAction() {
		// TODO handle Open action

	}

	@FXML
	private void saveAction(ActionEvent event) {
		handleSaveAction();
	}

	private void handleSaveAction() {
		PixImage image = GuiFacade.getInstance().getActiveimage();

		if (!AppContext.getInstance().getFiles().containsKey(image)) {
			handleSaveAsAction();
		} else {
			try {
				Executor.getInstance().executeAction(new SaveAction(image));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		}

	}

	@FXML
	private void saveAsAction(ActionEvent event) {
		handleSaveAsAction();
	}

	private void handleSaveAsAction() {

		PixImage image = GuiFacade.getInstance().getActiveimage();

		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("pix", "*.pix"));

		File file = fc.showSaveDialog(null);
		log.debug("saving image to : " + file.toString());
		if (null != file) {
			AppContext.getInstance().getFiles().put(image, file);
			handleSaveAction();
		}

	}

	@FXML
	public void cancel(final ActionEvent event) {
		handleCancel();
	}

	private void handleCancel() {
		try {
			Executor.getInstance().cancel();
			Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	public void redo(final ActionEvent event) {
		handleRedo();
	}

	private void handleRedo() {
		try {
			Executor.getInstance().redo();
			Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	public void unSelect(ActionEvent event) {
		handleUnSelect();
	}

	private void handleUnSelect() {

		PixImage activeImage = GuiFacade.getInstance().getActiveimage();
		GuiFacade.getInstance().getSelections().remove(activeImage);
		PixTab tab = GuiFacade.getInstance().getActiveTab();

		try {
			Executor.getInstance().executeAction(new ActionUpdateSelection(activeImage));
			Executor.getInstance().executeAction(new RefreshTabAction(tab));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

}
