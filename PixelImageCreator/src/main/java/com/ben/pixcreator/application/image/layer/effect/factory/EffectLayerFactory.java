
package com.ben.pixcreator.application.image.layer.effect.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.OpacityModifier;

public class EffectLayerFactory {

	public static ALayer getFXLayer(Effect effect, ALayer layer) throws EffectException {

		switch (effect.getEffect()) {

		case OPACITY:
			IModifier modifier = new OpacityModifier(layer, effect);
			return modifier.modify(layer);
		case ENLARGE:
			break;
		case SHRINK:
			break;
		default:
			break;

		}

		return layer;
	}

}
