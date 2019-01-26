
package com.ben.pixcreator.application.action.impl;

import java.util.ArrayList;

import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.IAction;

/**
 * Represents a series of <strong>cancellable</strong> actions.
 * 
 * @author bmo
 *
 */
public class Operation implements IAction, ICancelable {

	ArrayList<ICancelable> actions;

	public Operation() {

		actions = new ArrayList<>();
	}

	public Operation(ArrayList<ICancelable> actions) {

		super();
		this.actions = actions;
	}

	public void addAction(ICancelable action) {

		if (actions == null) {
			actions = new ArrayList<>();
		}
		actions.add(action);
	}

	public void execute() throws Exception {

		if (actions != null && actions.size() > 0) {
			for (int i = 0; i < actions.size(); i++) {
				actions.get(i).execute();
			}
		}

	}

	public void cancel() throws Exception {

		if (actions != null && actions.size() > 0) {
			for (int j = actions.size(); j > 0; j--) {
				actions.get(j).cancel();
			}
		}
	}

	public ArrayList<ICancelable> getActions() {

		return actions;
	}

	public void setActions(ArrayList<ICancelable> actions) {

		this.actions = actions;
	}

}
