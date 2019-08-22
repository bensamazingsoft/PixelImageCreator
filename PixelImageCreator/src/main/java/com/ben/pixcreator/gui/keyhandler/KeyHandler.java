
package com.ben.pixcreator.gui.keyhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

	private AppContext ctx = AppContext.getInstance();

	private GuiFacade gui = GuiFacade.getInstance();

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(KeyHandler.class);

	@Override
	public void handle(KeyEvent event) {

		// log.debug("Handling " + event.getCode());
		if (event.getEventType().getName().equals("KEY_PRESSED") &&
				event.getCode() == KeyCode.SPACE) {
			// log.debug("KEY_PRESSED -> event.getCode() : " + event.getCode());

			if (ctx.getCurrTool() != PixTool.PAN) {

				gui.toggleToolTo(PixTool.PAN);
				gui.setPanMode(true);

			} else {
				gui.toggleToolTo(gui.getSelectedToolInToolbar());
				gui.setPanMode(false);

			}

			event.consume();
		}

		if (event.getEventType().getName().equals("KEY_PRESSED") &&
				event.getCode().isArrowKey()) {
			// log.debug("KEY_PRESSED -> event.getCode() : " + event.getCode());

			switch (event.getCode()) {

			case LEFT:
				log.debug("selectPrevColorBox()");
				gui.selectPrevColorBox();
				break;
			case RIGHT:
				log.debug("selectNextColorBox()");
				gui.selectNextColorBox();
				break;
			default:
				break;
			}

			event.consume();
		}

	}

}
