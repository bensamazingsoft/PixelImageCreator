package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class HueEffectParams extends EffectParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ParamValue<Double> hue = new ParamValue<Double>(0.0, 360.0, 0.0);

	public HueEffectParams() {

		hue.setValue(0.0);
		map.put(EffectParams.Param.HUE, hue);

	}

	public void setHue(Double val) {
		hue.setValue(val);
	}

	public double getHue() {

		Object res = map.get(EffectParams.Param.HUE).getValue();
		return Double.valueOf(res.toString());

	}

	@Override
	public String toString() {
		return "HueEffectParams [hue=" + hue + "]";
	}

}
