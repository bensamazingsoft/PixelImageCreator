package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.ResampleEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.choicebox.ListChoiceControl;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ResampleControl extends VBox implements IEffectPileViewItemControl {

	private Effect				fx;
	private Double				factorSave	= 1.0;
	private ChoiceBox<Double>	choiceBox;
	ParamValue<Double>			param;

	public ResampleControl(Effect fx) {
		this.fx = fx;

		param = (ParamValue<Double>) fx.getParams().get(EffectParams.Param.SAMPLEFACTOR);
		choiceBox = new ListChoiceControl<Double>(ResampleEffectParams.values, param);

		getChildren().add(new Label(AppContext.getInstance().getBundle().getString("Resample")));
		getChildren().add(choiceBox);

	}

	@Override
	public void bypass() {
		save();
		param.setValue(param.getBypass());
		choiceBox.setDisable(true);

	}

	@Override
	public void enable() {
		restore();
		choiceBox.setDisable(false);

	}

	@Override
	public void reset() {
		param.setValue(1.0);

	}

	@Override
	public void save() {
		factorSave = param.getValue();

	}

	@Override
	public void restore() {
		param.setValue(factorSave);

	}

	@Override
	public Node node() {

		return this;
	}

}
