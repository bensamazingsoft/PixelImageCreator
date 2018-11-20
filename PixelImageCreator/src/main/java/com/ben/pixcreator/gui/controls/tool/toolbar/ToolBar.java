package com.ben.pixcreator.gui.controls.tool.toolbar;

import com.sun.xml.internal.org.jvnet.staxex.NamespaceContextEx.Binding;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

public class ToolBar extends HBox implements Initializable{

	private ToggleGroup toggleGroup;
	
	@FXML
	ToggleButton selectBut;
	
	final Image selectButSelected;
	final Image selectButUnSelected;
	final ImageView selectButImg = new ImageView();
	
	
	
	public ToolBar(){
		
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
	private void toggleSelect(ActionEvent event){
		handleToggle();
	}
	
	
	private void handleToggle() {
		// TODO handle tool toggle action 
		
	}


	@Override
	public void initialize(URL url, ResourceBundle bd){
		
		selectBut.setImage(selectButImg);
		selectButImg.imageProperty().bind(Bindings.when(selectBut.selectedProperty()).then(selectButSelected).otherwise(selectButUnSelected));
		selectBut.setToggleGroup(toggleGroup);
		
	}
	
	
	
	
}
