
package com.ben.pixcreator.application.image.layer.effect.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.OpacityModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.SizeModifier;

public class EffectLayerFactory {

	public static ALayer getFXLayer(Effect effect, ALayer layer) throws EffectException {

		switch (effect.getEffect()) {

		case OPACITY:
			IModifier opModifier = new OpacityModifier(layer, effect);
			return opModifier.modify(layer);

		case ENLARGE:
			IModifier sizeModifierEnlarge = new SizeModifier(layer, effect);
			return sizeModifierEnlarge.modify(layer);

		case SHRINK:
			IModifier sizeModifierShrink = new SizeModifier(layer, effect);
			return sizeModifierShrink.modify(layer);

		default:
			break;

		}

		return layer;
	}

}
