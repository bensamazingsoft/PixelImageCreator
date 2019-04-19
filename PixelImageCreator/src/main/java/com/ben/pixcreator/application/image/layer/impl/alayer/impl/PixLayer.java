
package com.ben.pixcreator.application.image.layer.impl.alayer.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.sampler.LayerSampler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PixLayer extends ALayer {

	/**
	 * 
	 */

	@SuppressWarnings("unused")
	private static final Logger		log					= LoggerFactory.getLogger(PixLayer.class);
	private static final long		serialVersionUID	= 1L;
	private Map<Coord, ColorRGB>	grid;

	public PixLayer() {

		this.grid = new HashMap<Coord, ColorRGB>();

		visible.set(true);
	}

	public PixLayer(Map<Coord, ColorRGB> grid)

	{

		this();
		this.grid = grid;
	}

	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		int xCellSize = (int) Math.floor(canvas.getWidth() / xGridResolution);
		int yCellSize = (int) Math.floor(canvas.getHeight() / yGridResolution);

		if (xCellSize >= 1 && yCellSize >= 1) {

			drawGraphics(canvas, xCellSize, yCellSize);

		} else {
			// log.debug("Using LayerSample for layer " + toString());
			LayerSampler layerSampler = new LayerSampler(this);

			int xDivFactor = (int) Math.ceil(xGridResolution / canvas.getWidth());
			int yDivFactor = (int) Math.ceil(yGridResolution / canvas.getHeight());

			PixLayer drawLayer = layerSampler.div(xDivFactor, yDivFactor);

			drawLayer.draw(canvas, xGridResolution / xDivFactor, yGridResolution / yDivFactor);

		}

	}

	private void drawGraphics(Canvas canvas, int xCellSize, int yCellSize) {

		GraphicsContext graphic = canvas.getGraphicsContext2D();
		final double layerOpacity = getOpacity();

		for (Coord cell : getGrid().keySet()) {

			final double cellOpacity = getGrid().get(cell).getOpacity();
			graphic.setGlobalAlpha(layerOpacity * cellOpacity);

			graphic.setFill(getGrid().get(cell).getFxColor());

			graphic.fillRect(xCellSize * cell.getX(), yCellSize * cell.getY(), xCellSize, yCellSize);

		}

		graphic.setGlobalAlpha(1.0);

	}

	public Map<Coord, ColorRGB> getGrid()

	{

		return grid;
	}

	public void setGrid(Map<Coord, ColorRGB> grid)

	{

		this.grid = grid;
	}

	public class Memento extends ALayer.Memento {

		private Map<Coord, ColorRGB> grid;

		protected Memento(ALayer layer) {

			super(layer);

		}

		@Override
		protected void init(ALayer layer) {

			grid = ((PixLayer) layer).getGrid();

		}

		@Override
		public void restore() {

			((PixLayer) layer).setGrid(grid);

		}

	}

	@Override
	public String toString() {

		return "PixLayer [" + uuid + "]";
	}

	@Override
	public Memento getMemento() {

		return new PixLayer.Memento(this);
	}

	@Override
	public PixLayer duplicate() {

		PixLayer duplicate = new PixLayer(new HashMap<>(this.grid));
		duplicate.setUuid(getUUID());
		duplicate.setVisible(isVisible());
		duplicate.setOpacity(getOpacity());
		return duplicate;
	}

	/*
	 * Builder that returns a new PixLayer which grid is offsetted by the param
	 * coord values.
	 */
	@Override
	public ALayer offset(Coord min) {

		Map<Coord, ColorRGB> offset = new HashMap<>();

		this.getGrid().forEach((coord, color) -> {
			offset.put(new Coord(coord.getX() - min.getX(), coord.getY() - min.getY()), color);
		});
		this.setGrid(offset);

		return this;
	}

	public Coord minCell() {
		// get the min X and Y coord
		int minX = getGrid().keySet().stream().map(Coord::getX).min((a, b) -> Integer.compare(a, b)).get();

		int minY = getGrid().keySet().stream().map(Coord::getY).min((a, b) -> Integer.compare(a, b)).get();

		return new Coord(minX, minY);
	}

	public Coord maxCell() {
		// get the max X and Y coord
		int maxX = getGrid().keySet().stream().map(Coord::getX).max((a, b) -> Integer.compare(a, b)).get();

		int maxY = getGrid().keySet().stream().map(Coord::getY).max((a, b) -> Integer.compare(a, b)).get();

		return new Coord(maxX, maxY);
	}

}
