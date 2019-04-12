package com.ben.pixcreator.application.image.layer.modifier.impl;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SizeEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;

public class SizeModifier implements IModifier {

	private Effect effect;

	public SizeModifier(Effect effect) {
		this.effect = effect;
	}

	@Override
	public ALayer modify(ALayer layer) throws EffectException {

		if ((effect.getEffect() != EffectDesign.ENLARGE) && (effect.getEffect() != EffectDesign.SHRINK)) {

			throw new EffectException("Effect Design is not ENLARGE or SHRINK");

		}

		if (effect.getParams() instanceof SizeEffectParams) {

			SizeEffectParams params = (SizeEffectParams) effect.getParams();

			double xFactor = params.getX() / 100;
			double yFactor = params.getY() / 100;

			double xLayerFactor = layer.getSizeFactorX();
			double yLayerFactor = layer.getSizeFactorY();

			layer.setSizeFactorX(xLayerFactor * xFactor);
			layer.setSizeFactorY(yLayerFactor * yFactor);

		}

		return layer;
	}

}
