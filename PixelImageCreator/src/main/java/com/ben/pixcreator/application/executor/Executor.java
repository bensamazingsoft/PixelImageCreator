
package com.ben.pixcreator.application.executor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.impl.operation.Operation;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.facade.GuiFacade;

/**
 * Executes the actions and manage histories.</br>
 * A single action, if cancellable, is added to the current image history.<br/>
 * An operation is added do the active image history when it is terminated.
 * 
 * @author bmo
 *
 */
public class Executor {

	private static volatile Executor instance;

	private static final Logger logger = LoggerFactory.getLogger(Executor.class);

	private Operation currOperation;

	private Map<PixImage, LinkedList<ICancelable>>	historyMap		= new HashMap<>();
	private Map<PixImage, LinkedList<ICancelable>>	cancelledMap	= new HashMap<>();

	public boolean operationStarted;

	private Executor() {

		operationStarted = false;
	}

	private LinkedList<ICancelable> activeHistory() {

		return historyMap.computeIfAbsent(GuiFacade.getInstance().getActiveImage(),
				image -> new LinkedList<ICancelable>());
	}

	/**
	 * actions cancelled and redoable.
	 * 
	 * @return
	 */
	private LinkedList<ICancelable> activeCancelled() {

		return cancelledMap.computeIfAbsent(GuiFacade.getInstance().getActiveImage(),
				image -> new LinkedList<ICancelable>());
	}

	/**
	 * Executes a single action and store it to the active image history if it
	 * is a cancellable action.
	 * 
	 * @param action
	 * @throws Exception
	 */
	public void executeAction(IAction action) throws Exception {

		if (null != action) {
			action.execute();
			if (action instanceof ICancelable) {
				activeHistory().add((ICancelable) action);
				activeCancelled().clear();
			}
		}
	}

	/**
	 * If no prior operation is started, creates a new operation and update the
	 * operationStarted status.
	 * 
	 * 
	 */
	public void startOperation() {

		if (!operationStarted) {
			currOperation = new Operation();
			operationStarted = true;
		} else {
			logger.info("operation already started");
		}

	}

	/**
	 * Adds the action to the current operation (if one is started) and executes
	 * it.
	 * 
	 * @param action
	 * @throws Exception
	 */
	public void continueOperation(ICancelable action) throws Exception {

		if (operationStarted) {
			currOperation.addAction(action);
			action.execute();
		} else {
			logger.info("no started operation to continue");
		}
	}

	/**
	 * Terminates the current operation (if one is started) and adds it to the
	 * active image history IF it contains at least one action.<br/>
	 * Then updates the operation status.
	 * 
	 * 
	 */
	public void endOperation() {

		if (operationStarted) {
			if (!currOperation.getActions().isEmpty()) {
				activeHistory().add(currOperation);
				activeCancelled().clear();
			}
			operationStarted = false;

		} else {
			logger.info("no started operation to end");
		}
	}

	/**
	 * Terminates the current operation and cancels its actions<br/>
	 * Then updates the operation status.
	 * 
	 * 
	 */
	public void abortOperation() throws Exception {

		if (operationStarted) {
			currOperation.cancel();
			operationStarted = false;
		} else {
			logger.info("no started operation to abort");
		}

	}

	/**
	 * Removes the last item (action or operation) from the active image
	 * history, undoes it and puts it in the active image redo history.
	 * 
	 * @throws Exception
	 */
	public void cancel() throws Exception {

		if (activeHistory().size() > 0) {
			ICancelable cancelledAction = activeHistory().pollLast();
			cancelledAction.cancel();
			activeCancelled().add(cancelledAction);
		}

	}

	/**
	 * Removes the last item (action or operation) from the active image redo
	 * history, executes it and puts it in the active image history.
	 * 
	 * @throws Exception
	 */
	public void redo() throws Exception {

		if (activeCancelled().size() > 0) {
			ICancelable redoAction = activeCancelled().pollLast();
			redoAction.execute();
			activeHistory().add(redoAction);
		}
	}

	public static Executor getInstance() {

		if (instance == null) {
			instance = new Executor();
		}

		return instance;

	}

	public Map<PixImage, LinkedList<ICancelable>> getHistoryMap() {

		return historyMap;
	}

	public void setHistoryMap(Map<PixImage, LinkedList<ICancelable>> historyMap) {

		this.historyMap = historyMap;
	}

	public Map<PixImage, LinkedList<ICancelable>> getCancelledMap() {

		return cancelledMap;
	}

	public void setCancelledMap(Map<PixImage, LinkedList<ICancelable>> cancelledMap) {

		this.cancelledMap = cancelledMap;
	}

	public boolean isOperationStarted() {
		return operationStarted;
	}

}
