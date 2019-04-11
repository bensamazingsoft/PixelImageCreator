package com.ben.pixcreator.application.image.layer.modifier.impl;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;
import com.ben.pixcreator.application.image.layer.sampler.LayerSampler;

public class SampleModifier implements IModifier {

	private Effect fx;

	public SampleModifier(Effect effect) {
		this.fx = effect;
	}

	@SuppressWarnings("unused")
	@Override
	public ALayer modify(ALayer layer) throws EffectException {

		if (!(layer instanceof PixLayer)) {
			throw new EffectException("not a PixLayer");
		}

		PixLayer pxLayer = (PixLayer) layer.duplicate();
		Double factor = (Double) fx.getParams().get(EffectParams.Param.SAMPLEFACTOR).getValue();

		LayerSampler sampler = new LayerSampler(pxLayer);

		if (factor < 1) {
			pxLayer = sampler.div((int) Math.ceil(1 / factor), (int) Math.ceil(1 / factor));
		}
		if (factor > 1) {
			pxLayer = sampler.mult(factor.intValue(), factor.intValue());
		}

		return pxLayer;
	}

}
