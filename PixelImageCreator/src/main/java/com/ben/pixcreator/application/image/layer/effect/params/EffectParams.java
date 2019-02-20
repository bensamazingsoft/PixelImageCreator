
package com.ben.pixcreator.application.image.layer.effect.params;

import java.util.HashMap;
import java.util.Map;

public class EffectParams<T>
{

      public static enum Param {
	    OPACITY, XSIZE, YSIZE;
      }

      protected Map<Param, T> map = new HashMap<>();


      public T put(Param param, T value)
      {

	    return map.put(param, value);
      }


      public T get(Param param)
      {

	    return map.get(param);
      }

}
