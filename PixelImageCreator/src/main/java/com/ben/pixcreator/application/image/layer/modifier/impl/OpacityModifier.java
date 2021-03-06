package com.ben.pixcreator.application.image.layer.modifier.impl;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.EffectType;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;

public class OpacityModifier implements IModifier {

	private Effect fx;

	public OpacityModifier(Effect fx) {

		super();
		this.fx = fx;
	}

	@Override
	public ALayer modify(ALayer layer) throws EffectException {

		if (fx.getEffect() != EffectType.OPACITY) {
			throw new EffectException("Effect design is not OPACITY");
		}

		if (fx.getParams() instanceof OpacityEffectParams) {

			OpacityEffectParams param = (OpacityEffectParams) fx.getParams();

			double fxOp = param.getOpacity();
			double layerOp = layer.getOpacity();

			layer.setOpacity(fxOp * layerOp);

		}

		return layer;
	}

}
