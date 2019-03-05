package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.checkbox;

import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.CheckBox;

public class EffectCheckBox extends CheckBox {

	public EffectCheckBox(ParamValue<Boolean> lockRatio) {

		super();

		setSelected(lockRatio.getValue());

		selectedProperty().addListener((obs, oldVal, newVal) -> {

			lockRatio.setValue(newVal);

			try {
				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});

	}

}
