
package com.ben.pixcreator.application.action.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.facade.ToolGuiFacade;

import javafx.scene.control.Toggle;

public class ChangeToolAction implements IAction {
	private static final Logger	log	= LoggerFactory.getLogger(ChangeToolAction.class);
	private PixTool				pixTool;

	public ChangeToolAction(PixTool pixTool) {

		this.pixTool = pixTool;
	}

	@Override
	public void execute() throws Exception {

		log.debug("change to " + pixTool.name());

		final ToolGuiFacade gui = GuiFacade.getInstance();

		AppContext.getInstance().setCurrTool(pixTool);

		PixToolBar pixToolBar = gui.getPixToolBar();
		List<Toggle> list = pixToolBar.getToggles().stream()
				.filter(togBut -> ((PixTool) togBut.getUserData()).name().equals(pixTool.name()))
				.collect(Collectors.toList());

		if (list.size() > 0) {
			Toggle toggle = list.get(0);
			pixToolBar.getToggleGroup().selectToggle(toggle);
		}

	}

}
