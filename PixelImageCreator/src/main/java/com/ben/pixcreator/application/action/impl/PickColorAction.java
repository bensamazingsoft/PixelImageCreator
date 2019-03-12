package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.gui.controls.tab.PixTab;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PickColorAction implements IAction {

	private final PixTab		tab;
	private final MouseEvent	event;

	public PickColorAction(PixTab activeTab, MouseEvent event) {
		this.tab = activeTab;
		this.event = event;
	}

	@Override
	public void execute() throws Exception {

		Color color = readColor();

	}

	private Color readColor() {

		Canvas canvas = tab.getCanvas();

		WritableImage snap = canvas.snapshot(null, null);

		PixelReader reader = snap.getPixelReader();
		int x = new Double((event).getX()).intValue();
		int y = new Double((event).getY()).intValue();

		final Color color = reader.getColor(x, y);

		return color;
	}

}
