package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.choicebox;

import java.util.List;

import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class ListChoiceControl<T> extends ChoiceBox<T> {

	public ListChoiceControl(List<T> items, ParamValue<T> param) {

		setItems(FXCollections.observableArrayList(items));
		getSelectionModel().select(param.getValue());

		getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			param.setValue(newVal);

			try {
				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		});

	}

}
