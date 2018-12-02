
package com.ben.pixcreator.gui.controls.tab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.image.PixImage;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class PixTab extends Tab implements Initializable
{

      private final String   IMAGEPATH = "images/gui/buttons/tab/";

      private final PixImage image;

      @FXML
      private ScrollPane     scrollPane;
      @FXML
      private Canvas	     canvas;


      public PixTab(PixImage image)
      {

	    super();
	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixTab.fxml"), bundle);
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

	    this.image = image;

      }


      @FXML
      public void handleOnClose(Event event)
      {

	    // onClose
	    onClose();
      }


      private void onClose()
      {
	    // TODO implement onClose

      }


      @Override
      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    // TODO initialize
	    this.setText(image.getName());

	    canvas = new Canvas(image.getxSize(), image.getySize());

	    scrollPane.setContent(canvas);

      }


      public PixImage getImage()
      {

	    return image;
      }


      public Canvas getCanvas()
      {

	    return canvas;
      }

}