package com.ben.pixcreator.gui.controls.color.box;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

/**
 * has a color builder method
 *
 */
public class ColorBox extends HBox implements Toggle, Initializable{


	
	
	@FXML
	ColorPicker colorPicker;
	
	   public ColorBox() throws  IOException
	      {

		    super();



		    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ColorBox.fxml"), bundle);
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


	private ColorBox color(Color color){
		
		colorPicker.setValue(color);
		
		return this;		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		colorPicker.setValue(color);
		
		
		
		
	}
	
	
	public void setColor(Color color){
		
		colorPicker.setValue(color);
		
		
	}
	
	
	public Color getColor(){
		return colorPicker.getValue();
	}
	
	
	
}
