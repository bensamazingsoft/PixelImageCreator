package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class LumaKeyEffectParams extends EffectParams {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	ParamValue<Double> param = new ParamValue<Double>(0.0, 1.0, 1.0);

	public LumaKeyEffectParams() {

		param.setValue(1.0);
		map.put(EffectParams.Param.LUMAKEY, param);

	}

	public void setFactor(Double value) {

		param.setValue(value);

		map.put(EffectParams.Param.LUMAKEY, param);
	}

	public double getFactor() {

		final Object param = map.get(EffectParams.Param.LUMAKEY).getValue();
		final double value = Double.valueOf(param.toString());
		return value;
	}

	@Override
	public String toString() {
		return "LumaKeyEffectParams [LUMAKEY=" + param.getValue() + "]";
	}

}
