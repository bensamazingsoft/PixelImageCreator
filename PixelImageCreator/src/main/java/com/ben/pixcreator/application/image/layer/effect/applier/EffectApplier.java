
package com.ben.pixcreator.application.image.layer.effect.applier;

import com.ben.pixcreator.application.image.layer.impl.ALayer;

public interface EffectApplier {

	/**
	 * Apply effect to the layer and returns the 'decorated' new layer
	 * 
	 * @param source
	 * @return
	 */
	public ALayer apply(ALayer source);

}
