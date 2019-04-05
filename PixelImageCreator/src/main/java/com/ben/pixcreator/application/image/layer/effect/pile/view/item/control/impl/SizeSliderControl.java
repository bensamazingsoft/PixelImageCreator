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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SizeSliderControl extends StackPane implements IEffectPileViewItemControl {

	VBox content = new VBox();

	private Slider		sliderX		= new Slider();
	private Slider		sliderY		= new Slider();
	private CheckBox	lockRatioCb	= new CheckBox();

	ParamValue<Double>	X;
	ParamValue<Double>	Y;
	ParamValue<Boolean>	lockRatio;

	private double saveX = 0;

	private double saveY = 0;

	@SuppressWarnings("unchecked")
	public SizeSliderControl(Effect fx) {

		X = (ParamValue<Double>) fx.getParams().get(Param.XSIZE);
		Y = (ParamValue<Double>) fx.getParams().get(Param.YSIZE);
		lockRatio = (ParamValue<Boolean>) fx.getParams().get(Param.KEEPRATIO);

		sliderX = new EffectSliderDouble(X);
		sliderY = new EffectSliderDouble(Y);
		lockRatioCb = new EffectCheckBox(lockRatio);

		saveX = sliderX.getValue();
		saveY = sliderY.getValue();

		sliderX.valueProperty().addListener((obs, oldVal, newVal) -> {
			if (lockRatio.getValue()) {
				sliderY.setValue((double) newVal);
			}
		});

		sliderY.valueProperty().addListener((obs, oldVal, newVal) -> {
			if (lockRatio.getValue()) {
				sliderX.setValue((double) newVal);
			}
		});

		content.getChildren().add(new Label(AppContext.getInstance().getBundle().getString("width")));
		content.getChildren().add(sliderX);
		content.getChildren().add(new Label(AppContext.getInstance().getBundle().getString("height")));
		content.getChildren().add(sliderY);
		content.getChildren().add(new Label(AppContext.getInstance().getBundle().getString("keepRatio")));
		content.getChildren().add(lockRatioCb);

		getChildren().add(content);
	}

	@Override
	public void bypass() {

		save();

		sliderX.setValue(X.getBypass());
		sliderY.setValue(Y.getBypass());
		lockRatio.setValue(lockRatio.getBypass());

		sliderX.setDisable(true);
		sliderY.setDisable(true);
		lockRatioCb.setDisable(true);

	}

	@Override
	public void enable() {

		restore();
		sliderX.setDisable(false);
		sliderY.setDisable(false);
		lockRatioCb.setDisable(false);

	}

	@Override
	public void reset() {

		X.setValue(X.getBypass());
		Y.setValue(Y.getBypass());
		lockRatio.setValue(lockRatio.getBypass());

	}

	@Override
	public Node node() {

		return this;
	}

	@Override
	public void save() {
		saveX = sliderX.getValue();
		saveY = sliderY.getValue();

	}

	@Override
	public void restore() {

		sliderX.setValue(saveX);
		sliderY.setValue(saveY);

	}

}
