
package com.ben.pixcreator.application.image.layer.effect.applier.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.image.layer.effect.EffectLayer;
import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

/**
 * encapsulates the process of applying opacity effect to a layer.
 * 
 * @author ben
 *
 */
public class OpacityEffectApplier implements EffectApplier {

	private static final Logger log = LoggerFactory.getLogger(OpacityEffectApplier.class);

	private final OpacityEffectParams param;

	public OpacityEffectApplier(OpacityEffectParams params) {

		this.param = params;
	}

	@Override
	public EffectLayer apply(ALayer source) {

		source.setOpacity(source.getOpacity() * param.getOpacity());

		return source;
	}

}
