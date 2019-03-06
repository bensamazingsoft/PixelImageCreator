
package com.ben.pixcreator.application.image.layer.effect;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.applier.factory.EffectApplierFactory;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;

import javafx.scene.canvas.Canvas;

public class PicEffectLayer extends PicLayer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	protected final ALayer		sourceLayer;
	private static EffectDesign	effectDesign;
	protected EffectApplier		effectApplier;

	public PicEffectLayer(ALayer layer, Effect effect) {

		super();
		this.sourceLayer = layer;
		this.effectApplier = EffectApplierFactory.getEffectApplier(effect);
		setVisible(true);

	}

	public PicEffectLayer(PicLayer source) {
		super();

		this.sourceLayer = source;

		this.setVisible(source.isVisible());

		this.setPosition(source.getPosition());

		this.setSizeFactorX(source.getSizeFactorX());

		this.setSizeFactorY(source.getSizeFactorY());

		this.setZoomFactor(source.getZoomFactor());

		this.setImage(source.getImage());

		this.setImageFile(source.getImageFile());

	}

	public ALayer getLayer() {

		return sourceLayer;
	}

	@Override
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		effectApplier.apply(sourceLayer).draw(canvas, xGridResolution, yGridResolution);
	}

	public static EffectDesign getEffect() {

		return effectDesign;
	}

}
