package com.ben.pixcreator.application.color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;

public class ColorUtils {

	/**
	 * @param colors
	 * @return the average Color of the list or Color.WHITE if the list is empty
	 */
	public static Color averageColor(List<Color> colors) {

		if (colors.isEmpty()) {
			return Color.WHITE;
		}

		Map<String, Integer> averageMap = colors.stream()
				.map(color -> getRVB(color).entrySet())
				.flatMap(Set::stream)
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (a, b) -> (a + b) / 2));

		return Color.rgb(averageMap.get("red"), averageMap.get("green"), averageMap.get("blue"));
	}

	private static Map<String, Integer> getRVB(Color color) {

		Map<String, Integer> rvb = new HashMap<>();

		rvb.put("blue", (int) (color.getBlue() * 255));
		rvb.put("green", (int) (color.getGreen() * 255));
		rvb.put("red", (int) (color.getRed() * 255));

		return rvb;
	}

}
