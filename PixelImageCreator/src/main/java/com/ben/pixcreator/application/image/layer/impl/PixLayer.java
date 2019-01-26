
package com.ben.pixcreator.application.image.layer.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.sampler.LayerSampler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PixLayer extends ALayer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Map<Coord, Color>	grid;

	public PixLayer() {

		this.grid = new HashMap<Coord, Color>();
	}

	public PixLayer(Map<Coord, Color> grid) {

		super();
		this.grid = grid;
	}

	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		int xCellSize = (int) Math.floor(canvas.getWidth() / xGridResolution);
		int yCellSize = (int) Math.floor(canvas.getHeight() / yGridResolution);

		if (xCellSize >= 1 || yCellSize >= 1) {

			drawGraphics(canvas, xCellSize, yCellSize);

		} else {
			LayerSampler layerSampler = new LayerSampler(this);

			int xDivFactor = (int) Math.floor(xGridResolution / canvas.getWidth());
			int yDivFactor = (int) Math.floor(yGridResolution / canvas.getHeight());

			PixLayer drawLayer = layerSampler.div(xDivFactor, yDivFactor);

			drawLayer.draw(canvas, 1, 1); // TODO wrong!!

		}

	}

	private void drawGraphics(Canvas canvas, int xCellSize, int yCellSize) {

		GraphicsContext graphic = canvas.getGraphicsContext2D();

		for (Coord cell : getGrid().keySet()) {

			graphic.setFill(getGrid().get(cell));

			graphic.fillRect(xCellSize * cell.getX(), yCellSize * cell.getY(), xCellSize, yCellSize);

		}

	}

	public Map<Coord, Color> getGrid() {

		return grid;
	}

	public void setGrid(Map<Coord, Color> grid) {

		this.grid = grid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((grid == null) ? 0 : grid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PixLayer other = (PixLayer) obj;
		if (grid == null) {
			if (other.grid != null)
				return false;
		} else if (!grid.equals(other.grid))
			return false;
		return true;
	}

}
