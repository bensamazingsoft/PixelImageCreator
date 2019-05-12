
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

      }


      /**
       * @return a baked image of this layer. Consists of an image made with all sub-layers.
       * @throws EffectException
       */
      public Image getBakedSnapshot() throws EffectException
      {

	    PixImage image = GuiFacade.getInstance().getActiveimage();
	    Pile<ALayer> layerList = image.getLayerList();

	    if (layerList.getAllItems().contains(this))
	    {

		  int layerIdx = layerList.getIdx(this);
		  Map<ALayer, Boolean> visibility = new HashMap<>();
		  Map<ALayer, Double> zoomFactors = new HashMap<>();

		  for (ALayer layer : layerList.getAllItems())
		  {
			visibility.put(layer, layer.isVisible());
			if (layerList.getIdx(layer) > layerIdx)
			{
			      layer.setVisible(false);
			}

			if (layer instanceof PicLayer)
			{
			      zoomFactors.put(layer, ((PicLayer) layer).getZoomFactor());
			      ((PicLayer) layer).setZoomFactor(1.0d);
			}
		  }

		  Canvas canvas = new Canvas(image.getxSize(), image.getySize());
		  DrawImageFactory.getDrawImage(image).draw(canvas);

		  Image snap = canvas.snapshot(null, null);

		  visibility.keySet().forEach(layer -> {
			layer.setVisible(visibility.get(layer));
		  });

		  zoomFactors.keySet().forEach(layer -> {
			if (layer instanceof PicLayer)
			{
			      ((PicLayer) layer).setZoomFactor(zoomFactors.get(layer));
			}
		  });

		  return snap;

	    }
	    return getImage();

      }

}
