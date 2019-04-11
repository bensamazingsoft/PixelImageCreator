package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.impl;

import java.util.Arrays;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.IEffectPileViewItemControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.choicebox.ListChoiceControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.colorpicker.ColorControl;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.control.textarea.TextContentControl;
import com.ben.pixcreator.application.image.layer.impl.TextLayer;

import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class TextControl extends VBox implements IEffectPileViewItemControl {

	private Effect fx;

	ParamValue<ColorRGB>		colorParam			= new ParamValue<>(new ColorRGB());
	ParamValue<Integer>			fontSizeParam		= new ParamValue<Integer>(48);
	ParamValue<String>			fontFamilyParam		= new ParamValue<String>(Font.getDefault().getFamily());
	ParamValue<TextAlignment>	textAlignParam		= new ParamValue<TextAlignment>(TextAlignment.LEFT);
	ParamValue<VPos>			baselineParam		= new ParamValue<VPos>(VPos.TOP);
	ParamValue<FontPosture>		postureParam		= new ParamValue<FontPosture>(FontPosture.REGULAR);
	ParamValue<FontWeight>		fontWeightParam		= new ParamValue<FontWeight>(FontWeight.NORMAL);
	ParamValue<String>			textContentParam	= new ParamValue<String>(
			AppContext.getInstance().getBundle().getString("TextLayerDefaultText"));

	final ResourceBundle bundle = AppContext.getInstance().getBundle();

	private ColorRGB		colorParamSave			= new ColorRGB();
	private Integer			fontSizeParamSave		= 48;
	private String			fontFamilyParamSave		= Font.getDefault().getFamily();
	private TextAlignment	textAlignParamSave		= TextAlignment.LEFT;
	private VPos			baselineParamSave		= VPos.TOP;
	private FontPosture		postureParamSave		= FontPosture.REGULAR;
	private FontWeight		fontWeightParamSave		= FontWeight.NORMAL;
	private String			textContentParamSave	= textContentParam.getValue();

	ColorPicker					colorControl;
	ChoiceBox<Integer>			sizeParamControl;
	ChoiceBox<String>			fontFamilyControl;
	ChoiceBox<TextAlignment>	textAlignControl;
	ChoiceBox<VPos>				baselineControl;
	ChoiceBox<FontPosture>		postureControl;
	ChoiceBox<FontWeight>		fontWeightControl;
	TextArea					textControl;

	@SuppressWarnings("unchecked")
	public TextControl(Effect fx) {
		this.fx = fx;

		colorParam = (ParamValue<ColorRGB>) fx.getParams().get(EffectParams.Param.COLOR);
		colorControl = new ColorControl(colorParam);
		fontSizeParam = (ParamValue<Integer>) fx.getParams().get(EffectParams.Param.FONTSIZE);
		sizeParamControl = new ListChoiceControl<Integer>(TextLayer.fontSizes, fontSizeParam);
		fontFamilyParam = (ParamValue<String>) fx.getParams().get(EffectParams.Param.FONTFAMILY);
		fontFamilyControl = new ListChoiceControl<String>(Font.getFamilies(), fontFamilyParam);
		textAlignParam = (ParamValue<TextAlignment>) fx.getParams().get(EffectParams.Param.TXTALIGN);
		textAlignControl = new ListChoiceControl<TextAlignment>(
				Arrays.asList(TextAlignment.values()), textAlignParam);
		baselineParam = (ParamValue<VPos>) fx.getParams().get(EffectParams.Param.TXTBASELINE);
		baselineControl = new ListChoiceControl<VPos>(Arrays.asList(VPos.values()), baselineParam);
		postureParam = (ParamValue<FontPosture>) fx.getParams().get(EffectParams.Param.FONTPOSTURE);
		postureControl = new ListChoiceControl<FontPosture>(Arrays.asList(FontPosture.values()),
				postureParam);
		fontWeightParam = (ParamValue<FontWeight>) fx.getParams().get(EffectParams.Param.FONTWEIGHT);
		fontWeightControl = new ListChoiceControl<FontWeight>(Arrays.asList(FontWeight.values()),
				fontWeightParam);
		textContentParam = (ParamValue<String>) fx.getParams().get(EffectParams.Param.TEXT);
		textControl = new TextContentControl(textContentParam);

		save();

		getChildren().add(new Label(bundle.getString("color")));
		getChildren().add(colorControl);
		getChildren().add(new Label(bundle.getString("size")));
		getChildren().add(sizeParamControl);
		getChildren().add(new Label(bundle.getString("family")));
		getChildren().add(fontFamilyControl);
		getChildren().add(new Label(bundle.getString("alignment")));
		getChildren().add(textAlignControl);
		getChildren().add(new Label(bundle.getString("baseline")));
		getChildren().add(baselineControl);
		getChildren().add(new Label(bundle.getString("style")));
		getChildren().add(postureControl);
		getChildren().add(fontWeightControl);
		getChildren().add(new Label(bundle.getString("text")));
		getChildren().add(textControl);
	}

	@Override
	public void bypass() {
		save();

		colorControl.setValue(colorParam.getBypass().getFxColor());
		colorControl.setDisable(true);

		sizeParamControl.setValue(fontSizeParam.getBypass());
		sizeParamControl.setDisable(true);

		fontFamilyControl.getSelectionModel().select(fontFamilyParam.getBypass());
		fontFamilyControl.setDisable(true);

		textAlignControl.getSelectionModel().select(textAlignParam.getBypass());
		textAlignControl.setDisable(true);

		baselineControl.getSelectionModel().select(baselineParam.getBypass());
		baselineControl.setDisable(true);

		postureControl.getSelectionModel().select(postureParam.getBypass());
		postureControl.setDisable(true);

		fontWeightControl.getSelectionModel().select(fontWeightParam.getBypass());
		fontWeightControl.setDisable(true);

		// textControl.setText(textContentParam.getBypass());
		textControl.setDisable(true);

	}

	@Override
	public void enable() {

		restore();

		colorControl.setDisable(false);

		sizeParamControl.setDisable(false);

		fontFamilyControl.setDisable(false);

		textAlignControl.setDisable(false);

		baselineControl.setDisable(false);

		postureControl.setDisable(false);

		fontWeightControl.setDisable(false);

		textControl.setDisable(false);
	}

	@Override
	public void reset() {

		colorParam.setValue(colorParam.getBypass());
		fontSizeParam.setValue(fontSizeParam.getBypass());
		fontFamilyParam.setValue(fontFamilyParam.getBypass());
		textAlignParam.setValue(textAlignParam.getBypass());
		baselineParam.setValue(baselineParam.getBypass());
		postureParam.setValue(postureParam.getBypass());
		fontWeightParam.setValue(fontWeightParam.getBypass());
		textContentParam.setValue(textContentParam.getBypass());

	}

	@Override
	public void save() {
		colorParamSave = colorParam.getValue();
		fontSizeParamSave = fontSizeParam.getValue();
		fontFamilyParamSave = fontFamilyParam.getValue();
		textAlignParamSave = textAlignParam.getValue();
		baselineParamSave = baselineParam.getValue();
		postureParamSave = postureParam.getValue();
		fontWeightParamSave = fontWeightParam.getValue();
		textContentParamSave = textContentParam.getValue();

	}

	@Override
	public void restore() {

		colorControl.setValue(colorParamSave.getFxColor());

		sizeParamControl.setValue(fontSizeParamSave);

		fontFamilyControl.getSelectionModel().select(fontFamilyParamSave);

		textAlignControl.getSelectionModel().select(textAlignParamSave);

		baselineControl.getSelectionModel().select(baselineParamSave);

		postureControl.getSelectionModel().select(postureParamSave);

		fontWeightControl.getSelectionModel().select(fontWeightParamSave);

	}

	@Override
	public Node node() {
		return this;
	}

}
