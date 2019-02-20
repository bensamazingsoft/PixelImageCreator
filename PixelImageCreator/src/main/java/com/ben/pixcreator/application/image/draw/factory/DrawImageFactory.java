
package com.ben.pixcreator.application.image.draw.factory;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.draw.factory.DrawLayerFactory;
import com.ben.pixcreator.application.image.layer.effect.EffectLayer;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

/**
 * Static factory that process effects of a PixImage. The draw() method of the result image can then be called on a canvas for display.
 * 
 * @author ben
 *
 */
public class DrawImageFactory
{

      public static PixImage getDrawImage(PixImage image)
      {

	    PixImage drawImage = image.duplicate();

	    for (int i = 0; i < drawImage.getLayerList().getItems().size(); i++)
	    {

		  ALayer layer = drawImage.getLayerList().getItem(i);

		  if (layer instanceof EffectLayer)
		  {
			drawImage.getLayerList().replace(i, DrawLayerFactory.getDrawLayer(image, layer));
		  }

	    }

	    return drawImage;
      }

}
