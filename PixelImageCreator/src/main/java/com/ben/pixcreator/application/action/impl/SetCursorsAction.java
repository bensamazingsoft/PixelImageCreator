
package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.cursor.factory.ControlCursorFactory;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.Cursor;

public class SetCursorsAction implements IAction {
	private static final Logger log = LoggerFactory.getLogger(SetCursorsAction.class);

	@Override
	public void execute() throws Exception {

		GuiFacade.getInstance().getTabs().forEach(tab -> {

			PixTab pixTab = (PixTab) tab;

			final Cursor cursor = new ControlCursorFactory().getCursor();
			log.debug("execute : " + getClass().toString() + " : " + cursor.toString());

			pixTab.getCanvas().setCursor(cursor);
			pixTab.getScrollPane().setCursor(cursor);

		});

	}

}
