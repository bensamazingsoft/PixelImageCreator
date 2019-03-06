package com.ben.pixcreator.application.image.layer.effect.applier.impl;

import com.ben.pixcreator.application.image.layer.effect.applier.PicEffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SizeEffectParams;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;

public class SizeEffectApplier implements PicEffectApplier {

	private final SizeEffectParams params;

	public SizeEffectApplier(SizeEffectParams params) {
		this.params = params;
	}

	@Override
	public PicEffectLayer apply(PicLayer source) {

		PicEffectLayer pic = new PicEffectLayer(source);

		pic.setSizeFactorX(params.getX());
		pic.setSizeFactorY(params.getY());

		return pic;

	}

	@Override
	public String toString() {
		return "SizeEffectApplier [params=" + params + "]";
	}

}
