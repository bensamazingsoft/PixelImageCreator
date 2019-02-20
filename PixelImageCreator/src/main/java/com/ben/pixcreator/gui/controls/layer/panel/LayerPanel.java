
package com.ben.pixcreator.gui.controls.layer.panel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.action.impl.LayerAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.controls.layer.box.LayerBox;
import com.ben.pixcreator.gui.controls.layer.panel.actions.LayerActions;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LayerPanel extends BorderPane implements Initializable
{

      private final String		     IMAGEPATH		  = "images/gui/buttons/tools/";

      private SimpleObjectProperty<PixImage> image;
      private SimpleObjectProperty<ALayer>   activeLayer	  = new SimpleObjectProperty<>();

      final Image			     moveLayerDownButImg  = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveLayerDownButImg.png"));
      final Image			     moveLayerUpButImg	  = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveLayerUpButImg.png"));
      final Image			     deleteLayerButImg	  = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "deleteLayerButImg.png"));
      final Image			     duplicateLayerButImg = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "duplicateLayerButImg.png"));
      final Image			     newLayerButImg	  = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "newLayerButImg.png"));

      @FXML
      private ToolBar			     toolBar;

      @FXML
      private VBox			     moveLayerButBox;

      @FXML
      private VBox			     layersBox;

      @FXML
      private Button			     deleteLayerBut;

      @FXML
      private Button			     duplicateLayerBut;

      @FXML
      private Button			     newLayerBut;

      @FXML
      private Button			     moveLayerUpBut;

      @FXML
      private Button			     moveLayerDownBut;

      @FXML
      private ToggleGroup		     togglegroup;


      public LayerPanel()
      {

	    super();

	    getStylesheets().add("/styles/styles.css");
	    getStyleClass().add("layerpanel");

	    image = new SimpleObjectProperty<>();
	    image.addListener((obs, oldVal, newVal) -> populate());

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

	    GuiFacade.getInstance().setLayerPanel(this);

	    activeLayer.addListener((obs, oldVal, newVal) -> {

		  layersBox.getChildren().forEach(box -> {
			LayerBox lBox = (LayerBox) box;
			lBox.setLocked(false);
			GroupLock groupLock = AppContext.getInstance().getGroupLocks()
				    .get(GuiFacade.getInstance().getActiveImage());
			if (groupLock.getLockedLayers(newVal).contains(lBox.getLayer()))
			{
			      lBox.setLocked(true);
			}
		  });

	    });

      }


      @FXML
      private void handleMoveLayerDown(ActionEvent event)
      {

	    // handleMoveLayerDown
	    moveLayerDown();
      }


      private void moveLayerDown()
      {

	    executeLayerAction(LayerActions.MOVEDOWN);

      }


      @FXML
      private void handleMoveLayerUp(ActionEvent event)
      {

	    // handleMoveLayerUp
	    moveLayerUp();
      }


      private void moveLayerUp()
      {

	    executeLayerAction(LayerActions.MOVEUP);

      }


      @FXML
      private void handleDuplicateLayer(ActionEvent event)
      {

	    // handleDuplicateLayer
	    duplicateLayer();
      }


      private void duplicateLayer()
      {

	    executeLayerAction(LayerActions.DUPLICATE);

      }


      @FXML
      private void handleNewLayer(ActionEvent event)
      {

	    // handleNewLayer
	    newLayer();
      }


      private void newLayer()
      {

	    executeLayerAction(LayerActions.ADDNEW);

      }


      @FXML
      private void handleDeleteLayer(ActionEvent event)
      {

	    // handleDeleteLayer
	    deleteLayer();
      }


      private void deleteLayer()
      {

	    executeLayerAction(LayerActions.DELETE);

      }


      private void executeLayerAction(LayerActions action)
      {

	    if (null != activeLayer.get())
	    {
		  try
		  {
			Executor.getInstance()
				    .executeAction(new LayerAction(image.get(), activeLayer.get(), action));
			populate();

			Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));

		  }
		  catch (Exception e)
		  {
			new ExceptionPopUp(e);
		  }
	    }
      }


      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    deleteLayerBut.setGraphic(new ImageView(deleteLayerButImg));

	    duplicateLayerBut.setGraphic(new ImageView(duplicateLayerButImg));

	    newLayerBut.setGraphic(new ImageView(newLayerButImg));

	    moveLayerUpBut.setGraphic(new ImageView(moveLayerUpButImg));

	    moveLayerDownBut.setGraphic(new ImageView(moveLayerDownButImg));

      }


      private void populate() throws NumberFormatException
      {
	    // populate VBox with layerBoxes and toggleGroup

	    layersBox.getChildren().clear();

	    togglegroup = new ToggleGroup();

	    togglegroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
		  LayerBox box = (LayerBox) newVal;
		  ALayer layer = box.getUserData();
		  activeLayer.set(layer);
	    });

	    for (int i = 0; i < getImage().getLayerList().getItems().size(); i++)
	    {

		  ALayer layer = getImage().getLayerList().getItem(i);

		  LayerBox box = new LayerBox(getImage(), layer);

		  layersBox.getChildren().add(0, box);
		  box.setToggleGroup(togglegroup);

		  GuiFacade.getInstance().getMiniatureManager().addMiniature(layer, box.getCanvas());
	    }

      }


      public ToggleGroup getTogglegroup()
      {

	    return togglegroup;
      }


      public void setTogglegroup(ToggleGroup togglegroup)
      {

	    this.togglegroup = togglegroup;
      }


      public final SimpleObjectProperty<PixImage> imageProperty()
      {

	    return this.image;
      }


      public final PixImage getImage()
      {

	    return this.imageProperty().get();
      }


      public final void setImage(final PixImage image)
      {

	    this.imageProperty().set(image);
      }


      public final SimpleObjectProperty<ALayer> activeLayerProperty()
      {

	    return this.activeLayer;
      }


      public final ALayer getActiveLayer()
      {

	    return this.activeLayerProperty().get();
      }


      public final void setActiveLayer(final ALayer activeLayer)
      {

	    this.activeLayerProperty().set(activeLayer);
      }

}
