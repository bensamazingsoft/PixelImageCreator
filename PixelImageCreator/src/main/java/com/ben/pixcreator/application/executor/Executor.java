
package com.ben.pixcreator.application.executor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.impl.Operation;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class Executor {

	// TODO when open image occurs : create the entry in history and cancelled
	// map (computeif..???)

	private static Executor instance;

	private static final Logger logger = LoggerFactory.getLogger(Executor.class);

	private Operation currOperation;

	private Map<PixImage, LinkedList<ICancelable>>	historyMap		= new HashMap<>();
	private Map<PixImage, LinkedList<ICancelable>>	cancelledMap	= new HashMap<>();

	private boolean operationStarted;

	private Executor() {
		operationStarted = false;
	}

	private LinkedList<ICancelable> activeHistory() {
		return historyMap.get(GuiFacade.getInstance().getActiveImage());
	}

	private LinkedList<ICancelable> activeCancelled() {
		return cancelledMap.get(GuiFacade.getInstance().getActiveImage());
	}

	public void executeAction(IAction action) throws Exception {

		action.execute();
		if (action instanceof ICancelable) {
			activeHistory().add((ICancelable) action);
		}
		activeCancelled().clear();
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
				activeHistory().add(currOperation);
			}
			operationStarted = false;

		} else {
			logger.info("no started operation to end");
		}
	}

	public void cancel() throws Exception {

		if (activeHistory().size() > 0) {
			ICancelable cancelledAction = activeHistory().pollLast();
			cancelledAction.cancel();
			activeCancelled().add(cancelledAction);
		}

	}

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

}
