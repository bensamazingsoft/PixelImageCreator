package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;

public class NoOpAction implements IAction {
	private static final Logger log = LoggerFactory.getLogger(NoOpAction.class);

	@Override
	public void execute() throws Exception {
		log.debug("no operation");
	}

}
