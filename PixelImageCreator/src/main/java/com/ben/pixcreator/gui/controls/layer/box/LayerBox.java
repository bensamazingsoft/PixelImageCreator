package com.ben.pixcreator.gui.controls.layer.box;

import java.io.IOException;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.ILayer;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox*;

public class LayerBox extends HBox implements Initializable{

	private PixImage image;
	
	
	public LayerBox(PixImage image, ILayer layer) {
		
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


	private ILayer layer; 
	
	private Image miniature;
	private ImageView miniatureView;

	private ToggleButton eyeBut, lockBut;
	
	
	
	@FXML 
	StackPane eye
	
	
	@FXML
	StackPane miniaturePane;
	

	
	@FXML
	StackPane titlePane
	
	@FXML
	StackPane lockPane
	
	@FXML
	private void toggleEye(MouseEvent event){
		image.toggleLayerVisibility(layer);
	}
	
	@FXML
	private void toggleLock(MouseEvent event){
		//TODO toggle layer lock
	}
	
	
	@Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
	//TODO set height and growh titlepane
		
    }
	
}
