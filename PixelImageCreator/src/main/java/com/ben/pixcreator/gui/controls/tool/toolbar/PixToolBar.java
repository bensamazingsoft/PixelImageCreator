
package com.ben.pixcreator.gui.controls.tool.toolbar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.tools.PixTool;
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

public class PixToolBar extends HBox implements Initializable
{

      @FXML
      private ToggleGroup  toggleGroup;
      @FXML
      private ToggleButton selectBut;
      @FXML
      private ToggleButton drawBut;
      @FXML
      private ToggleButton pickBut;
      @FXML
      private ToggleButton moveBut;
      @FXML
      private ToggleButton panBut;
      @FXML
      private ToggleButton resizeBut;
      @FXML
      private ToggleButton zoomInBut;
      @FXML
      private ToggleButton zoomOutBut;

      // TODO get images
      final Image	   selectButSelected	= new Image("");
      final Image	   selectButUnSelected	= new Image("");
      final ImageView	   selectButImg		= new ImageView();

      final Image	   drawButSelected	= new Image("");
      final Image	   drawButUnSelected	= new Image("");
      final ImageView	   drawButImg		= new ImageView();

      final Image	   pickButSelected	= new Image("");
      final Image	   pickButUnSelected	= new Image("");
      final ImageView	   pickButImg		= new ImageView();

      final Image	   moveButSelected	= new Image("");
      final Image	   moveButUnSelected	= new Image("");
      final ImageView	   moveButImg		= new ImageView();

      final Image	   panButSelected	= new Image("");
      final Image	   panButUnSelected	= new Image("");
      final ImageView	   panButImg		= new ImageView();

      final Image	   resizeButSelected	= new Image("");
      final Image	   resizeButUnSelected	= new Image("");
      final ImageView	   resizeButImg		= new ImageView();

      final Image	   zoomInButSelected	= new Image("");
      final Image	   zoomInButUnSelected	= new Image("");
      final ImageView	   zoomInButImg		= new ImageView();

      final Image	   zoomOutButSelected	= new Image("");
      final Image	   zoomOutButUnSelected	= new Image("");
      final ImageView	   zoomOutButImg	= new ImageView();


      public PixToolBar()
      {

	    toggleGroup = new ToggleGroup();

	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixToolBar.fxml"), bundle);
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


      @FXML
      private void toggleDraw(ActionEvent event)
      {

	    handleToggle();
      }


      @FXML
      private void togglePick(ActionEvent event)
      {

	    handleToggle();
      }


      @FXML
      private void toggleMove(ActionEvent event)
      {

	    handleToggle();
      }


      @FXML
      private void togglePan(ActionEvent event)
      {

	    handleToggle();
      }


      @FXML
      private void toggleResize(ActionEvent event)
      {

	    handleToggle();
      }


      @FXML
      private void toggleZoomIn(ActionEvent event)
      {

	    handleToggle();
      }


      @FXML
      private void toggleZoomOut(ActionEvent event)
      {

	    handleToggle();
      }


      private void handleToggle()
      {

	    // handle tool toggle action with gui facade
	    GuiFacade guiFacade = GuiFacade.getInstance();
	    try
	    {
		  guiFacade.toggleToolTo((PixTool) toggleGroup.getSelectedToggle().getUserData());
	    }
	    catch (IOException e)
	    {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	    }

      }


      @Override
      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    selectBut.setGraphic(selectButImg);
	    selectBut.setUserData(PixTool.SELECT);
	    selectButImg.imageProperty().bind(Bindings.when(selectBut.selectedProperty()).then(selectButSelected).otherwise(selectButUnSelected));

	    drawBut.setGraphic(drawButImg);
	    drawBut.setUserData(PixTool.DRAW);
	    drawButImg.imageProperty().bind(Bindings.when(drawBut.selectedProperty()).then(drawButSelected).otherwise(drawButUnSelected));

	    pickBut.setGraphic(pickButImg);
	    pickBut.setUserData(PixTool.PICK);
	    pickButImg.imageProperty().bind(Bindings.when(pickBut.selectedProperty()).then(pickButSelected).otherwise(pickButUnSelected));

	    moveBut.setGraphic(selectButImg);
	    moveBut.setUserData(PixTool.MOVE);
	    moveButImg.imageProperty().bind(Bindings.when(moveBut.selectedProperty()).then(moveButSelected).otherwise(moveButUnSelected));

	    panBut.setGraphic(selectButImg);
	    panBut.setUserData(PixTool.PAN);
	    panButImg.imageProperty().bind(Bindings.when(panBut.selectedProperty()).then(panButSelected).otherwise(panButUnSelected));

	    resizeBut.setGraphic(selectButImg);
	    resizeBut.setUserData(PixTool.RESIZE);
	    resizeButImg.imageProperty().bind(Bindings.when(resizeBut.selectedProperty()).then(resizeButSelected).otherwise(resizeButUnSelected));

	    zoomInBut.setGraphic(selectButImg);
	    zoomInBut.setUserData(PixTool.ZOOMIN);
	    zoomInButImg.imageProperty().bind(Bindings.when(zoomInBut.selectedProperty()).then(zoomInButSelected).otherwise(zoomInButUnSelected));

	    zoomOutBut.setGraphic(selectButImg);
	    zoomOutBut.setUserData(PixTool.ZOOMOUT);
	    zoomOutButImg.imageProperty().bind(Bindings.when(zoomOutBut.selectedProperty()).then(zoomOutButSelected).otherwise(zoomOutButUnSelected));
      }


      public ToggleGroup getToggleGroup()
      {

	    return toggleGroup;
      }


      public void setToggleGroup(ToggleGroup toggleGroup)
      {

	    this.toggleGroup = toggleGroup;
      }

}
