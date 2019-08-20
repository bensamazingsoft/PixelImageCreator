
package com.ben.pixcreator.application.image.effect.manager;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.pile.BasicPile;
import com.ben.pixcreator.application.pile.Pile;

/**
 * 
 * encapsulates the Map that holds the effects for each layer and each image
 * 
 * @author bmo
 *
 */
public class EffectManager {

	private final Map<PixImage, Map<ALayer, BasicPile<Effect>>> manager;

	public EffectManager() {

		manager = new HashMap<>();
	}

	public EffectManager(Map<PixImage, Map<ALayer, BasicPile<Effect>>> manager) {

		super();
		this.manager = manager;
	}

	public Map<PixImage, Map<ALayer, BasicPile<Effect>>> getManager() {

		return manager;
	}

	/**
	 * Convenience method, returns the image effects piles (compute if absent)
	 * 
	 * @param image
	 * @return com.ben.pixcreator.application.pile.BasicPile<com.ben.pixcreator.application.image.effect.Effect>
	 */
	public Map<ALayer, BasicPile<Effect>> getImageEffects(PixImage image) {

		return manager.computeIfAbsent(image, k -> new HashMap<ALayer, BasicPile<Effect>>());
	}

	/**
	 * Convenience method, returns the layer effects piles (compute empty BasicPile
	 * if absent)
	 * 
	 * @param image
	 * @param layer
	 * @return com.ben.pixcreator.application.pile.BasicPile<com.ben.pixcreator.application.image.effect.Effect>
	 */
	public Pile<Effect> getImageLayerEffects(PixImage image, ALayer layer) {

		Map<ALayer, BasicPile<Effect>> imageEffects = getImageEffects(image);

		return imageEffects.computeIfAbsent(layer, k -> new BasicPile<Effect>());

	}

}
