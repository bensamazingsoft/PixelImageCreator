
package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class OpacityEffectParams extends EffectParams {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public OpacityEffectParams() {

		ParamValue<Double> param = new ParamValue<Double>(0.0, 1.0, 1.0);
		param.setValue(1.0);
		map.put(EffectParams.Param.OPACITY, param);

	}

	public void setOpacity(Double value) {

		ParamValue<Double> param = new ParamValue<Double>(0.0, 1.0, 1.0);
		param.setValue(value);

		map.put(EffectParams.Param.OPACITY, param);
	}

	public double getOpacity() {

		return (double) map.get(EffectParams.Param.OPACITY).getValue();
	}

}
