package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

public class SizeEffectParams extends EffectParams {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	ParamValue<Double>			xParam;
	ParamValue<Double>			yParam;
	ParamValue<Boolean>			lockRatio;

	public SizeEffectParams(double x, double y, double bypass) {

		xParam = new ParamValue<Double>(x, y, bypass);
		xParam.setValue(100d);
		map.put(Param.XSIZE, xParam);
		yParam = new ParamValue<Double>(x, y, bypass);
		yParam.setValue(100d);
		map.put(Param.YSIZE, yParam);
		lockRatio = new ParamValue<Boolean>(true, false, true);
		lockRatio.setValue(true);
		map.put(Param.KEEPRATIO, lockRatio);

	}

	public void setX(double value) {

		xParam.setValue(value);

		if (lockRatio.getValue()) {
			yParam.setValue(value);
		}

	}

	public void setY(double value) {
		yParam.setValue(value);

		if (lockRatio.getValue()) {
			xParam.setValue(value);
		}

	}

	public void setLock() {
		lockRatio.setValue(!lockRatio.getValue());

	}

	public double getX() {
		return xParam.getValue();

	}

	public double getY() {
		return yParam.getValue();

	}

	public boolean getLock() {
		return lockRatio.getValue();

	}
}
