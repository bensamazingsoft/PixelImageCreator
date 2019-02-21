
package com.ben.pixcreator.application.image.layer.effect;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.applier.EffectApplier;
import com.ben.pixcreator.application.image.layer.effect.applier.factory.EffectApplierFactory;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

import javafx.scene.canvas.Canvas;

public class EffectLayer extends ALayer
{

      /**
       * 
       */
      private static final long	  serialVersionUID = 1L;
      protected final ALayer	  sourceLayer;
      private static EffectDesign effectDesign;
      protected EffectApplier	  effectApplier;


      public EffectLayer(ALayer layer, Effect effect)
      {

	    super();
	    this.sourceLayer = layer;
	    this.effectApplier = EffectApplierFactory.getEffectApplier(effect);

      }


      public ALayer getLayer()
      {

	    return sourceLayer;
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    ALayer drawLayer = effectApplier.apply(sourceLayer);
	    drawLayer.draw(canvas, xGridResolution, yGridResolution);
      }


      public static EffectDesign getEffect()
      {

	    return effectDesign;
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
