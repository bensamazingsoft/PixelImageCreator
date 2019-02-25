
package com.ben.gui.fx.pile.view.item.control.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.OpacitySliderControl;

public class EffectPileViewItemControlFactory {

	public static IEffectPileViewItemControl getControl(Effect fx) {

		switch (fx.getEffect()) {
		case OPACITY:
			return new OpacitySliderControl(fx);
		case SIZE:
			break;
		default:
			break;

		}

		return null;
	}

}
