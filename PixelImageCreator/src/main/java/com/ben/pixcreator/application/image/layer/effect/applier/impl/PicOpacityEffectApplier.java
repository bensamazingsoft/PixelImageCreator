
package com.ben.pixcreator.application.image.layer.effect.applier.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.image.layer.effect.applier.PicEffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.impl.OpacityPicLayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;

/**
 * encapsulates the process of applying opacity effect to a layer.
 * 
 * @author ben
 *
 */
public class PicOpacityEffectApplier implements PicEffectApplier {

	private static final Logger log = LoggerFactory.getLogger(PicOpacityEffectApplier.class);

	private final OpacityEffectParams param;

	public PicOpacityEffectApplier(OpacityEffectParams params) {

		this.param = params;
	}

	@Override
	public PicLayer apply(PicLayer source) {

		return new OpacityPicLayer((PicLayer) source, (double) param.get(EffectParams.Param.OPACITY).getValue());

	}

}
