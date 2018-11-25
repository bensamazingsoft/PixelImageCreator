
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
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PixToolBar extends ToolBar implements Initializable
{

      private final String IMAGEPATH		= "images/gui/buttons/tools/";

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
      final Image	   selectButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "selectButSelected.png"));
      final Image	   selectButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "selectButUnSelected.png"));
      final ImageView	   selectButImg		= new ImageView();

      final Image	   drawButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "drawButSelected.png"));
      final Image	   drawButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "drawButUnSelected.png"));
      final ImageView	   drawButImg		= new ImageView();

      final Image	   pickButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "pickButSelected.png"));
      final Image	   pickButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "pickButUnSelected.png"));
      final ImageView	   pickButImg		= new ImageView();

      final Image	   moveButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveButSelected.png"));
      final Image	   moveButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveButUnSelected.png"));
      final ImageView	   moveButImg		= new ImageView();

      final Image	   panButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "panButSelected.png"));
      final Image	   panButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "panButUnSelected.png"));
      final ImageView	   panButImg		= new ImageView();

      final Image	   resizeButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "resizeButSelected.png"));
      final Image	   resizeButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "resizeButUnSelected.png"));
      final ImageView	   resizeButImg		= new ImageView();

      final Image	   zoomInButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "zoomInButSelected.png"));
      final Image	   zoomInButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "zoomInButUnSelected.png"));
      final ImageView	   zoomInButImg		= new ImageView();

      final Image	   zoomOutButSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "zoomOutButSelected.png"));
      final Image	   zoomOutButUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "zoomOutButUnSelected.png"));
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

	    moveBut.setGraphic(moveButImg);
	    moveBut.setUserData(PixTool.MOVE);
	    moveButImg.imageProperty().bind(Bindings.when(moveBut.selectedProperty()).then(moveButSelected).otherwise(moveButUnSelected));

	    panBut.setGraphic(panButImg);
	    panBut.setUserData(PixTool.PAN);
	    panButImg.imageProperty().bind(Bindings.when(panBut.selectedProperty()).then(panButSelected).otherwise(panButUnSelected));

	    resizeBut.setGraphic(resizeButImg);
	    resizeBut.setUserData(PixTool.RESIZE);
	    resizeButImg.imageProperty().bind(Bindings.when(resizeBut.selectedProperty()).then(resizeButSelected).otherwise(resizeButUnSelected));

	    zoomInBut.setGraphic(zoomInButImg);
	    zoomInBut.setUserData(PixTool.ZOOMIN);
	    zoomInButImg.imageProperty().bind(Bindings.when(zoomInBut.selectedProperty()).then(zoomInButSelected).otherwise(zoomInButUnSelected));

	    zoomOutBut.setGraphic(zoomOutButImg);
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
