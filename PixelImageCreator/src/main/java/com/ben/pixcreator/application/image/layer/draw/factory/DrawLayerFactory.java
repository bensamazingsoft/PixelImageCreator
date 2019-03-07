
package com.ben.pixcreator.application.image.layer.draw.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.factory.EffectLayerFactory;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.pile.Pile;

/**
 * //instantiate layer decorator by looping through each effect
 * 
 * @author ben
 *
 */
public class DrawLayerFactory {

	public static ALayer getDrawLayer(Pile<Effect> effectPile, ALayer layer) throws EffectException {

		ALayer sourceLayer = layer.duplicate();

		// loop through all effects in the layer,instantiating the decorator, in
		// the pile order
		for (int i = 0; i < effectPile.getAllItems().size(); i++) {

			ALayer fXLayer = EffectLayerFactory.getFXLayer(effectPile.getItem(i), sourceLayer);

			sourceLayer = fXLayer;

		}

		return sourceLayer;
	}

}
