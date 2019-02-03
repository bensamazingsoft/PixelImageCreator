
package com.ben.pixcreator.gui.controls.layer.box;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.grouplock.manager.GroupLockManager;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

// make this a Toggle to handle select mecanism in the layerPanel
public class LayerBox extends HBox implements Initializable, Toggle
{

      // properties
      private final int			  MINIATUREHEIGHT;
      private final int			  MINIATUREWIDTH;
      private final int			  BOXHEIGHT;
      private final int			  BOXWIDTH;

      // toggle fields

      private ObjectProperty<ToggleGroup> toggleGroup	 = new SimpleObjectProperty<ToggleGroup>();
      private SimpleBooleanProperty	  selected	 = new SimpleBooleanProperty();
      ObservableMap<Object, Object>	  properties;

      // instance fields

      private PixImage			  image;

      private ALayer			  layer;

      private Image			  miniature;
      private ImageView			  miniatureView;

      private final String		  IMAGEPATH	 = "images/gui/buttons/layerbox/";

      private Image			  imgTypePicImg	 = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypePicImg.png"));
      private Image			  imgTypePixImg	 = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypePixImg.png"));

      @FXML
      private ImageView			  imgTypeView;

      final private Image		  lockSelected	 = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "lockSelected.png"));
      final private Image		  lockUnSelected = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "lockUnSelected.png"));
      final private ImageView		  lockButImg	 = new ImageView();

      final private Image		  eyeSelected	 = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "eyeSelected.png"));
      final private Image		  eyeUnSelected	 = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "eyeUnSelected.png"));
      final private ImageView		  eyeButImg	 = new ImageView();

      @FXML
      private ToggleButton		  eyeBut;

      @FXML
      private ToggleButton		  lockBut;

      @FXML
      private StackPane			  eye;

      @FXML
      private StackPane			  miniaturePane;
      @FXML
      private Canvas			  canvas;

      @FXML
      private StackPane			  titlePane;

      @FXML
      private StackPane			  lockPane;


      public LayerBox(PixImage image, ALayer layer) throws NumberFormatException
      {

	    super();
	    getStylesheets().add("/styles/styles.css");
	    getStyleClass().add("layerbox");

	    MINIATUREHEIGHT = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));
	    MINIATUREWIDTH = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));
	    BOXHEIGHT = Integer.valueOf(AppContext.getInstance().propertyContext().get("layerBoxH"));
	    BOXWIDTH = Integer.valueOf(AppContext.getInstance().propertyContext().get("layerBoxW"));

	    this.image = image;
	    this.layer = layer;

	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LayerBox.fxml"), bundle);
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
      private void toggleEye(MouseEvent event)
      {

      }


      @FXML
      private void toggleLock(MouseEvent event)
      {

	    toggleLayerLock();
      }


      private void toggleLayerLock()
      {

	    if (GroupLockManager.getInstance().getGroupLock(layer).size() != 0)
	    {
		  GroupLockManager.getInstance().unlock(layer);
	    }
	    else
	    {
		  GroupLockManager.getInstance().lockToActiveLayer(layer);
	    }

      }


      @FXML
      private void handleMiniatureClicked(MouseEvent event)
      {

	    miniatureClicked();
      }


      private void miniatureClicked()
      {

	    if (!isSelected())
	    {
		  toggleGroup.get().selectToggle(this);
	    }

      }


      @Override
      public void initialize(URL arg0, ResourceBundle arg1)
      {
	    // TODO set height and growh titlepane, set Pix/Pix logo, etc

	    eyeBut.setGraphic(eyeButImg);
	    eyeBut.selectedProperty().bindBidirectional(layer.visibleProperty());
	    eyeButImg.imageProperty()
			.bind(Bindings.when(eyeBut.selectedProperty()).then(eyeSelected).otherwise(eyeUnSelected));

	    lockBut.setGraphic(lockButImg);
	    lockButImg.imageProperty()
			.bind(Bindings.when(lockBut.selectedProperty()).then(lockSelected).otherwise(lockUnSelected));

	    imgTypeView.imageProperty().setValue(layer instanceof PixLayer ? imgTypePixImg : imgTypePicImg);

	    this.setMaxHeight(BOXHEIGHT);
	    this.setMinWidth(BOXWIDTH);
	    HBox.setHgrow(titlePane, Priority.ALWAYS);

	    canvas.setHeight(MINIATUREHEIGHT);
	    canvas.setWidth(MINIATUREWIDTH);

	    selected.addListener((obs, oldVal, newVal) -> {
		  if (newVal)
		  {
			setStyle("-fx-background-color:red");
		  }
		  else
		  {
			setStyle("-fx-background-color:gray");
		  }
	    });

      }


      @Override
      public ToggleGroup getToggleGroup()
      {

	    return toggleGroup.get();
      }


      public ALayer getUserData()
      {

	    return layer;
      }


      @Override
      public void setToggleGroup(ToggleGroup arg0)
      {

	    toggleGroup.set(arg0);
	    toggleGroup.get().getToggles().add(this);
	    toggleGroup.get().selectToggle(this);
      }


      public void setUserData(Object arg0)
      {

      }


      public ObjectProperty<ToggleGroup> toggleGroupProperty()
      {

	    return toggleGroup;
      }


      @Override
      public boolean isSelected()
      {

	    return selected.get();
      }


      @Override
      public BooleanProperty selectedProperty()
      {

	    return selected;
      }


      @Override
      public void setSelected(boolean value)
      {

	    selected.set(value);

      }

}
