package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.colorpicker;

import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.ColorPicker;

public class ColorControl extends ColorPicker {

	public ColorControl(ParamValue<ColorRGB> colorParam) {
		super();
		setValue(colorParam.getValue().getFxColor());
		valueProperty().addListener((obs, oldVal, newVal) -> {
			colorParam.setValue(new ColorRGB(newVal));

			try {
				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});
	}

}
