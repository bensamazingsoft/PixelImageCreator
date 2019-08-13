
package com.ben.pixcreator.gui.controls.layer.panel;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.LayerAction;
import com.ben.pixcreator.application.action.impl.MiniaturesUpdateAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.gui.controls.layer.box.LayerBox;
import com.ben.pixcreator.gui.controls.layer.panel.actions.LayerActions;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.tooltip.provider.ToolTipProvider;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LayerPanel extends BorderPane implements Initializable
{

      private final GuiFacade					      gui		   = GuiFacade.getInstance();

      private final static Map<PixImage, Map<ALayer, LayerBox>>	      layerBoxManager	   = new HashMap<>();

      private final String					      IMAGEPATH		   = "images/gui/buttons/tools/";

      private SimpleObjectProperty<PixImage>			      image;
      private SimpleObjectProperty<ALayer>			      activeLayer	   = new SimpleObjectProperty<>();
      public static Map<PixImage, Map<ALayer, SimpleBooleanProperty>> effectPaneExpand	   = new HashMap<>();

      final Image						      moveLayerDownButImg  = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveLayerDownButImg.png"));
      final Image						      moveLayerUpButImg	   = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveLayerUpButImg.png"));
      final Image						      deleteLayerButImg	   = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "deleteLayerButImg.png"));
      final Image						      duplicateLayerButImg = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "duplicateLayerButImg.png"));
      final Image						      newLayerButImg	   = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "newLayerButImg.png"));
      final Image						      newPicLayerButImg	   = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "newPicLayerButImg.png"));
      final Image						      newTextLayerButImg   = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "newTextLayerButImg.png"));
      final Image						      newBakeLayerButImg   = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "newBakeLayerButImg.png"));

      @FXML
      private ToolBar						      toolBar;

      @FXML
      private VBox						      moveLayerButBox;

      @FXML
      private VBox						      layersBox;

      @FXML
      private Button						      deleteLayerBut;

      @FXML
      private Button						      duplicateLayerBut;

      @FXML
      private Button						      newLayerBut;

      @FXML
      private Button						      newPicLayerBut;

      @FXML
      private Button						      newTextLayerBut;

      @FXML
      private Button						      newBakeLayerBut;

      @FXML
      private Button						      moveLayerUpBut;

      @FXML
      private Button						      moveLayerDownBut;

      @FXML
      private ToggleGroup					      togglegroup;

      @FXML
      private ScrollPane					      scrollPane;

      private ToolTipProvider					      toolTipProvider	   = AppContext.getInstance().getToolTipProvider();


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
	    newPixLayer();
      }


      @FXML
      private void handleNewPicLayer(ActionEvent event)
      {

	    // handleNewLayer
	    newPicLayer();
      }


      @FXML
      private void handleNewTextLayer(ActionEvent event)
      {

	    // handleNewLayer
	    newTextLayer();
      }


      private void newTextLayer()
      {

	    executeLayerAction(LayerActions.ADDNEWTEXT);

      }


      @FXML
      private void handleNewBakeLayer(ActionEvent event)
      {

	    // handleNewLayer
	    newBakeLayer();
      }


      private void newBakeLayer()
      {

	    executeLayerAction(LayerActions.ADDNEWBAKE);

      }


      private void newPicLayer()
      {

	    executeLayerAction(LayerActions.ADDNEWPIC);

      }


      private void newPixLayer()
      {

	    executeLayerAction(LayerActions.ADDNEW);

      }


      @FXML
      private void handleDeleteLayer(ActionEvent event)
      {

	    deleteLayer();

      }


      private void deleteLayer()
      {

	    executeLayerAction(LayerActions.DELETE);

	    if (image.get().getLayerPile().isEmpty())
	    {
		  newPixLayer();

	    }

      }


      private void executeLayerAction(LayerActions action)
      {

	    final ALayer layer = activeLayer.get();
	    if (null != layer)
	    {
		  try
		  {
			Executor.getInstance()
				    .executeAction(new LayerAction(image.get(), layer, action));

			populate();

			if (gui.getActiveimage().getLayerPile().getIdx(layer) > -1)
			{
			      gui.selectLayer(gui.getFocusLayer());
			}

			Executor.getInstance().executeAction(new RefreshTabAction(gui.getActiveTab()));

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
	    deleteLayerBut.setTooltip(toolTipProvider.get("deleteLayerButTip"));

	    duplicateLayerBut.setGraphic(new ImageView(duplicateLayerButImg));
	    duplicateLayerBut.setTooltip(toolTipProvider.get("duplicateLayerButTip"));

	    newLayerBut.setGraphic(new ImageView(newLayerButImg));
	    newLayerBut.setTooltip(toolTipProvider.get("newLayerButTip"));

	    newPicLayerBut.setGraphic(new ImageView(newPicLayerButImg));
	    newPicLayerBut.setTooltip(toolTipProvider.get("newPicLayerButTip"));

	    newTextLayerBut.setGraphic(new ImageView(newTextLayerButImg));
	    newTextLayerBut.setTooltip(toolTipProvider.get("newTextLayerButTip"));

	    newBakeLayerBut.setGraphic(new ImageView(newBakeLayerButImg));
	    newBakeLayerBut.setTooltip(toolTipProvider.get("newBakeLayerButTip"));

	    moveLayerUpBut.setGraphic(new ImageView(moveLayerUpButImg));
	    moveLayerUpBut.setTooltip(toolTipProvider.get("moveLayerUpButTip"));

	    moveLayerDownBut.setGraphic(new ImageView(moveLayerDownButImg));
	    moveLayerDownBut.setTooltip(toolTipProvider.get("moveLayerDownButTip"));

	    scrollPane.setFitToWidth(true);
	    scrollPane.setFitToHeight(true);

      }


      public void populate() throws NumberFormatException
      {
	    // populate VBox with layerBoxes and toggleGroup

	    layersBox.getChildren().clear();

	    togglegroup = new ToggleGroup();

	    togglegroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
		  LayerBox box = (LayerBox) newVal;
		  ALayer layer = box.getUserData();
		  activeLayer.set(layer);
	    });

	    togglegroup.getToggles().addListener(new TogListChangeListener());

	    Map<ALayer, LayerBox> imageLayerBox = layerBoxManager.computeIfAbsent(
			getImage(),
			key -> new HashMap<ALayer, LayerBox>());

	    for (int i = 0; i < getImage().getLayerPile().getItems().size(); i++)
	    {

		  ALayer layer = getImage().getLayerPile().getItem(i);

		  LayerBox box = imageLayerBox.computeIfAbsent(layer, key -> new LayerBox(getImage(), layer));

		  box.loadEffectsPane();

		  layersBox.getChildren().add(0, box);
		  box.setToggleGroup(togglegroup);

		  GuiFacade.getInstance().getMiniatureManager().addMiniature(layer, box.getImageView());

	    }

	    try
	    {
		  Executor.getInstance().executeAction(new MiniaturesUpdateAction(GuiFacade.getInstance().getActiveTab()));
	    }
	    catch (Exception e)
	    {
		  new ExceptionPopUp(e);
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

      public class TogListChangeListener implements ListChangeListener<LayerBox>, InvalidationListener
      {

	    private final Logger log = LoggerFactory.getLogger(LayerPanel.TogListChangeListener.class);


	    @Override
	    public void onChanged(javafx.collections.ListChangeListener.Change<? extends LayerBox> arg0)
	    {

		  log.debug("populated by listener");
		  populate();

	    }


	    @Override
	    public void invalidated(Observable observable)
	    {

	    }

      }

}
