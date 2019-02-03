
package com.ben.pixcreator.gui.exception.popup;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ExceptionPopUp extends Alert
{

      private static final Logger log = LoggerFactory.getLogger(ExceptionPopUp.class);


      public ExceptionPopUp(AlertType arg0)
      {

	    super(arg0);
      }


      public ExceptionPopUp()
      {

	    super(AlertType.ERROR);

	    setTitle(AppContext.getInstance().getBundle().getString("ExceptionPopUpTitle"));
      }


      public ExceptionPopUp(Exception e)
      {

	    this();
	    log.error(e.getMessage(), e);
	    setContentText(e.getMessage());

	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);

	    String exceptionText = sw.toString();

	    Label label = new Label("Stacktrace :");

	    TextArea textArea = new TextArea(exceptionText);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);

	    GridPane expContent = new GridPane();
	    expContent.setMaxWidth(Double.MAX_VALUE);
	    expContent.add(label, 0, 0);
	    expContent.add(textArea, 0, 1);

	    getDialogPane().setExpandableContent(expContent);

	    showAndWait();
      }

}