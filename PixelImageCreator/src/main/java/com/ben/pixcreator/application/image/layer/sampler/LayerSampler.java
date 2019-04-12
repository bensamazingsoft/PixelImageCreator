
package com.ben.pixcreator.application.image.layer.sampler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ben.pixcreator.application.color.ColorUtils;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;

import javafx.scene.paint.Color;

public class LayerSampler {

	private PixLayer	pixLayer;
	private Coord		max	= new Coord();
	private Coord		min	= new Coord();

	public LayerSampler(PixLayer pixLayer) {

		this.pixLayer = Objects.requireNonNull(pixLayer);

		max = pixLayer.maxCell();

		min = pixLayer.minCell();

	}

	public PixLayer div(int xDivFactor, int yDivFactor) {

		PixLayer resultLayer = new PixLayer();

		// for each resulting new Coords (multiples of divfactors)
		for (int x = min.getX(); x <= max.getX(); x += xDivFactor) {
			for (int y = min.getY(); y <= max.getY(); y += yDivFactor) {

				List<Color> colors = new ArrayList<>();

				// for each coord to be merged in a new one
				for (int i = 0; i < xDivFactor; i++) {
					for (int j = 0; j < yDivFactor; j++) {
						// if it "exists" in the layer grid
						if (null != pixLayer.getGrid().get(new Coord(x + i, y + j))) {
							colors.add(pixLayer.getGrid().get(new Coord(x + i, y + j)).getFxColor());
						}
					}
				}
				if (!colors.isEmpty()) {
					final int nouvX = x / xDivFactor;
					final int nouvY = y / yDivFactor;
					resultLayer.getGrid().put(new Coord(nouvX, nouvY),
							new ColorRGB(ColorUtils.averageColor(colors)));
				}
			}

		}

		return (PixLayer) resultLayer.offset(
				new Coord(-1 * (min.getX() - resultLayer.minCell().getX()),
						-1 * (min.getY() - resultLayer.minCell().getY())));
	}

	public PixLayer mult(int xMultFactor, int yMultFactor) {

		PixLayer resultLayer = new PixLayer();

		for (Coord coord : pixLayer.getGrid().keySet()) {

			resultLayer.getGrid()
					.putAll(multCell(coord, pixLayer.getGrid().get(coord).getFxColor(), xMultFactor, yMultFactor));

		}

		return (PixLayer) resultLayer.offset(min.mult(xMultFactor - 1));
	}

	private Map<Coord, ColorRGB> multCell(Coord coord, Color color, int xMultFactor, int yMultFactor) {

		Map<Coord, ColorRGB> result = new HashMap<>();

		int newXOrigin = coord.getX() * xMultFactor;
		int newYOrigin = coord.getY() * yMultFactor;

		for (int x = 0; x < xMultFactor; x++) {
			for (int y = 0; y < yMultFactor; y++) {

				result.put(new Coord(newXOrigin + x, newYOrigin + y), new ColorRGB(color));
			}
		}

		return result;
	}

}
