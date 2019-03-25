
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.cursor.factory.ControlCursorFactory;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class SetCursorsAction implements IAction
{

      @Override
      public void execute() throws Exception
      {

	    GuiFacade.getInstance().getTabs().forEach(tab -> {

		  PixTab pixTab = (PixTab) tab;

		  pixTab.getCanvas().setCursor(new ControlCursorFactory().getCursor());
		  pixTab.getScrollPane().setCursor(new ControlCursorFactory().getCursor());

	    });

      }

}
