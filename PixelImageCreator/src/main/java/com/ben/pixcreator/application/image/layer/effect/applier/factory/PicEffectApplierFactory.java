
package com.ben.pixcreator.application.image.layer.effect.applier.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.applier.PicEffectApplier;
import com.ben.pixcreator.application.image.layer.effect.applier.impl.PicOpacityEffectApplier;
import com.ben.pixcreator.application.image.layer.effect.applier.impl.SizeEffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SizeEffectParams;

public class PicEffectApplierFactory {

	public static PicEffectApplier getEffectApplier(Effect effect) {

		switch (effect.getEffect()) {
		case OPACITY:

			return new PicOpacityEffectApplier((OpacityEffectParams) effect.getParams());

		case ENLARGE:

			return new SizeEffectApplier((SizeEffectParams) effect.getParams());

		default:
			break;
		}

		return null;
	}
}
