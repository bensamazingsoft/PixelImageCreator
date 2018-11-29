
package com.ben.pixcreator.gui.controls.layer.panel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.ILayer;
import com.ben.pixcreator.gui.controls.layer.box.LayerBox;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LayerPanel extends BorderPane implements Initializable
{

      private final String     IMAGEPATH	    = "images/gui/buttons/tools/";

      private final PixImage   image;



      // private Image moveLayerDownButImg,moveLayerUpButImg,deleteLayerButImg,duplicateLayerButImg,newLayerButImg;

      final Image	       moveLayerDownButImg  = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveLayerDownButImg.png"));
      final Image	       moveLayerUpButImg    = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveLayerUpButImg.png"));
      final Image	       deleteLayerButImg    = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "deleteLayerButImg.png"));
      final Image	       duplicateLayerButImg = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "duplicateLayerButImg.png"));
      final Image	       newLayerButImg	    = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "newLayerButImg.png"));

      @FXML
      private ToolBar	       toolBar;

      @FXML
      private VBox	       moveLayerButBox;

      @FXML
      private VBox	       layersBox;

      @FXML
      private Button	       deleteLayerBut;

      @FXML
      private Button	       duplicateLayerBut;

      @FXML
      private Button	       newLayerBut;

      @FXML
      private Button	       moveLayerUpBut;

      @FXML
      private Button	       moveLayerDownBut;

      @FXML
      private ToggleGroup      togglegroup;


      public LayerPanel()
      {

	    super();
	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LayerPanel.fxml"), bundle);
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

	    image = GuiFacade.getInstance().getActiveImage();

      }


      @FXML
      private void handleMoveLayerDown(MouseEvent event)
      {

	    // handleMoveLayerDown
	    moveLayerDown();
      }


      private void moveLayerDown()
      {
	    // TODO moveLayerDown

      }


      @FXML
      private void handleMoveLayerUp(MouseEvent event)
      {

	    // handleMoveLayerUp
	    moveLayerUp();
      }


      private void moveLayerUp()
      {
	    // TODO moveLayerUp

      }


      @FXML
      private void handleDuplicateLayer(MouseEvent event)
      {

	    // handleDuplicateLayer
	    duplicateLayer();
      }


      private void duplicateLayer()
      {
	    // TODO duplicateLayer

      }


      @FXML
      private void handleNewLayer(MouseEvent event)
      {

	    // handleNewLayer
	    newLayer();
      }


      private void newLayer()
      {
	    // TODO newLayer

      }


      @FXML
      private void handleDeleteLayer(MouseEvent event)
      {

	    // handleDeleteLayer
	    deleteLayer();
      }


      private void deleteLayer()
      {
	    // TODO deleteLayer

      }


      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    // TODO initialize
	    populate();
	    
	    
	    deleteLayerBut.setGraphic(new ImageView(deleteLayerButImg));

	    duplicateLayerBut.setGraphic(new ImageView(duplicateLayerButImg));

	    newLayerBut.setGraphic(new ImageView(newLayerButImg));

	    moveLayerUpBut.setGraphic(new ImageView(moveLayerUpButImg));

	    moveLayerDownBut.setGraphic(new ImageView(moveLayerDownButImg));
      }


      private void populate()
      {
	    // populate VBox with layerBoxes and toggleGroup

    	  togglegroup = new ToggleGroup();
    	  
	    if (layer != null) {
			for (int i = 0; i < image.getLayers().getIdx().size(); i++) {

				ILayer layer = image.getLayers().getLayerById(i);
				LayerBox box = new LayerBox(image, layer);

				layersBox.getChildren().add(box);

				box.setToggleGroup(togglegroup);

			}
		}

      }


	public ToggleGroup getTogglegroup() {
		return togglegroup;
	}


	public void setTogglegroup(ToggleGroup togglegroup) {
		this.togglegroup = togglegroup;
	}

}
