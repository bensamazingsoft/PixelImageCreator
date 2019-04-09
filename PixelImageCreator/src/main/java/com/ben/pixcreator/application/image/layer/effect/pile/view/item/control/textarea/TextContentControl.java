package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.textarea;

import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.TextArea;

public class TextContentControl extends TextArea {

	public TextContentControl(ParamValue<String> textContentParam) {

		setText(textContentParam.getValue());

		textProperty().addListener((obs, oldVal, newVal) -> {
			textContentParam.setValue(newVal);

			try {
				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});

	}

}
