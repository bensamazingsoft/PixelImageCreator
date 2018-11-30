
package com.ben.pixcreator.gui.controls.layer.box;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.ILayer;

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
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.StackPane;

//TODO make this a Toggle to handle select mecanism in the layerPanel
public class LayerBox extends HBox implements Initializable, Toggle
{
	
	//properties
	private final int MINIATUREHEIGHT= Integer.valueOf(AppContext.getInstance().getProperties().get("miniatureWH"));
	private final int MINIATUREWIDTH = Integer.valueOf(AppContext.getInstance().getProperties().get("miniatureWH"));
	private final int BOXHEIGHT = Integer.valueOf(AppContext.getInstance().getProperties().get("layerBoxH"));
	
	//toggle fields
	
	private ToggleGroup toggleGroup;
	private ObjectProperty<ToggleGroup> toggleGroupProperty = new SimpleObjectProperty<ToggleGroup>();
	private Boolean selected = false;
	private BooleanProperty selectedProperty = new SimpleBooleanProperty(selected);
	ObservableMap<Object, Object> properties;
	
	//instance fields
	
      private PixImage image;

      private ILayer	   layer;

      private Image	   miniature;    
      private ImageView	   miniatureView;
      
      private final String IMAGEPATH		= "images/gui/buttons/layerbox/";
      
      private Image	   imgTypePicImg = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypePicImg.png"));   
      private Image	   imgTypePixImg = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypePixImg.png")); 
  
      @FXML
      private ImageView	  imgTypeView;
      
      
      final private Image	   lockSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "lockSelected.png"));
      final private Image	   lockUnSelected	= new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "lockUnSelected.png"));
      final private ImageView	   lockButImg		= new ImageView();

      @FXML
      private ToggleButton eyeBut;

      @FXML
      private ToggleButton lockBut;
      
      @FXML
      private StackPane	   eye;

      @FXML
      private StackPane	   miniaturePane;
      @FXML
      private Canvas canvas;

      @FXML
      private StackPane	   titlePane;

      @FXML
      private StackPane	   lockPane;
      
      
      public LayerBox(PixImage image, ILayer layer)
      {

	    super();
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

	    this.image = image;
	    this.layer = layer;
      }



      @FXML
      private void toggleEye(MouseEvent event)
      {

	    image.toggleLayerVisibility(layer);
      }


      @FXML
      private void toggleLock(MouseEvent event)
      {

	    // TODO toggle layer lock
      }

      @FXML
      private void handleMiniatureClicked(MouseEvent event){
    	  // handleMiniatureClicked
    	  miniatureClicked();
      }

      private void miniatureClicked() {
		// miniatureClicked
		if (!selected){
			selected = true;
			selectedProperty.setValue(selected);
			
		}
	}



	@Override
      public void initialize(URL arg0, ResourceBundle arg1)
      {
	    // TODO set height and growh titlepane, set Pix/Pix logo, etc
		
		lockBut.setGraphic(lockButImg);
		lockButImg.imageProperty().bind(Bindings.when(lockBut.selectedProperty()).then(lockButSelected).otherwise(lockButUnSelected));

		imgTypeView.imageProperty().setValue(layer instanceof PixLayer ? imgTypePixImg : imgTypePicImg);

		
		this.setHeight(BOXHEIGHT);
		this.setHgrow(titlePane, Priority.ALWAYS);
		
		
		canvas.setHeight(MINIATUREHEIGHT);
		canvas.setWidth(MINIATUREWIDTH);

      }



	public ObservableMap<Object, Object> getProperties() {

		return properties;
	}



	public ToggleGroup getToggleGroup() {

		return toggleGroup;
	}



	public ILayer getUserData() {

		return layer;
	}



	public boolean isSelected() {

		return selected;
	}



	public BooleanProperty selectedProperty() {

		return selectedProperty;
	}



	public void setSelected(boolean arg0) {
		selected = arg0;
		selectedProperty.set(selected);
		
	}



	public void setToggleGroup(ToggleGroup arg0) {
	
		toggleGroup=arg0;
		toggleGroupProperty.set(toggleGroup);
	}



	public void setUserData(Object arg0) {

		
	}



	public ObjectProperty<ToggleGroup> toggleGroupProperty() {

		return toggleGroupProperty;
	}

}