package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.slider;

import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.Slider;

public class EffectSlider extends Slider {

	public EffectSlider(ParamValue<Double> param) {

		super();

		setMin(param.getMin());
		setMax(param.getMax());
		setValue(param.getValue());

		valueProperty().addListener((obs, oldVal, newVal) -> {

			param.setValue((Double) newVal);

			try {
				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});
	}

}
