
package com.ben.pixcreator.application.image.draw.factory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.draw.factory.DrawLayerFactory;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.BakeLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PicLayer;
import com.ben.pixcreator.application.pile.Pile;

/**
 * Static factory that process effects of a PixImage. The draw() method of the result image can then be called on a canvas for display.
 * 
 * @author ben
 *
 */
public class DrawImageFactory
{

      /**
       * Static factory method that process effects of a PixImage. The draw() method of the result image can then be called on a canvas for display.
       * 
       * @param image
       * @return
       * @throws EffectException
       */
      public static PixImage getDrawImage(PixImage image) throws EffectException
      {

	    PixImage drawImage = image.duplicate();
	    preBake(drawImage, image);
	    // loop through all layers
	    for (int i = 0; i < drawImage.getLayerList().getItems().size(); i++)
	    {

		  ALayer layer = drawImage.getLayerList().getItem(i);

		  Pile<Effect> effectPile = AppContext.getInstance().getEffectManager().getImageLayerEffects(image,
			      layer);

		  // check visibility here : no effect = no need to compute a non
		  // visible effect layer
		  if (!effectPile.isEmpty() && layer.isVisible())
		  {

			drawImage.getLayerList().replace(i, DrawLayerFactory.getDrawLayer(effectPile, layer));

		  }
	    }
	    return drawImage;
      }


      private static void preBake(PixImage drawImage, PixImage image) throws EffectException
      {

	    for (int i = 0; i < drawImage.getLayerList().getItems().size(); i++)
	    {

		  ALayer layer = drawImage.getLayerList().getItem(i);

		  if (layer instanceof BakeLayer)
		  {

			PicLayer bakedPicLayer = ((BakeLayer) layer).getBakedPicLayer();

			// Pile<Effect> effectPile = AppContext.getInstance().getEffectManager().getImageLayerEffects(image,
			// layer);

			drawImage.getLayerList().replace(i, bakedPicLayer);

		  }
	    }

      }

}
