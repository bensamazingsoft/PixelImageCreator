package com.ben.pixcreator.application.image.layer.effect.params.impl;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class TextEffectParams extends EffectParams {

	private static final long serialVersionUID = 1L;

	ParamValue<ColorRGB>		colorParam		= new ParamValue<>(new ColorRGB());
	ParamValue<Integer>			fontSizeParam	= new ParamValue<Integer>(48);
	ParamValue<TextAlignment>	textAlignParam	= new ParamValue<TextAlignment>(TextAlignment.LEFT);
	ParamValue<VPos>			baselineParam	= new ParamValue<VPos>(VPos.TOP);
	ParamValue<FontPosture>		postureParam	= new ParamValue<FontPosture>(FontPosture.REGULAR);
	ParamValue<FontWeight>		fontWeightParam	= new ParamValue<FontWeight>(FontWeight.NORMAL);
	ParamValue<String>			fontFamilyParam	= new ParamValue<String>(Font.getDefault().getFamily());
	ParamValue<String>			textParam		= new ParamValue<String>(
			AppContext.getInstance().getBundle().getString("TextLayerDefaultText"));

	public TextEffectParams() {

		map.put(EffectParams.Param.COLOR, colorParam);
		map.put(EffectParams.Param.FONTSIZE, fontSizeParam);
		map.put(EffectParams.Param.TXTALIGN, textAlignParam);
		map.put(EffectParams.Param.TXTBASELINE, baselineParam);
		map.put(EffectParams.Param.FONTPOSTURE, postureParam);
		map.put(EffectParams.Param.FONTWEIGHT, fontWeightParam);
		map.put(EffectParams.Param.FONTFAMILY, fontFamilyParam);
		map.put(EffectParams.Param.TEXT, textParam);

	}

	public ColorRGB getColor() {
		return colorParam.getValue();
	}

	public void setColor(ColorRGB color) {
		this.colorParam.setValue(color);
	}

	public int getFontSize() {
		return fontSizeParam.getValue();
	}

	public void setFontSize(Integer fontSize) {
		this.fontSizeParam.setValue(fontSize);
	}

	public TextAlignment getTextAlign() {
		return textAlignParam.getValue();
	}

	public void setTextAlign(TextAlignment textAlign) {
		this.textAlignParam.setValue(textAlign);
	}

	public VPos getBaseline() {
		return baselineParam.getValue();
	}

	public void setBaseline(VPos baseline) {
		this.baselineParam.setValue(baseline);
	}

	public FontPosture getPosture() {
		return postureParam.getValue();
	}

	public void setPosture(FontPosture posture) {
		this.postureParam.setValue(posture);
	}

	public FontWeight getFontWeight() {
		return fontWeightParam.getValue();
	}

	public void setFontWeight(FontWeight fontWeight) {
		this.fontWeightParam.setValue(fontWeight);
	}

	public String getFontFamily() {
		return fontFamilyParam.getValue();
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamilyParam.setValue(fontFamily);
	}

	public String getTextParam() {
		return textParam.getValue();
	}

	public void setText(String text) {
		this.textParam.setValue(text);
	}

}
