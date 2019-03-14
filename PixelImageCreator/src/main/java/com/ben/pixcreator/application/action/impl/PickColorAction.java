package com.ben.pixcreator.application.action.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleObjectProperty;
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

		Color color = readColorFromEventCoordinates();

		final GuiFacade gui = GuiFacade.getInstance();
		Set<Color> colors = gui.getImagesColors().get(gui.getActiveImage()).stream()
				.map(prop -> prop.get())
				.collect(Collectors.toSet());

		if (!colors.contains(color)) {

			addColorToImageColorsAndReloadRoster(color, gui);

		}

		gui.getColorRoster().selectColor(color);

	}

	private void addColorToImageColorsAndReloadRoster(Color color, final GuiFacade gui) {

		SimpleObjectProperty<Color> prop = new SimpleObjectProperty<>();
		prop.set(color);

		gui.getImagesColors().get(gui.getActiveImage()).add(prop);

		gui.getColorRoster().reload(gui.getActiveImage());

	}

	private Color readColorFromEventCoordinates() {

		Canvas canvas = tab.getCanvas();

		WritableImage snap = canvas.snapshot(null, null);

		PixelReader reader = snap.getPixelReader();
		int x = new Double((event).getX()).intValue();
		int y = new Double((event).getY()).intValue();

		final Color color = reader.getColor(x, y);

		return color;
	}

}
