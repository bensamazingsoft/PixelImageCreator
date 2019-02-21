
package com.ben.pixcreator.application.image.layer.effect.applier.impl;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

/**
 * encapsulates the process of applying opacity effect to a layer.
 * 
 * @author ben
 *
 */
public class OpacityEffectApplier implements EffectApplier
{

      private final OpacityEffectParams param;


      public OpacityEffectApplier(OpacityEffectParams params)
      {

	    this.param = params;
      }


      @Override
      public ALayer apply(ALayer source)
      {

	    if (source instanceof PixLayer)
	    {

		  PixLayer drawLayer = ((PixLayer) source).duplicate();

		  ((PixLayer) source).getGrid().forEach((coord, rgb) -> {
			drawLayer.getGrid().put(coord, computeNewColor(rgb));
		  });

		  return drawLayer;
	    }

	    return source;
      }


      private ColorRGB computeNewColor(ColorRGB rgb)
      {

	    return new ColorRGB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getOpacity() * param.getOpacity());
      }

}
