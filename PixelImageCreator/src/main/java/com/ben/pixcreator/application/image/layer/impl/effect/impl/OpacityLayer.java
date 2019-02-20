
package com.ben.pixcreator.application.image.layer.impl.effect.impl;

import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.impl.effect.AEffectLayer;
import com.ben.pixcreator.application.image.layer.impl.effect.params.impl.OpacityEffectParams;

public class OpacityLayer extends AEffectLayer
{

      private static final long serialVersionUID = 1L;


      public OpacityLayer(PixLayer layer, OpacityEffectParams params)
      {

	    super(layer, params);
	    effectizer = new OpacityPixEffectizer(params);
      }

      // TODO PicLayer constructor
}
