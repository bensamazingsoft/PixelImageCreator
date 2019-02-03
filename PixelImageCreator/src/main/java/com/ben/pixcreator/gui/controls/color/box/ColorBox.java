
package com.ben.pixcreator.gui.controls.color.box;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * has a color builder method
 *
 */
public class ColorBox extends StackPane implements Toggle, Initializable
{

      @FXML
      ColorPicker				colorPicker;

      private SimpleBooleanProperty		selected    = new SimpleBooleanProperty();
      private SimpleObjectProperty<ToggleGroup>	toggleGroup = new SimpleObjectProperty<>();
      private ObjectProperty<Color>		color	    = new SimpleObjectProperty<>();


      public ColorBox()
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


      // builder
      public ColorBox color(Color color)
      {

	    setColor(color);

	    return this;

      }


      @Override
      public void initialize(URL arg0, ResourceBundle bundle)
      {

	    colorPicker.valueProperty().bindBidirectional(color);

	    this.color.addListener((obs, oldVal, newVal) -> {
		  colorPicker.setValue(newVal);
	    });

      }


      @Override
      public ToggleGroup getToggleGroup()
      {

	    return toggleGroup.get();
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
      public void setSelected(boolean arg0)
      {

	    selected.set(arg0);

      }


      @Override
      public void setToggleGroup(ToggleGroup arg0)
      {

	    toggleGroup.set(arg0);
	    toggleGroup.get().getToggles().add(this);
	    toggleGroup.get().selectToggle(this);

      }


      @Override
      public ObjectProperty<ToggleGroup> toggleGroupProperty()
      {

	    return toggleGroup;
      }


      public final ObjectProperty<Color> colorProperty()
      {

	    return this.color;
      }


      public final Color getColor()
      {

	    return this.colorProperty().get();
      }


      public final void setColor(final Color color)
      {

	    this.colorProperty().set(color);
      }

}
