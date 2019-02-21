
package com.ben.pixcreator.application.image.effect.manager;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.pile.Pile;

public class EffectManager
{

      private final Map<PixImage, Map<ALayer, Pile<Effect>>> manager;


      public EffectManager()
      {

	    manager = new HashMap<>();
      }


      public EffectManager(Map<PixImage, Map<ALayer, Pile<Effect>>> manager)
      {

	    super();
	    this.manager = manager;
      }


      public Map<PixImage, Map<ALayer, Pile<Effect>>> getManager()
      {

	    return manager;
      }


      public Map<ALayer, Pile<Effect>> getImageEffects(PixImage image)
      {

	    return manager.computeIfAbsent(image, k -> new HashMap<ALayer, Pile<Effect>>());
      }

}
