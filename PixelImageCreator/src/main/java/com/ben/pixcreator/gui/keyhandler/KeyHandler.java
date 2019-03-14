package com.ben.pixcreator.gui.keyhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(KeyHandler.class);

	@Override
	public void handle(KeyEvent event) {
		// log.debug("Handling " + event.getCode());
		if (event.getEventType().getName().equals("KEY_PRESSED") &&
				event.getCode().isWhitespaceKey()) {
			// log.debug("KEY_PRESSED -> event.getCode() : " + event.getCode());
			GuiFacade.getInstance().getActiveTab().togglePanMode();

			event.consume();
		}
	}

}
