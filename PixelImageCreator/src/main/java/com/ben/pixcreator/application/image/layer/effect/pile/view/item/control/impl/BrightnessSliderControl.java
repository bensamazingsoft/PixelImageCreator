package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams.Param;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.slider.EffectSliderDouble;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class BrightnessSliderControl extends StackPane implements IEffectPileViewItemControl {

	private Slider		slider;
	ParamValue<Double>	params;
	private double		save	= 0;

	public BrightnessSliderControl(Effect fx) {

		params = (ParamValue<Double>) fx.getParams().get(Param.BRIGHTNESS);

		slider = new EffectSliderDouble(params);

		getChildren().add(slider);

		save = slider.getValue();
	}

	@Override
	public void bypass() {
		save();
		slider.setValue(params.getBypass());
		slider.setDisable(true);

	}

	@Override
	public void enable() {
		slider.setDisable(false);
		restore();

	}

	@Override
	public void reset() {
		slider.setValue(params.getBypass());

	}

	@Override
	public void save() {
		save = slider.getValue();

	}

	@Override
	public void restore() {
		slider.setValue(save);

	}

	@Override
	public Node node() {

		return this;

	}

}
