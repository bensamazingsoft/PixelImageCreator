
package com.ben.pixcreator.application.executor;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.impl.Operation;

public class Executor {

	private static Executor instance;

	private static final Logger logger = LoggerFactory.getLogger(Executor.class);

	private Operation				currOperation;
	private LinkedList<ICancelable>	history;
	private LinkedList<ICancelable>	cancelled;
	private boolean					operationStarted;

	private Executor() {

		history = new LinkedList<>();
		cancelled = new LinkedList<>();
		operationStarted = false;
	}

	public void executeAction(IAction action) throws Exception {

		action.execute();
		if (action instanceof ICancelable) {
			history.add((ICancelable) action);
		}
		cancelled.clear();
	}

	public void startOperation() throws Exception {

		if (!operationStarted) {
			currOperation = new Operation();
			operationStarted = true;
		} else {
			logger.info("operation already started");
		}

	}

	public void continueOperation(ICancelable action) throws Exception {

		if (operationStarted) {
			currOperation.addAction(action);
			action.execute();
		} else {
			logger.info("no started operation to continue");
		}
	}

	public void endOperation() throws Exception {

		if (operationStarted) {
			if (operationStarted) {
				history.add(currOperation);
			}
			operationStarted = false;

		} else {
			logger.info("no started operation to end");
		}
	}

	public void cancel() throws Exception {

		if (history.size() > 0) {
			ICancelable cancelledAction = history.pollLast();
			cancelledAction.cancel();
			cancelled.add(cancelledAction);
		}

	}

	public void redo() throws Exception {

		if (cancelled.size() > 0) {
			ICancelable redoAction = cancelled.pollLast();
			redoAction.execute();
			history.add(redoAction);
		}
	}

	public LinkedList<ICancelable> getHistory() {

		return history;
	}

	public void setHistory(LinkedList<ICancelable> history) {

		this.history = history;

	}

	public static Executor getInstance() {

		if (instance == null) {
			instance = new Executor();
		}

		return instance;

	}

}
