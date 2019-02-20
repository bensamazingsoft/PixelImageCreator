
package com.ben.pixcreator.application.image.layer.effect.applier.impl;

import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

public class OpacityPixEffectApplier implements EffectApplier
{

      private final OpacityEffectParams param;


      public OpacityPixEffectApplier(OpacityEffectParams params)
      {

	    this.param = params;
      }


      @Override
      public ALayer apply(ALayer source)
      {

	    PixLayer drawLayer = ((PixLayer) source).duplicate();
	    // TODO apply opacity param value to the drawlayer grid
	    return drawLayer;
      }

}
