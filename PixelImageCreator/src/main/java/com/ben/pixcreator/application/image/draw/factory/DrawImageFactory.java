
package com.ben.pixcreator.application.image.draw.factory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.draw.factory.DrawLayerFactory;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
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
       */
      public static PixImage getDrawImage(PixImage image)
      {

	    PixImage drawImage = image.duplicate();

	    // loop through all layers
	    for (int i = 0; i < drawImage.getLayerList().getItems().size(); i++)
	    {

		  Pile<Effect> effectPile = AppContext.getInstance().getEffectManager().getImageLayerEffects(image, image.getLayerList().getItem(i));

		  ALayer layer = drawImage.getLayerList().getItem(i);

		  // check visibility here cause if no effect, no need to compute a non visible effect layer
		  if (!effectPile.isEmpty() && layer.isVisible())
		  {

			drawImage.getLayerList().replace(i, DrawLayerFactory.getDrawLayer(effectPile, layer));

		  }
	    }

	    return drawImage;
      }

}
