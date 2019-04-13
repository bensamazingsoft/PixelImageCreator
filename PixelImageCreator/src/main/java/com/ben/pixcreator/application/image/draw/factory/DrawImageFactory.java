
package com.ben.pixcreator.application.image.draw.factory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.draw.factory.DrawLayerFactory;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.BakeLayer;
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

	    // loop through all layers
	    for (int i = 0; i < drawImage.getLayerList().getItems().size(); i++)
	    {

		  ALayer layer = drawImage.getLayerList().getItem(i);

		  // process non BakeLayer
		  if (!(layer instanceof BakeLayer))
		  {
			// retrieve layer effects
			Pile<Effect> effectPile = new Pile<Effect>(AppContext.getInstance().getEffectManager().getImageLayerEffects(image,
				    layer));

			for (int a = i; a < drawImage.getLayerList().getItems().size(); a++)
			{

			      ALayer layer2 = drawImage.getLayerList().getItem(a);

			      if (layer2 instanceof BakeLayer)
			      {

				    Pile<Effect> bakeEffectPile = AppContext.getInstance().getEffectManager().getImageLayerEffects(image,
						layer2);

				    if (!bakeEffectPile.isEmpty())
				    {
					  for (int x = 0; x < bakeEffectPile.getAllItems().size(); x++)
					  {

						effectPile.add(bakeEffectPile.getItem(x));

					  }
				    }

			      }

			}
			// check visibility here : no effect = no need to compute a non
			// visible effect layer
			if (!effectPile.isEmpty() && layer.isVisible())
			{

			      drawImage.getLayerList().replace(i, DrawLayerFactory.getDrawLayer(effectPile, layer));

			}
		  }
	    }
	    return drawImage;
      }

}
