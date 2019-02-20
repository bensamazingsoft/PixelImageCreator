
package com.ben.pixcreator.application.image.layer.effect;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.applier.factory.EffectApplierFactory;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

import javafx.scene.canvas.Canvas;

public class EffectLayer extends ALayer
{

      /**
       * 
       */
      private static final long	serialVersionUID = 1L;
      protected final PixLayer	sourceLayer;
      private static Effect	effect;
      protected EffectApplier	effectApplier;


      public EffectLayer(PixLayer layer, EffectParams<?> params)
      {

	    super();
	    this.sourceLayer = layer;
	    this.effectApplier = EffectApplierFactory.getEffectApplier(params);

      }


      public PixLayer getLayer()
      {

	    return sourceLayer;
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    ALayer drawLayer = effectApplier.apply(sourceLayer);
	    drawLayer.draw(canvas, xGridResolution, yGridResolution);
      }


      public static Effect getEffect()
      {

	    return effect;
      }


      @Override
      public Memento getMemento()
      {

	    return sourceLayer.getMemento();
      }


      @Override
      public ALayer duplicate()
      {

	    return sourceLayer.duplicate();
      }


      @Override
      public ALayer offset(Coord min)
      {

	    return sourceLayer.offset(min);
      }

}
