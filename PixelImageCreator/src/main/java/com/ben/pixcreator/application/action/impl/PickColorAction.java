
package com.ben.pixcreator.application.action.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger log = LoggerFactory.getLogger(PickColorAction.class);

	private static WritableImage snapshot;

	private final PixTab		tab;
	private final MouseEvent	event;

	public PickColorAction(PixTab activeTab, MouseEvent event) {

		this.tab = activeTab;
		this.event = event;
	}

	@Override
	public void execute() throws Exception {

		Color color = readColorFromEventCoordinates(tab, event);

		final GuiFacade gui = GuiFacade.getInstance();
		Set<Color> colors = gui.getImagesColors().get(gui.getActiveImage()).stream()
				.map(prop -> prop.get())
				.collect(Collectors.toSet());

		if (!colors.contains(color)) {

			addColorToImageColorsAndReloadRoster(color, gui);

		}

		gui.getColorRoster().selectColor(color);

		log.debug(color.toString());

	}

	private void addColorToImageColorsAndReloadRoster(Color color, final GuiFacade gui) {

		SimpleObjectProperty<Color> prop = new SimpleObjectProperty<>();
		prop.set(color);

		gui.getImagesColors().get(gui.getActiveImage()).add(prop);

		gui.getColorRoster().reload(gui.getActiveImage());

	}

	public static Color readColorFromEventCoordinates(PixTab tab, MouseEvent event) {

		Canvas canvas = tab.getCanvas();

		WritableImage snap = snapshot == null ? canvas.snapshot(null, null) : snapshot;

		PixelReader reader = snap.getPixelReader();
		int x = new Double((event).getX()).intValue();
		int y = new Double((event).getY()).intValue();

		final Color color = reader.getColor(x, y);

		return color;
	}

	public static WritableImage getSnapshot() {
		return snapshot;
	}

	public static void setSnapshot(WritableImage snapshot) {
		PickColorAction.snapshot = snapshot;
	}

}
