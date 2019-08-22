
package com.ben.pixcreator.gui.mainapp;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.LoadSerializedRecentFilesAction;
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

	    try
	    {
		  AppContext.getInstance().init();
	    }
	    catch (Exception e)
	    {
		  new ExceptionPopUp(e);
	    }

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

	    Executor.getInstance().executeAction(new LoadSerializedRecentFilesAction());
	    Executor.getInstance().executeAction(new OpenNewImageAction());

	    GuiFacade.getInstance().toggleToolTo(AppContext.getInstance().getCurrTool());
	    GuiFacade.getInstance().setPanMode(AppContext.getInstance().getCurrTool() == PixTool.PAN);

	    // start this thread in case Pick is the starting tool.
	    AppContext.getInstance().getSnapshotUpdater().start();

      }


      private void handleClose()
      {

	    try
	    {

		  final AppContext ctx = AppContext.getInstance();
		  ctx.propertyContext().save();
		  ctx.getCursorUpdater().setClose(true);
		  AppContext.getInstance().getSnapshotUpdater().setClose(true);

		  Executor.getInstance().executeAction(new SaveRecentFilesAction());

	    }
	    catch (Exception e)
	    {
		  new ExceptionPopUp(e);
	    }
      }
}
