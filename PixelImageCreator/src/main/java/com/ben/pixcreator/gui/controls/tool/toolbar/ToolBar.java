
package com.ben.pixcreator.gui.controls.tool.toolbar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ToolBar extends HBox implements Initializable
{
	@FXML
    private ToggleGroup toggleGroup;

      @FXML
     private ToggleButton	  selectBut;

      // TODO get images
      final Image	  selectButSelected   = new Image("");
      final Image	  selectButUnSelected = new Image("");
      final ImageView	  selectButImg	      = new ImageView();


      public ToolBar()
      {

	    toggleGroup = new ToggleGroup();

	    ResourceBundle bundle = ResourceBundle.getBundle("trad.properties");
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
			"ToolBar.fxml"), bundle);
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
      }


      @FXML
      private void toggleSelect(ActionEvent event)
      {
   	  
    	  handleToggle();
      }


      private void handleToggle()
      {
	    // TODO handle tool toggle action with gui facade 
    	 GuiFacade guiFacade = GuiFacade.getInstance();
    	 guiFacade.toggleToolTo(toggleGroup.getSelectedToggle().getUserData());

      }


      @Override
      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    selectBut.setGraphic(selectButImg);
	    selectBut.setUserData(Tool.SELECT);
	    selectButImg.imageProperty().bind(Bindings.when(selectBut.selectedProperty()).then(selectButSelected).otherwise(selectButUnSelected));
	

      }


	public ToggleGroup getToggleGroup() {
		return toggleGroup;
	}


	public void setToggleGroup(ToggleGroup toggleGroup) {
		this.toggleGroup = toggleGroup;
	}

}
