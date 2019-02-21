
package com.ben.pixcreator.application.image.layer.effect.applier.factory;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.applier.impl.OpacityEffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;

public class EffectApplierFactory
{

      public static EffectApplier getEffectApplier(Effect effect)
      {

	    switch (effect.getEffect())
	    {
	    case OPACITY:

		  return new OpacityEffectApplier((OpacityEffectParams) effect.getParams());

	    default:
		  break;
	    }

	    return null;
      }
}
