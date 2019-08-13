
package com.ben.pixcreator.application.image.creator;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PixImageCreator
{

      private static final String _1200PX_900PX	= "1200px * 900px";
      private static final String _800PX_600PX	= "800px * 600px";
      private static final String _400PX_300PX	= "400px * 300px";
      private Alert		  popUp;
      ToggleGroup		  toggleGroup	= new ToggleGroup();


      public PixImageCreator()
      {

	    String txt = AppContext.getInstance().getBundle().getString("newImageDialogContextText");

	    popUp = new Alert(AlertType.CONFIRMATION, txt, ButtonType.OK);
	    popUp.getDialogPane().setContent(getContent());

      }


      public PixImage getNewImage()
      {

	    popUp.showAndWait();

	    switch (((RadioButton) toggleGroup.getSelectedToggle()).getText())
	    {

	    case _1200PX_900PX:
		  return new PixImage(_1200PX_900PX, 1200, 900);
	    case _800PX_600PX:
		  return new PixImage(_800PX_600PX, 800, 600);
	    case _400PX_300PX:
		  return new PixImage(_400PX_300PX, 400, 300);
	    default:
		  break;
	    }
	    return new PixImage();
      }


      private Node getContent()
      {

	    VBox box = new VBox();
	    StackPane stack = new StackPane(box);

	    RadioButton _400PX_300PX_RadioButton = new RadioButton();
	    _400PX_300PX_RadioButton.setToggleGroup(toggleGroup);
	    _400PX_300PX_RadioButton.setText(_400PX_300PX);
	    RadioButton _800PX_600PX_RadioButton = new RadioButton();
	    _800PX_600PX_RadioButton.setToggleGroup(toggleGroup);
	    _800PX_600PX_RadioButton.setText(_800PX_600PX);
	    RadioButton _1200PX_900PX_RadioButton = new RadioButton();
	    _1200PX_900PX_RadioButton.setToggleGroup(toggleGroup);
	    _1200PX_900PX_RadioButton.setText(_1200PX_900PX);

	    box.getChildren().addAll(
			new Label(AppContext.getInstance().getBundle().getString("imageSize")), _400PX_300PX_RadioButton, _800PX_600PX_RadioButton, _1200PX_900PX_RadioButton);

	    toggleGroup.selectToggle(_800PX_600PX_RadioButton);

	    return stack;
      }

}
