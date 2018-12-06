
package com.ben.pixcreator.gui.mainapp;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application
{

	//TODO need to implement the onClose behaviour (save properties etc)
	
      private static final Logger log = LoggerFactory.getLogger(MainApp.class);


      public static void main(String[] args) throws Exception
      {

	    launch(args);
      }


      public void start(Stage stage) throws Exception
      {

	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    String fxmlFile = "/fxml/main.fxml";
	    log.debug("Loading FXML for main view from: {}", fxmlFile);
	    FXMLLoader loader = new FXMLLoader();
	    Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

	    log.debug("Showing JFX scene");
	    Scene scene = new Scene(rootNode, 400, 200);
	    scene.getStylesheets().add("/styles/styles.css");

	    stage.setTitle(bundle.getString("appTitle"));
	    stage.setScene(scene);
	    stage.show();
      }
}
