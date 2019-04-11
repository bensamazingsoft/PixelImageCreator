
package com.ben.pixcreator.application.image.layer.effect.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class EffectParams implements Serializable {

	/**
	* 
	*/
	private static final long			serialVersionUID	= 1L;
	protected Map<Param, ParamValue<?>>	map					= new HashMap<>();

	public EffectParams() {

		super();

	}

	public static enum Param {
		OPACITY, XSIZE, YSIZE, KEEPRATIO, HUE, SATURATION, BRIGHTNESS, COLOR, FONTFAMILY, FONTSIZE, FONTWEIGHT, FONTPOSTURE, TEXT, TXTALIGN, TXTBASELINE, SAMPLEFACTOR;
	}

	public ParamValue<?> put(Param param, ParamValue<?> value) {

		return map.put(param, value);
	}

	public ParamValue<?> get(Param param) {

		return map.get(param);
	}

}
