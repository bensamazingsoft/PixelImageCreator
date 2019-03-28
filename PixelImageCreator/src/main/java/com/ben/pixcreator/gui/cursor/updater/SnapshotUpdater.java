package com.ben.pixcreator.gui.cursor.updater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.PickColorAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;

/**
 * updates the canvas snapshot used by the Pick tool. The purpose is to lighten
 * the MOUSE_MOVED event listener method wich was causing cursor bugs when
 * tasked with calculating the snapshot everytime the event was triggered. And
 * it's cool.
 * 
 * @author bmo
 *
 */
public class SnapshotUpdater extends Thread {

	private static final Logger	log		= LoggerFactory.getLogger(SnapshotUpdater.class);
	private boolean				close	= false;

	@Override
	public void run() {

		super.run();

		if (!close) {
			for (;;) {
				try {
					sleep(50);
				} catch (InterruptedException e2) {
					log.error("infinite loop pre-sleep error", e2);
				}
				if (AppContext.getInstance().getCurrTool() == PixTool.PICK) {
					try {

						sleep(1000);

						Platform.runLater(() -> {

							PixTab tab = GuiFacade.getInstance().getActiveTab();
							Canvas canvas = tab.getCanvas();
							PickColorAction.setSnapshot(canvas.snapshot(null, null));
						});

					} catch (InterruptedException e) {
						log.error("infinite loop sleep interrupted", e);
					}
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
