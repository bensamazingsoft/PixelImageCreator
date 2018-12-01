package com.ben.pixcreator.gui.pane.tabpane;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.pane.tabpane.FXMLLoader;
import com.ben.pixcreator.gui.pane.tabpane.IOException;
import com.ben.pixcreator.gui.pane.tabpane.Initializable;
import com.ben.pixcreator.gui.pane.tabpane.ResourceBundle;
import com.ben.pixcreator.gui.pane.tabpane.TabPane;
import com.ben.pixcreator.gui.pane.tabpane.URL;



public class PixTab extends Tab implements Initializable
{

      private final String     IMAGEPATH	    = "images/gui/buttons/tab/";

      private final PixImage image;
      
      
      @FXML
      private ScrollPane scrollPane;
      @FXML
      private Canvas canvas;

      public PixTabPane(PixImage image)
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
      public void handleOnClose(Event event){
    	  //onClose
    	  onClose();
      }
      
      
      private void onClose() {
		// TODO implement onClose
		
	}




	public void initialize(URL arg0, ResourceBundle arg1)
      {

	    // TODO initialize
    	  this.setText(image.getName());
    	  
    	  canvas = new Canvas(image.getxSize(),image.getySize());
   
    	  scrollPane.setContent(canvas);
	    

      }




	public PixImage getImage() {
		return image;
	}


	public Canvas getCanvas() {
		return canvas;
	}
      
}