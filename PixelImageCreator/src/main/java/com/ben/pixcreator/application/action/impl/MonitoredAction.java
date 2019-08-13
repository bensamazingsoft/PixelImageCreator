
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MonitoredAction implements IAction
{

      private Task<?> task;
      private Alert   alert;


      public MonitoredAction(Task<?> task)
      {

	    super();
	    this.task = task;

	    alert = setupAlert();

	    task.setOnScheduled(evt -> {
		  alert.show();
	    });

	    task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, evt -> {

		  alert.close();

	    });

	    task.addEventHandler(WorkerStateEvent.WORKER_STATE_FAILED, evt -> {

		  alert.close();

	    });

      }


      public MonitoredAction success(EventHandler<? super WorkerStateEvent> eventHandler)
      {

	    task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, eventHandler);

	    return this;
      }


      public MonitoredAction fail(EventHandler<? super WorkerStateEvent> eventHandler)
      {

	    task.addEventHandler(WorkerStateEvent.WORKER_STATE_FAILED, eventHandler);

	    return this;
      }


      @Override
      public void execute() throws Exception
      {

	    new Thread(task).start();

      }


      private Alert setupAlert()
      {

	    Alert alert = new Alert(AlertType.INFORMATION);

	    alert.headerTextProperty().bind(task.titleProperty());

	    alert.contentTextProperty().bind(task.messageProperty());

	    return alert;

      }

}
