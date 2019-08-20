
package com.ben.pixcreator.application.image.draw.factory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.draw.factory.DrawLayerFactory;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.BakeLayer;
import com.ben.pixcreator.application.pile.BasicPile;
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

	    for (int currIndex = 0; currIndex < drawImage.getLayerPile().getItems().size(); currIndex++)
	    {

		  ALayer layer = drawImage.getLayerPile().getItem(currIndex);

		  if (!(layer instanceof BakeLayer) && layer.isVisible())
		  {

			processLayerEffects(image, drawImage, currIndex);
		  }
	    }
	    return drawImage;
      }


      /**
       * @param image
       * @param drawImage
       * @param currIndex
       * @param layer
       * @throws EffectException
       */
      private static void processLayerEffects(PixImage image, PixImage drawImage, int currIndex) throws EffectException
      {

	    ALayer layer = drawImage.getLayerPile().getItem(currIndex);
	    Pile<Effect> effectPile = new BasicPile<Effect>(AppContext.getInstance().getEffectManager().getImageLayerEffects(image, layer));

	    if (imageHasBakeLayers(image))
	    {

		  addBakeLayersEffectToLayerEffectPile(image, drawImage, currIndex, layer, effectPile);
	    }

	    if (!effectPile.isEmpty())
	    {

		  drawImage.getLayerPile().replace(currIndex, DrawLayerFactory.getDrawLayer(effectPile, layer));

	    }
      }


      /**
       * @param image
       * @return
       */
      private static boolean imageHasBakeLayers(PixImage image)
      {

	    boolean hasBakeLayer = !image.getLayerPile().getAllItems()
			.stream()
			.filter(layer -> layer instanceof BakeLayer)
			.collect(Collectors.toSet())
			.isEmpty();
	    return hasBakeLayer;
      }


      /**
       * @param image
       * @param drawImage
       * @param currIndex
       * @param layer
       * @param effectPile
       */
      private static void addBakeLayersEffectToLayerEffectPile(PixImage image, PixImage drawImage, int currIndex, ALayer layer, Pile<Effect> effectPile)
      {

	    for (int a = currIndex; a < drawImage.getLayerPile().getItems().size(); a++)
	    {

		  ALayer iterationLayer = drawImage.getLayerPile().getItem(a);

		  if (iterationLayer instanceof BakeLayer && layerLockedToIterationLayer(image, layer, iterationLayer))
		  {

			addBakeLayerEffectToEffectPile(image, effectPile, iterationLayer);

		  }

	    }
      }


      /**
       * @param image
       * @param layer
       * @param iterationLayer
       * @param groupLock
       * @param iterationLayer
       * @return
       */
      private static boolean layerLockedToIterationLayer(PixImage image, ALayer layer, ALayer iterationLayer)
      {

	    final Map<ALayer, Set<ALayer>> groupLock = AppContext.getInstance().getGroupLocks().get(image).getGroup();

	    boolean lock = groupLock.computeIfAbsent(iterationLayer, lay -> new HashSet<ALayer>()).contains(layer);
	    return lock;
      }


      /**
       * @param image
       * @param effectPile
       * @param bakeLayer
       */
      private static void addBakeLayerEffectToEffectPile(PixImage image, Pile<Effect> effectPile, ALayer bakeLayer)
      {

	    Pile<Effect> bakeEffectPile = AppContext.getInstance().getEffectManager()
			.getImageLayerEffects(
				    image,
				    bakeLayer);

	    if (!bakeEffectPile.isEmpty())
	    {
		  for (int x = 0; x < bakeEffectPile.getAllItems().size(); x++)
		  {

			effectPile.add(bakeEffectPile.getItem(x));

		  }
	    }
      }

}
