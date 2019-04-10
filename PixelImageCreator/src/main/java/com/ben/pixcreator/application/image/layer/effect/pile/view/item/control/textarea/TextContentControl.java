package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.textarea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.TextArea;

public class TextContentControl extends TextArea {

	private static final Logger log = LoggerFactory.getLogger(TextContentControl.class);

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

		// addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		// log.debug("setFocused(true)");
		// setFocused(true);
		// });

	}

}
