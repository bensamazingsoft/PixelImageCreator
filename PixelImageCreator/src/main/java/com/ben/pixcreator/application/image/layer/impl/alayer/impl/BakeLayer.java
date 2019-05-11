
package com.ben.pixcreator.application.image.layer.impl.alayer.impl;

import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class BakeLayer extends PicLayer {

	private static final long serialVersionUID = 1L;

	public BakeLayer() {

		super();

		Canvas canvas = new Canvas(100, 100);
		setImage(canvas.snapshot(null, null));

	}

	@Override
	public String toString() {

		return "BakeLayer [image : " + getImage() + "]";
	}

	@Override
	public ALayer duplicate() {

		BakeLayer clone = new BakeLayer();

		clone.setUuid(getUUID());

		clone.setVisible(isVisible());

		clone.setPosition(getPosition());

		clone.setSizeFactorX(getSizeFactorX());

		clone.setSizeFactorY(getSizeFactorY());

		clone.setZoomFactor(getZoomFactor());

		clone.setImage(getImage());

		clone.setImageFile(getImageFile());

		clone.setOpacity(getOpacity());

		return clone;
	}

	@Override
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

	}

	/**
	 * @return a baked image of this layer. Consists of an image made with all
	 *         sub-layers.
	 * @throws EffectException
	 */
	public Image getBakedSnapshot() throws EffectException {

	}

}
