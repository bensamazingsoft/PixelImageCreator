
package com.ben.pixcreator.application.image.layer.impl.effect.params.impl;

import com.ben.pixcreator.application.image.layer.impl.effect.params.EffectParams;

public class OpacityEffectParams extends EffectParams<Double>
{

      public OpacityEffectParams()
      {

	    super();
	    map.put(EffectParams.Param.OPACITY, 1.0);
      }


      public void setOpacity(Double value)
      {

	    map.put(EffectParams.Param.OPACITY, value);
      }


      public double getOpacity()
      {

	    return map.get(EffectParams.Param.OPACITY);
      }

}
