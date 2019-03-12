
package com.ben.pixcreator.application.image.layer.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
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
		// log.debug("Drawing " + toString());
		// log.debug("canvas width : " + canvas.getWidth() + " height : " +
		// canvas.getHeight() + "xCellSize :" + xCellSize
		// + " yCellSize : " + yCellSize);

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

		graphic.setGlobalAlpha(getOpacity());

		for (Coord cell : getGrid().keySet()) {

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

}
