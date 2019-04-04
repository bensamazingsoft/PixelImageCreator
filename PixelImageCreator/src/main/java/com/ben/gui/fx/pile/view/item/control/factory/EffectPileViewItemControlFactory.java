
package com.ben.gui.fx.pile.view.item.control.factory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.HueSliderControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.OpacitySliderControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.SizeSliderControl;

public class EffectPileViewItemControlFactory {

	public static IEffectPileViewItemControl getControl(Effect fx) {

		switch (fx.getEffect()) {
		case OPACITY:
			return new OpacitySliderControl(fx);
		case ENLARGE:
			return new SizeSliderControl(fx);
		case SHRINK:
			return new SizeSliderControl(fx);
		case HUE:
			return new HueSliderControl(fx);
		default:
			break;

		}

		return null;
	}

	public static String getTitle(Effect fx) {

		switch (fx.getEffect()) {

		case OPACITY:
			return AppContext.getInstance().getBundle().getString("opacity");
		case ENLARGE:
			return AppContext.getInstance().getBundle().getString("enlarge");
		case SHRINK:
			return AppContext.getInstance().getBundle().getString("shrink");
		case HUE:
			return AppContext.getInstance().getBundle().getString("hue");
		default:
			break;

		}

		return "";
	}

}
