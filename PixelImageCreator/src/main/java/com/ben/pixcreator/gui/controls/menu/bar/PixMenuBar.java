
package com.ben.pixcreator.gui.controls.menu.bar;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PixMenuBar extends MenuBar
{

      public PixMenuBar()
      {

	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixMenuBar.fxml"), bundle);
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
      private void newAction(final ActionEvent event)
      {

	    handleNewAction();
      }


      private void handleNewAction()
      {
	    // TODO handle New action

      }


      @FXML
      private void openAction(final ActionEvent event)
      {

	    handleOpenAction();
      }


      private void handleOpenAction()
      {
	    // TODO handle Open action

      }


      @FXML
      private void handleKeyInput(InputEvent event)
      {

	    if (event instanceof KeyEvent)
	    {
		  final KeyEvent keyEvent = (KeyEvent) event;
		  if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.N)
		  {
			handleNewAction();
		  }
		  if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.O)
		  {
			handleOpenAction();
		  }
	    }
      }

}
