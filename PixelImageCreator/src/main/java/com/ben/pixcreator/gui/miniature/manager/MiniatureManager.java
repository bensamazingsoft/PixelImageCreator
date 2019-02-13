
package com.ben.pixcreator.gui.miniature.manager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class MiniatureManager {

	private static final Logger log = LoggerFactory.getLogger(MiniatureManager.class);

	private Map<ALayer, Canvas> miniatures = new HashMap<>();

	public void addMiniature(ALayer layer, Canvas canvas) {

		miniatures.put(layer, canvas);

	}

	public void update(ALayer layer) {

		Canvas canvas = miniatures.get(layer);

		canvas.getGraphicsContext2D().setFill(Color.WHITE);
		canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		if (miniatures.containsKey(layer)) {

			int xGridResolution = 0;
			int yGridResolution = 0;

			if (layer instanceof PixLayer) {

				int minX = ((PixLayer) layer).getGrid().keySet().stream()
						.mapToInt(Coord::getX)
						.min()
						.orElse(0);
				// log.debug("minX : " + minX);
				int maxX = ((PixLayer) layer).getGrid().keySet().stream()
						.mapToInt(Coord::getX)
						.max()
						.orElse(0);
				// log.debug("maxX : " + maxX);
				int minY = ((PixLayer) layer).getGrid().keySet().stream()
						.mapToInt(Coord::getY)
						.min()
						.orElse(0);
				// log.debug("minY : " + minY);
				int maxY = ((PixLayer) layer).getGrid().keySet().stream()
						.mapToInt(Coord::getY)
						.max()
						.orElse(0);
				// log.debug("maxY : " + maxY);

				xGridResolution = maxX - minX + 1;
				yGridResolution = maxY - minY + 1;

				layer = ((PixLayer) layer).duplicate().offset(new Coord(minX, minY));

			} else if (layer instanceof PicLayer) {
				// TODO PicLayer case
			}
			layer.draw(canvas, xGridResolution, yGridResolution);
		}

	}

}
