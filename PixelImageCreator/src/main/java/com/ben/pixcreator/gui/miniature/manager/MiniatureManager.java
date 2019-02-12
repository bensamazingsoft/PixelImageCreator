package com.ben.pixcreator.gui.miniature.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

import javafx.scene.canvas.Canvas;

public class MiniatureManager {

	private Map<ALayer, Canvas> miniatures = new HashMap<>();

	public void addMiniature(ALayer layer, Canvas canvas) {
		miniatures.put(layer, canvas);

	}

	public void update(ALayer layer) {

		if (miniatures.containsKey(layer)) {

			int xGridResolution = 0;
			int yGridResolution = 0;

			if (layer instanceof PixLayer) {

				Coord min = ((PixLayer) layer).getGrid().entrySet().stream()
						.map(Entry::getKey)
						.min(Coord.COMPARATOR).orElse(new Coord());
				Coord max = ((PixLayer) layer).getGrid().entrySet().stream()
						.map(Entry::getKey)
						.max(Coord.COMPARATOR).orElse(new Coord());
				xGridResolution = max.getX() - min.getX();
				yGridResolution = max.getY() - min.getY();
			} else if (layer instanceof PicLayer) {
				// TODO PicLayer case
			}
			layer.draw(miniatures.get(layer), xGridResolution, yGridResolution);
		}

	}

}
