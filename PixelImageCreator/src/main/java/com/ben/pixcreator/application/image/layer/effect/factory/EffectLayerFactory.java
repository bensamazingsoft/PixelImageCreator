
package com.ben.pixcreator.application.image.layer.effect.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.applier.impl.PicEffectLayer;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;

public class EffectLayerFactory {

	public static ALayer getFXLayer(Effect effect, ALayer sourceLayer) {

		if (sourceLayer instanceof PicLayer)
			return new PicEffectLayer((PicLayer) sourceLayer, effect);

		return sourceLayer;

	}

}
