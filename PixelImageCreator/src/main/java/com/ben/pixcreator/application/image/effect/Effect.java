
package com.ben.pixcreator.application.image.effect;

import java.io.Serializable;

import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;

/**
 * Encapsulates an effect
 * 
 * @author ben
 *
 */
public class Effect implements Serializable {

	/**
	* 
	*/
	private static final long	serialVersionUID	= 1L;
	private final EffectDesign	effect;
	private final EffectParams	params;

	public Effect(EffectDesign effect, EffectParams params) {

		super();
		this.effect = effect;
		this.params = params;
	}

	public EffectParams getParams() {

		return params;
	}

	public EffectDesign getEffect() {

		return effect;
	}

	@Override
	public String toString() {
		return "Effect [effect=" + effect + ", params=" + params + "]";
	}

}
