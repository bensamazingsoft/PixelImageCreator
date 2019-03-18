
package com.ben.pixcreator.gui.pane.tabpane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.action.impl.OpenNewImageAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PixTabPane extends TabPane implements Initializable
{

      // private final String IMAGEPATH = "images/gui/buttons/tabpane/";

      public PixTabPane()
      {

	    super();
	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixTabPane.fxml"), bundle);
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try
	    {
		  fxmlLoader.load();
	    }
	    catch (IOException e)
	    {
		  throw new RuntimeException(e);
	    }

	    GuiFacade.getInstance().setPixTabPane(this);

	    getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {

		  if (newVal instanceof Tab)
		  {
			PixTab tab = (PixTab) newVal;
			PixImage image = tab.getImage();
			GuiFacade.getInstance().setActiveImage(image);
		  }

		  else if (null == newVal)
		  {
			try
			{
			      Executor.getInstance().executeAction(new OpenNewImageAction());
			}
			catch (Exception e)
			{
			      new ExceptionPopUp(e);
			}
		  }

	    });

      }


      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    populate();

	    GuiFacade.getInstance().setPixTabPane(this);

	    focusedProperty().addListener((obs, oldVal, newVal) -> {
		  if (!newVal)
		  {
			requestFocus();
		  }
	    });

      }


      private void populate()
      {

      }

}