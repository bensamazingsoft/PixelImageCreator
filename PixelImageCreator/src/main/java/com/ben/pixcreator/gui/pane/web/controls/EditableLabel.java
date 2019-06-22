
package com.ben.pixcreator.gui.pane.web.controls;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class EditableLabel extends StackPane
{

      private TextField		    textField;
      private Label		    label;

      private SimpleStringProperty  text;

      private SimpleBooleanProperty editable;


      public EditableLabel()
      {

	    super();

	    label = new Label();
	    getChildren().add(label);
	    textField = new TextField();
	    getChildren().add(textField);
	    text = new SimpleStringProperty();
	    editable = new SimpleBooleanProperty(false);

	    text.bindBidirectional(label.textProperty());
	    text.bindBidirectional(textField.textProperty());

	    textField.visibleProperty().bindBidirectional(editable);
	    label.visibleProperty().bind(editable.not());

	    addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		  if (event.getEventType().getName().equals("MOUSE_CLICKED") && event.getClickCount() > 1 && !editable.get())
		  {

			editable.set(true);

		  }
	    });

	    textField.setOnAction(event -> {

		  if (editable.get())
		  {
			editable.set(false);
		  }

	    });
      }


      public EditableLabel(String text)
      {

	    this();
	    setText(text);

      }


      public final SimpleBooleanProperty editableProperty()
      {

	    return this.editable;
      }


      public final boolean isEditable()
      {

	    return this.editableProperty().get();
      }


      public final void setEditable(final boolean editable)
      {

	    this.editableProperty().set(editable);
      }


      public final SimpleStringProperty textProperty()
      {

	    return this.text;
      }


      public final String getText()
      {

	    return this.textProperty().get();
      }


      public final void setText(final String text)
      {

	    this.textProperty().set(text);
      }

}
