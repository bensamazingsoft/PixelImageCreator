
package com.ben.gui.fx.pile.view.item.control.factory;

import com.ben.gui.fx.pile.view.item.control.impl.OpacitySliderControl;
import com.ben.pixcreator.application.image.effect.Effect;

public class EffectPileViewItemControlFactory
{

      public static EffectPileViewItemControl getControl(Effect fx)
      {

	    switch (fx.getEffect())
	    {
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
