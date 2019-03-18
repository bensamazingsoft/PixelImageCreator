
package com.ben.pixcreator.gui.mainapp;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.LoadRecentFilesAction;
import com.ben.pixcreator.application.action.impl.OpenNewImageAction;
import com.ben.pixcreator.application.action.impl.SaveRecentFilesAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.keyhandler.KeyHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainApp extends Application
{

      @SuppressWarnings("unused")
      private static final Logger log = LoggerFactory.getLogger(MainApp.class);


      public static void main(String[] args) throws Exception
      {

	    launch(args);
      }


      public void start(Stage stage) throws Exception
      {

	    AppContext.init();

	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    String fxmlFile = "/fxml/main.fxml";
	    // log.debug("Loading FXML for main view from: {}", fxmlFile);
	    FXMLLoader loader = new FXMLLoader();
	    Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

	    // log.debug("Showing JFX scene");
	    Scene scene = new Scene(rootNode, 1200, 800);
	    scene.getStylesheets().add("/styles/styles.css");
	    scene.addEventFilter(KeyEvent.ANY, new KeyHandler());

	    stage.setOnCloseRequest((Event) -> handleClose());
	    stage.setTitle(bundle.getString("appTitle"));
	    stage.setScene(scene);
	    stage.show();

	    GuiFacade.getInstance().setPanMode(AppContext.getInstance().getCurrTool() == PixTool.PAN);

	    Executor.getInstance().executeAction(new OpenNewImageAction());
	    Executor.getInstance().executeAction(new LoadRecentFilesAction());

      }


      private void handleClose()
      {

	    try
	    {

		  // if (AppContext.getInstance().getCurrTool() == PixTool.PAN)
		  // {
		  // AppContext.getInstance().setCurrTool(PixTool.DRAW);
		  // }

		  AppContext.getInstance().propertyContext().save();

		  Executor.getInstance().executeAction(new SaveRecentFilesAction());

	    }
	    catch (Exception e)
	    {
		  new ExceptionPopUp(e);
	    }
      }
}
