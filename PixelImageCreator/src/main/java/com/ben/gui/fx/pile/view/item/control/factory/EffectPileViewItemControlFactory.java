
package com.ben.gui.fx.pile.view.item.control.factory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.BrightnessSliderControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.HueSliderControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.LumaKeyControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.OpacitySliderControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.ResampleControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.SaturationSliderControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.SizeSliderControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl.TextControl;

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
		case SATURATION:
			return new SaturationSliderControl(fx);
		case BRIGHTNESS:
			return new BrightnessSliderControl(fx);
		case TEXT:
			return new TextControl(fx);
		case RESAMPLE:
			return new ResampleControl(fx);
		case LUMAKEY:
			return new LumaKeyControl(fx);
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
		case SATURATION:
			return AppContext.getInstance().getBundle().getString("saturation");
		case BRIGHTNESS:
			return AppContext.getInstance().getBundle().getString("brightness");
		case TEXT:
			return AppContext.getInstance().getBundle().getString("text");
		case RESAMPLE:
			return AppContext.getInstance().getBundle().getString("resample");
		case LUMAKEY:
			return AppContext.getInstance().getBundle().getString("lumaKey");
		default:
			break;

		}

		return "";
	}

}
