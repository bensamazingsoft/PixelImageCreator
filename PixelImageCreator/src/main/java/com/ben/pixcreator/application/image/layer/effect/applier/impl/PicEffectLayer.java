package com.ben.pixcreator.application.image.layer.effect.applier.impl;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.applier.PicEffectApplier;
import com.ben.pixcreator.application.image.layer.effect.applier.factory.PicEffectApplierFactory;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;

import javafx.scene.canvas.Canvas;

public class PicEffectLayer extends PicLayer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	protected final PicLayer	sourceLayer;
	private static EffectDesign	effectDesign;
	protected PicEffectApplier	effectApplier;

	public PicEffectLayer(PicLayer layer, Effect effect) {
		this(layer);
		this.effectApplier = PicEffectApplierFactory.getEffectApplier(effect);

	}

	public PicEffectLayer(PicLayer source) {

		super();
		this.sourceLayer = source;

		setVisible(source.isVisible());

		setPosition(source.getPosition());

		setSizeFactorX(source.getSizeFactorX());

		setSizeFactorY(source.getSizeFactorY());

		setZoomFactor(source.getZoomFactor());

		setImage(source.getImage());

		setImageFile(source.getImageFile());

		effectApplier = new PicEffectApplier() {

			@Override
			public PicLayer apply(PicLayer source) {

				return source;
			}
		};

	}

	public ALayer getLayer() {

		return sourceLayer;
	}

	@Override
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		PicLayer drawLayer = effectApplier.apply(sourceLayer);
		drawLayer.draw(canvas, xGridResolution, yGridResolution);
	}

	public static EffectDesign getEffect() {

		return effectDesign;
	}

	@Override
	public Memento getMemento() {

		return sourceLayer.getMemento();
	}

	@Override
	public ALayer duplicate() {

		return sourceLayer.duplicate();
	}

	@Override
	public ALayer offset(Coord min) {

		return sourceLayer.offset(min);
	}

}
