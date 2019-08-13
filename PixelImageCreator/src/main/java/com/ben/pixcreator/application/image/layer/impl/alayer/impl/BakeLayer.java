
package com.ben.pixcreator.application.image.layer.impl.alayer.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.draw.factory.DrawImageFactory;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class BakeLayer extends PicLayer
{

      private static final long serialVersionUID = 1L;


      public BakeLayer()
      {

	    super();

	    Canvas canvas = new Canvas(100, 100);
	    setImage(canvas.snapshot(null, null));

      }


      @Override
      public String toString()
      {

	    return "BakeLayer [image : " + getImage() + "]";
      }


      @Override
      public ALayer duplicate()
      {

	    BakeLayer clone = new BakeLayer();

	    clone.setUuid(getUUID());

	    clone.setVisible(isVisible());

	    clone.setPosition(getPosition());

	    clone.setSizeFactorX(getSizeFactorX());

	    clone.setSizeFactorY(getSizeFactorY());

	    clone.setZoomFactor(getZoomFactor());

	    clone.setImage(getImage());

	    clone.setImageFile(getImageFile());

	    clone.setOpacity(getOpacity());

	    return clone;
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    // No op intended
      }


      /**
       * @return a baked image of this layer. Consists of an image made with all sub-layers.
       * @throws EffectException
       */
      public Image getBakedSnapshot() throws EffectException
      {

	    PixImage image = GuiFacade.getInstance().getActiveimage();

	    if (!image.getLayerPile().getAllItems().contains(this))
	    {

		  return getImage();

	    }

	    Map<ALayer, Boolean> savedLayersVisibilityState = new HashMap<>();
	    Map<ALayer, Double> savedLayersZoomFactors = new HashMap<>();

	    saveImageLayersState(image.getLayerPile(), savedLayersVisibilityState, savedLayersZoomFactors);
	    resetImageLayersState(image.getLayerPile(), savedLayersVisibilityState, savedLayersZoomFactors);

	    Canvas canvas = new Canvas(image.getxSize(), image.getySize());
	    DrawImageFactory.getDrawImage(image).draw(canvas);
	    Image snap = canvas.snapshot(null, null);

	    restoreImageLayersState(savedLayersVisibilityState, savedLayersZoomFactors);

	    return snap;

      }


      /**
       * @param imageLayers
       * @param layerIdx
       * @param savedLayersVisibilityState
       * @param savedLayersZoomFactors
       */
      private void saveImageLayersState(Pile<ALayer> imageLayers, Map<ALayer, Boolean> savedLayersVisibilityState, Map<ALayer, Double> savedLayersZoomFactors)
      {

	    for (ALayer imageLayer : imageLayers.getAllItems())
	    {
		  savedLayersVisibilityState.put(imageLayer, imageLayer.isVisible());

		  if (imageLayer instanceof PicLayer)
		  {
			savedLayersZoomFactors.put(imageLayer, ((PicLayer) imageLayer).getZoomFactor());

		  }
	    }
      }


      /**
       * @param imageLayers
       * @param layerIdx
       * @param savedLayersVisibilityState
       * @param savedLayersZoomFactors
       */
      private void resetImageLayersState(Pile<ALayer> imageLayers, Map<ALayer, Boolean> savedLayersVisibilityState, Map<ALayer, Double> savedLayersZoomFactors)
      {

	    int layerIdx = imageLayers.getIdx(this);

	    for (ALayer imageLayer : imageLayers.getAllItems())
	    {

		  if (imageLayers.getIdx(imageLayer) > layerIdx)
		  {
			imageLayer.setVisible(false);
		  }

		  if (imageLayer instanceof PicLayer)
		  {

			((PicLayer) imageLayer).setZoomFactor(1.0d);
		  }
	    }
      }


      /**
       * @param savedLayersVisibilityState
       * @param savedLayersZoomFactors
       */
      private void restoreImageLayersState(Map<ALayer, Boolean> savedLayersVisibilityState, Map<ALayer, Double> savedLayersZoomFactors)
      {

	    savedLayersVisibilityState.keySet().forEach(layer -> {
		  layer.setVisible(savedLayersVisibilityState.get(layer));
	    });

	    savedLayersZoomFactors.keySet().forEach(layer -> {
		  if (layer instanceof PicLayer)
		  {
			((PicLayer) layer).setZoomFactor(savedLayersZoomFactors.get(layer));
		  }
	    });
      }

}
