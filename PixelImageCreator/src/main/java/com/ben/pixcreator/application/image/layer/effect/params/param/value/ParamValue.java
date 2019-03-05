
package com.ben.pixcreator.application.image.layer.effect.params.param.value;

import java.io.Serializable;

public class ParamValue<T> implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	protected final T min;

	protected final T	max;
	protected final T	bypass;

	protected T value;

	public ParamValue(T min, T max, T bypass) {

		super();
		this.min = min;
		this.max = max;
		this.bypass = bypass;
	}

	public ParamValue(T value) {

		min = max = bypass = null;
		this.value = value;
	}

	public T getValue() {

		return value;
	}

	public void setValue(T value) {

		this.value = value;
	}

	public T getMin() {

		return min;
	}

	public T getMax() {

		return max;
	}

	public T getBypass() {

		return bypass;
	}

	@Override
	public String toString() {
		return "ParamValue [value=" + value + "]";
	}

}
