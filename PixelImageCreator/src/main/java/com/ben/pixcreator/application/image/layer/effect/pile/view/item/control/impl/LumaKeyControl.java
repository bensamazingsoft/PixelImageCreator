package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams.Param;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.checkbox.EffectCheckBox;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.slider.EffectSliderDouble;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LumaKeyControl extends VBox implements IEffectPileViewItemControl {

	private Slider		slider;
	CheckBox			invertCb;
	ParamValue<Double>	factorParam;
	ParamValue<Boolean>	invertParam;
	private double		save	= 0;
	private boolean		saveInvert;

	@SuppressWarnings("unchecked")
	public LumaKeyControl(Effect fx) {

		factorParam = (ParamValue<Double>) fx.getParams().get(Param.LUMAKEY);
		invertParam = (ParamValue<Boolean>) fx.getParams().get(Param.LUMAKEYINVERT);

		slider = new EffectSliderDouble(factorParam);
		invertCb = new EffectCheckBox(invertParam);

		final Label invertLabel = new Label(AppContext.getInstance().getBundle().getString("invert"));
		final HBox invertContent = new HBox();
		invertContent.getChildren().add(invertLabel);
		invertContent.getChildren().add(invertCb);

		getChildren().add(slider);
		getChildren().add(invertContent);

		save = slider.getValue();
	}

	@Override
	public void bypass() {

		save();
		slider.setValue(factorParam.getBypass());
		slider.setDisable(true);
		invertParam.setValue(false);

	}

	@Override
	public void enable() {

		slider.setDisable(false);
		invertCb.setDisable(false);
		restore();
	}

	@Override
	public void reset() {

		slider.setValue(factorParam.getBypass());
		invertCb.selectedProperty().set(false);

	}

	@Override
	public Node node() {

		return this;
	}

	@Override
	public void save() {
		save = slider.getValue();
		saveInvert = invertCb.selectedProperty().get();

	}

	@Override
	public void restore() {
		slider.setValue(save);
		invertCb.selectedProperty().set(saveInvert);

	}

}
