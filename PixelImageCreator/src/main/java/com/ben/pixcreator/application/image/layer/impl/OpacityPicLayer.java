package com.ben.pixcreator.application.image.layer.impl;

import javafx.scene.canvas.Canvas;

public class OpacityPicLayer extends PicLayer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final PicLayer		picLayer;

	public OpacityPicLayer(PicLayer source, double opacity) {
		this.picLayer = source;
		this.opacity = opacity * getOpacity();
	}

	@Override
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		double op = canvas.getGraphicsContext2D().getGlobalAlpha();

		canvas.getGraphicsContext2D().setGlobalAlpha(opacity);

		picLayer.draw(canvas, xGridResolution, yGridResolution);

		canvas.getGraphicsContext2D().setGlobalAlpha(op);
	}

}
