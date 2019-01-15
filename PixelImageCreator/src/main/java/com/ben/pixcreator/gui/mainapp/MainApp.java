
package com.ben.pixcreator.gui.mainapp;

import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static final Logger log = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) throws Exception {

		launch(args);
	}

	public void start(Stage stage) throws Exception {

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		String fxmlFile = "/fxml/main.fxml";
		log.debug("Loading FXML for main view from: {}", fxmlFile);
		FXMLLoader loader = new FXMLLoader();
		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

		log.debug("Showing JFX scene");
		Scene scene = new Scene(rootNode, 400, 200);
		scene.getStylesheets().add("/styles/styles.css");

		stage.setOnCloseRequest((Event) -> handleClose());
		stage.setTitle(bundle.getString("appTitle"));
		stage.setScene(scene);
		stage.show();
	}

	private void handleClose() {
		try {
			AppContext.getInstance().getProperties().save();
		} catch (IOException e) {
			new ExceptionPopUp(e);
		}
	}
}
