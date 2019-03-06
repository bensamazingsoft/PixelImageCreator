package com.ben.pixcreator.application.image.layer.effect.applier.impl;

import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SizeEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;

public class SizeEffectApplier implements EffectApplier {

	private final SizeEffectParams params;

	public SizeEffectApplier(SizeEffectParams params) {
		this.params = params;
	}

	@Override
	public ALayer apply(ALayer source) {

		if (source instanceof PicLayer) {

			PicLayer pic = (PicLayer) source.duplicate();

			pic.setSizeFactorX(params.getX());
			pic.setSizeFactorY(params.getY());

			return pic;

		}

		return source;
	}

	@Override
	public String toString() {
		return "SizeEffectApplier [params=" + params + "]";
	}

}
