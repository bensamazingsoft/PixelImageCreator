package com.ben.pixcreator.gui.cursor.updater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.SetCursorsAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

import javafx.application.Platform;

public class CursorUpdater extends Thread {

	private static final Logger log = LoggerFactory.getLogger(CursorUpdater.class);

	private boolean	run		= false;
	private boolean	close	= false;

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public void run() {

		super.run();
		while (!close) {
			for (;;) {

				try {
					sleep(50);
				} catch (InterruptedException e2) {
					log.error("infinite loop pre-sleep error", e2);
				}

				if (AppContext.getInstance().getCurrTool() == PixTool.PICK) {

					try {
						sleep(150);
					} catch (InterruptedException e1) {

						log.error("infinite loop sleep interrupted", e1);
					}

					Platform.runLater(() -> {

						try {
							// log.debug("update (Platform.runLater)");

							Executor.getInstance().executeAction(new SetCursorsAction());

						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (Exception e) {
							new ExceptionPopUp(e);
						}
					});

				}
				if (close) {
					break;
				}
			}
		}
	}

	public void setClose(boolean close) {
		this.close = close;
	}

}
