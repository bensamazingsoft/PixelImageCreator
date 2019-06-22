
package com.ben.pixcreator.gui.pane.web.controls;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class EditableTextArea extends VBox
{

      private TextArea		    textArea;

      private SimpleStringProperty  text;

      private SimpleBooleanProperty editable;

      private Button		    okBut;


      public EditableTextArea()
      {

	    textArea = new TextArea();
	    text = new SimpleStringProperty();
	    editable = new SimpleBooleanProperty();
	    okBut = new Button("OK");

	    textArea.setWrapText(true);
	    textArea.mouseTransparentProperty().bind(editable.not());

	    text.bindBidirectional(textArea.textProperty());
	    textArea.editableProperty().bindBidirectional(editable);
	    getChildren().add(textArea);

	    okBut.setAlignment(Pos.BOTTOM_RIGHT);
	    okBut.visibleProperty().bindBidirectional(editable);
	    okBut.setOnAction(event -> {
		  if (editable.get())
		  {
			setEditable(false);

		  }
	    });
	    getChildren().add(okBut);

	    addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		  if (event.getEventType().getName().equals("MOUSE_CLICKED") && event.getClickCount() > 1 && !editable.get())
		  {

			editable.set(true);

		  }
	    });

	    setEditable(false);
      }


      public EditableTextArea(String text)
      {

	    this();
	    setText(text);

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

}
