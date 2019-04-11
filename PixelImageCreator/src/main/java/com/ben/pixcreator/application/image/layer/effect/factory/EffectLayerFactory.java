
package com.ben.pixcreator.application.image.layer.effect.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.BrightnessModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.HueModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.OpacityModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.SampleModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.SaturationModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.SizeModifier;
import com.ben.pixcreator.application.image.layer.modifier.impl.TextModifier;

public class EffectLayerFactory {

	public static ALayer getFXLayer(Effect effect, ALayer layer) throws EffectException {

		switch (effect.getEffect()) {

		case OPACITY:
			IModifier opModifier = new OpacityModifier(effect);
			return opModifier.modify(layer);

		case ENLARGE:
			IModifier sizeModifierEnlarge = new SizeModifier(effect);
			return sizeModifierEnlarge.modify(layer);

		case SHRINK:
			IModifier sizeModifierShrink = new SizeModifier(effect);
			return sizeModifierShrink.modify(layer);

		case HUE:
			IModifier hueModifier = new HueModifier(effect);
			return hueModifier.modify(layer);
		case SATURATION:
			IModifier saturationModifier = new SaturationModifier(effect);
			return saturationModifier.modify(layer);
		case BRIGHTNESS:
			IModifier brightnessModifier = new BrightnessModifier(effect);
			return brightnessModifier.modify(layer);
		case TEXT:
			IModifier textModifier = new TextModifier(effect);
			return textModifier.modify(layer);
		case RESAMPLE:
			IModifier sampleModifier = new SampleModifier(effect);
			return sampleModifier.modify(layer);
		default:
			break;

		}

		return layer;
	}

}
