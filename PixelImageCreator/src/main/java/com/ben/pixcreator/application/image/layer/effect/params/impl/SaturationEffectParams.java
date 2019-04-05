package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class SaturationEffectParams extends EffectParams {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ParamValue<Double> saturation = new ParamValue<Double>(0.0, 2.0, 1.0);

	public SaturationEffectParams() {

		saturation.setValue(1.0);
		map.put(EffectParams.Param.SATURATION, saturation);

	}

	public void setSaturation(Double val) {
		saturation.setValue(val);
	}

	public double getSaturation() {

		Object res = map.get(EffectParams.Param.SATURATION).getValue();
		return Double.valueOf(res.toString());

	}

	@Override
	public String toString() {
		return "SaturationEffectParams [hue=" + saturation + "]";
	}

}
