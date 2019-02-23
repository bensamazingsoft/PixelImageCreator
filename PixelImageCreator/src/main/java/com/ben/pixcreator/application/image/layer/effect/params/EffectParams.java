
package com.ben.pixcreator.application.image.layer.effect.params;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class EffectParams
{

      protected Map<Param, ParamValue<?>> map = new HashMap<>();


      public EffectParams()
      {

	    super();

      }

      public static enum Param {
	    OPACITY, XSIZE, YSIZE;
      }


      public ParamValue<?> put(Param param, ParamValue<?> value)
      {

	    return map.put(param, value);
      }


      public ParamValue<?> get(Param param)
      {

	    return map.get(param);
      }

}
