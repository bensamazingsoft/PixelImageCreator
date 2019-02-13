
package com.ben.pixcreator.application.image.layer.sampler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

import javafx.scene.paint.Color;

public class LayerSampler {

	private PixLayer	pixLayer;
	private final Coord	max;

	public LayerSampler(PixLayer pixLayer) {

		this.pixLayer = Objects.requireNonNull(pixLayer);

		// get the max X and Y coord
		int maxX = pixLayer.getGrid().keySet().stream().map(Coord::getX).max((a, b) -> Integer.compare(a, b)).get();

		int maxY = pixLayer.getGrid().keySet().stream().map(Coord::getY).max((a, b) -> Integer.compare(a, b)).get();

		max = new Coord(maxX, maxY);
	}

	public PixLayer div(int xDivFactor, int yDivFactor) {

		PixLayer resultLayer = new PixLayer();

		// for each resulting new Coords (multiples of divfactors)
		for (int x = 0; x <= max.getX(); x += xDivFactor) {
			for (int y = 0; y <= max.getY(); y += yDivFactor) {

				List<Color> colors = new ArrayList<>();

				// for each coord to be merged in a new one
				for (int i = 0; i < xDivFactor; i++) {
					for (int j = 0; j < yDivFactor; j++) {
						// if it "exists" in the layer grid
						if (null != pixLayer.getGrid().get(new Coord(x + i, y + j))) {
							colors.add(pixLayer.getGrid().get(new Coord(x + i, y + j)));
						}
					}
				}
				if (!colors.isEmpty()) {
					resultLayer.getGrid().put(new Coord(x / xDivFactor, y / yDivFactor), averageColor(colors));
				}
			}

		}

		return resultLayer;
	}

	public Color averageColor(List<Color> colors) {

		if (colors.isEmpty()) {
			return Color.WHITE;
		}

		Map<String, Integer> averageMap = colors.stream()
				.map(color -> getRVB(color).entrySet())
				.flatMap(Set::stream)
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> (a + b) / 2));

		return Color.rgb(averageMap.get("red"), averageMap.get("green"), averageMap.get("blue"));
	}

	private Map<String, Integer> getRVB(Color color) {

		Map<String, Integer> rvb = new HashMap<>();

		rvb.put("blue", (int) (color.getBlue() * 255));
		rvb.put("green", (int) (color.getGreen() * 255));
		rvb.put("red", (int) (color.getRed() * 255));

		return rvb;
	}

	public PixLayer mult(int xMultFactor, int yMultFactor) {

		PixLayer resultLayer = new PixLayer();

		for (Coord coord : pixLayer.getGrid().keySet()) {

			resultLayer.getGrid().putAll(multCell(coord, pixLayer.getGrid().get(coord), xMultFactor, yMultFactor));

		}

		return resultLayer;
	}

	private Map<Coord, Color> multCell(Coord coord, Color color, int xMultFactor, int yMultFactor) {
		Map<Coord, Color> result = new HashMap<>();

		int newXOrigin = coord.getX() * xMultFactor;
		int newYOrigin = coord.getY() * yMultFactor;

		for (int x = 0; x < xMultFactor; x++) {
			for (int y = 0; y < yMultFactor; y++) {

				result.put(new Coord(newXOrigin + x, newYOrigin + y), color);
			}
		}

		return result;
	}

}
