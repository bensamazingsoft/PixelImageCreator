
package com.ben.pixcreator.application.executor;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.impl.Operation;

public class Executor
{

      private static Executor	  instance;

      private static final Logger logger = LoggerFactory.getLogger(Executor.class);

      private Operation		  currOperation;
      private LinkedList<IAction> history;
      private LinkedList<IAction> cancelled;
      private boolean		  operationStarted;


      private Executor()
      {

	    history = new LinkedList<IAction>();
	    cancelled = new LinkedList<IAction>();
	    operationStarted = false;
      }


      public void executeAction(IAction action) throws Exception
      {

	    action.execute();
	    history.add(action);
	    cancelled.clear();
      }


      public void startOperation(IAction action) throws Exception
      {

	    if (!operationStarted)
	    {
		  currOperation = new Operation();
		  currOperation.addAction(action);
		  action.execute();
		  operationStarted = true;
	    }
	    else
	    {
		  logger.info("operation already started");
	    }

      }


      public void continueOperation(IAction action) throws Exception
      {

	    if (operationStarted)
	    {
		  currOperation.addAction(action);
		  action.execute();
	    }
	    else
	    {
		  logger.info("no started operation to continue");
	    }
      }


      public void endOperation(IAction action) throws Exception
      {

	    if (operationStarted)
	    {
		  currOperation.addAction(action);
		  history.add(currOperation);
		  action.execute();
		  operationStarted = false;

	    }
	    else
	    {
		  logger.info("no started operation to end");
	    }
      }


      public void cancel() throws Exception
      {

	    if (history.size() > 0)
	    {
		  IAction cancelledAction = history.pollLast();
		  cancelledAction.cancel();
		  cancelled.add(cancelledAction);
	    }

      }


      public void redo() throws Exception
      {

	    if (cancelled.size() > 0)
	    {
		  IAction redoAction = cancelled.pollLast();
		  redoAction.execute();
		  history.add(redoAction);
	    }
      }


      public LinkedList<IAction> getHistory()
      {

	    return history;
      }


      public void setHistory(LinkedList<IAction> history)
      {

	    this.history = history;

      }


      public static Executor getInstance()
      {

	    if (instance == null)
	    {
		  instance = new Executor();
	    }

	    return instance;

      }

}
