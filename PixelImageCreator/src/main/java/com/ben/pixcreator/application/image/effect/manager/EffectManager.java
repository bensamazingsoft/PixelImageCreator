
package com.ben.pixcreator.application.image.effect.manager;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.effect.Effect;
import com.ben.pixcreator.application.image.layer.impl.effect.params.EffectParams;
import com.ben.pixcreator.application.pile.Pile;

public class EffectManager
{

      private final Map<PixImage, Map<ALayer, Pile<Map<Effect, EffectParams>>>> manager;


      public EffectManager()
      {

	    manager = new HashMap<>();
      }


      public EffectManager(Map<PixImage, Map<ALayer, Pile<Map<Effect, EffectParams>>>> manager)
      {

	    super();
	    this.manager = manager;
      }

}
