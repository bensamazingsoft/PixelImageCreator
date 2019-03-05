
package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams.Param;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.slider.EffectSlider;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class OpacitySliderControl extends StackPane implements IEffectPileViewItemControl {

	private Slider		slider;
	ParamValue<Double>	params;

	@SuppressWarnings("unchecked")
	public OpacitySliderControl(Effect fx) {

		params = (ParamValue<Double>) fx.getParams().get(Param.OPACITY);

		slider = new EffectSlider(params);

		getChildren().add(slider);
	}

	@Override
	public void bypass() {

		slider.setValue(params.getBypass());
		slider.setDisable(true);

	}

	@Override
	public void enable() {

		slider.setDisable(false);
	}

	@Override
	public void reset() {

		slider.setValue(params.getBypass());

	}

	@Override
	public Node node() {

		return this;
	}

}
