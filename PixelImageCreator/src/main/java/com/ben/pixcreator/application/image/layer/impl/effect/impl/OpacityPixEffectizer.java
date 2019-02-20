
package com.ben.pixcreator.application.image.layer.impl.effect.impl;

import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.impl.effect.EffectApplier;
import com.ben.pixcreator.application.image.layer.impl.effect.params.impl.OpacityEffectParams;

public class OpacityPixEffectizer implements EffectApplier
{

      private final OpacityEffectParams param;


      public OpacityPixEffectizer(OpacityEffectParams params)
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
