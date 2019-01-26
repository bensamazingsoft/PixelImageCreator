
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.Tab;

public class RefreshAllTabsAction implements IAction {

	@Override
	public void execute() throws Exception {

		for (Tab tab : GuiFacade.getInstance().getPixTabPane().getTabs()) {

			PixTab pxTab = (PixTab) tab;

			Executor.getInstance().executeAction(new RefreshTabAction(pxTab));

		}

	}

}
