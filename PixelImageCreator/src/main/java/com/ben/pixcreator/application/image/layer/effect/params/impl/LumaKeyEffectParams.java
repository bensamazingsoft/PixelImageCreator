package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class LumaKeyEffectParams extends EffectParams {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	ParamValue<Double>	factorParam	= new ParamValue<Double>(0.0, 1.0, 1.0);
	ParamValue<Boolean>	invertParam	= new ParamValue<Boolean>(false);

	public LumaKeyEffectParams() {

		factorParam.setValue(1.0);
		map.put(EffectParams.Param.LUMAKEY, factorParam);
		map.put(EffectParams.Param.LUMAKEYINVERT, invertParam);

	}

	public void setFactor(Double value) {

		factorParam.setValue(value);

		map.put(EffectParams.Param.LUMAKEY, factorParam);
	}

	public void setInvert(Boolean invert) {
		invertParam.setValue(invert);
	}

	public boolean isInvert() {
		return invertParam.getValue();
	}

	public double getFactor() {

		final Object param = map.get(EffectParams.Param.LUMAKEY).getValue();
		final double value = Double.valueOf(param.toString());
		return value;
	}

	@Override
	public String toString() {
		return "LumaKeyEffectParams [LUMAKEY=" + factorParam.getValue() + "]";
	}

}
