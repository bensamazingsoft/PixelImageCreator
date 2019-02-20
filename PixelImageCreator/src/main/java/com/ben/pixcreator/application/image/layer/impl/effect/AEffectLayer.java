
package com.ben.pixcreator.application.image.layer.impl.effect;

import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.impl.effect.params.EffectParams;

import javafx.scene.canvas.Canvas;

public class AEffectLayer extends PixLayer
{

      /**
       * 
       */
      private static final long	serialVersionUID = 1L;
      private final PixLayer	sourceLayer;
      private static Effect	effect		 = Effect.OPACITY;
      private EffectParams	params;
      protected EffectApplier	effectizer;


      public AEffectLayer(PixLayer layer, EffectParams params)
      {

	    super();
	    this.sourceLayer = layer;
	    this.params = params;

      }


      public PixLayer getLayer()
      {

	    return sourceLayer;
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    ALayer drawLayer = effectizer.apply(sourceLayer);
	    drawLayer.draw(canvas, xGridResolution, yGridResolution);
      }


      public static Effect getEffect()
      {

	    return effect;
      }

}
