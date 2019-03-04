package com.ben.pixcreator.application.image.layer.impl;

import com.ben.pixcreator.application.image.coords.Coord;

import javafx.scene.canvas.Canvas;

public class OpacityPicLayer extends ALayer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final PicLayer		picLayer;
	private final double		opacity;

	public OpacityPicLayer(PicLayer source, double opacity) {
		this.picLayer = source;
		this.opacity = opacity;
	}

	@Override
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		double op = canvas.getGraphicsContext2D().getGlobalAlpha();

		canvas.getGraphicsContext2D().setGlobalAlpha(opacity);

		picLayer.draw(canvas, xGridResolution, yGridResolution);

		canvas.getGraphicsContext2D().setGlobalAlpha(op);
	}

	@Override
	public Memento getMemento() {

		return picLayer.getMemento();
	}

	@Override
	public ALayer duplicate() {

		return new OpacityPicLayer(picLayer, opacity);
	}

	@Override
	public ALayer offset(Coord min) {

		return new OpacityPicLayer((PicLayer) picLayer.offset(min), opacity);
	}

}
