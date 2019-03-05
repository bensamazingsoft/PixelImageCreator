
package com.ben.pixcreator.application.image.layer.effect.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.EffectLayer;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

public class EffectLayerFactory {

	public static ALayer getFXLayer(Effect effect, ALayer sourceLayer) {

		switch (effect.getEffect()) {

		case OPACITY:
			return new EffectLayer(sourceLayer, effect);
		case ENLARGE:
			return new EffectLayer(sourceLayer, effect);
		case SHRINK:
			return new EffectLayer(sourceLayer, effect);
		default:
			break;

		}

		return sourceLayer;
	}

}
