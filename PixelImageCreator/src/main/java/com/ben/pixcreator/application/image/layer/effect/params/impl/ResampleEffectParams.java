package com.ben.pixcreator.application.image.layer.effect.params.impl;

import java.util.Arrays;
import java.util.List;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class ResampleEffectParams extends EffectParams {

	private static final long serialVersionUID = 1L;

	public static List<Double> values = Arrays.asList(0.25, 0.5, 1.0, 2.0, 4.0);

	private ParamValue<Double> factorParam = new ParamValue<>(1.0);

	public ResampleEffectParams() {

		map.put(EffectParams.Param.SAMPLEFACTOR, factorParam);

	}

	public void setFactor(Double factor) {

		if (values.contains(factor)) {
			factorParam.setValue(factor);
		}
	}

	public Double getFactor() {
		return factorParam.getValue();
	}

	@Override
	public String toString() {
		return "ResampleEffectParams [factorParam=" + factorParam.getValue() + "]";
	}
}
