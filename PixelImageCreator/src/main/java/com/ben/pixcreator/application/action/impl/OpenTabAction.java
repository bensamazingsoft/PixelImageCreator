
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class OpenTabAction implements IAction {

	private final PixImage image;

	public OpenTabAction(PixImage image) {

		this.image = image;
	}

	@Override
	public void execute() throws Exception {

		AppContext.getInstance().getGroupLocks().put(image, new GroupLock());

		PixTab tab = new PixTab(image);

		GuiFacade.getInstance().addTab(tab);

		Executor.getInstance().executeAction(new RefreshTabAction(tab));

	}

}
