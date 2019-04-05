package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class BrightnessEffectParams extends EffectParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ParamValue<Double> brightness = new ParamValue<Double>(0.0, 1.0, 1.0);

	public BrightnessEffectParams() {

		brightness.setValue(1.0);
		map.put(EffectParams.Param.BRIGHTNESS, brightness);

	}

	public void setBrightness(Double val) {
		brightness.setValue(val);
	}

	public double getBrightness() {

		Object res = map.get(EffectParams.Param.BRIGHTNESS).getValue();
		return Double.valueOf(res.toString());

	}

	@Override
	public String toString() {
		return "BrightnessEffectParams [hue=" + brightness + "]";
	}

}
