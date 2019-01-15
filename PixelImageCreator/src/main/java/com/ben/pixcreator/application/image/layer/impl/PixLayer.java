
package com.ben.pixcreator.application.image.layer.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.sampler.LayerSampler;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class PixLayer extends ALayer {

	private Map<Coord, Color> grid;

	public PixLayer() {

		this.grid = new HashMap<Coord, Color>();
	}

	public PixLayer(Map<Coord, Color> grid) {

		super();
		this.grid = grid;
	}

	public void show(Canvas canvas, int xGridResolution, int yGridResolution) {

		int xCellSize = (int) Math.floor(canvas.getWidth() / xGridResolution);
		int yCellSize = (int) Math.floor(canvas.getHeight() / yGridResolution);

		if (xCellSize != 0 || yCellSize != 0) {

			// TODO Draw piximage to canvas

		} else {
			LayerSampler layerSampler = new LayerSampler(this);

			int xDivFactor = (int) Math.floor(xGridResolution / canvas.getWidth());
			int yDivFactor = (int) Math.floor(yGridResolution / canvas.getHeight());

			PixLayer drawLayer = layerSampler.div(xDivFactor, yDivFactor);

			drawLayer.show(canvas, 1, 1);

		}

	}

	public Map<Coord, Color> getGrid() {

		return grid;
	}

	public void setGrid(Map<Coord, Color> grid) {

		this.grid = grid;
	}

}
