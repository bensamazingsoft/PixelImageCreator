package com.ben.pixcreator.application.image.layer.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.TextAlignment;

public class TextLayer extends PicLayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(TextLayer.class);

	private Font font = Font.getDefault();

	public TextLayer(File imageFile, String text) {
		super(imageFile);
		this.text = text;
	}

	private String				text			= AppContext.getInstance().getBundle()
			.getString("TextLayerDefaultText");
	private TextAlignment		align			= TextAlignment.CENTER;
	private VPos				baseline		= VPos.CENTER;
	private FontSmoothingType	smoothingType	= FontSmoothingType.GRAY;
	private RenderType			renderType		= RenderType.FILL;

	private enum RenderType {
		FILL, STROKE;
	}

	@Override
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {
		renderImage();
		super.draw(canvas, xGridResolution, yGridResolution);
	}

	private void renderImage() {

		Canvas renderCanvas = new Canvas();
		GraphicsContext graph = renderCanvas.getGraphicsContext2D();
		graph.setFont(font);
		graph.setTextAlign(getAlign());
		graph.setTextBaseline(getBaseline());
		graph.setFontSmoothingType(getSmoothingType());

		switch (renderType) {
		case FILL:
			graph.fillText(text, 0, 0, Double.MAX_VALUE);
			break;
		case STROKE:
			graph.strokeText(text, 0, 0, Double.MAX_VALUE);
			break;
		}

		setImage(renderCanvas.snapshot(null, null));

	}

	@Override
	public String toString() {

		return "TextLayer [" + text + "]";
	}

	@Override
	public ALayer duplicate() {

		TextLayer dup = (TextLayer) super.duplicate();

		dup.setFont(getFont());
		dup.setText(getText());
		dup.setAlign(getAlign());
		dup.setBaseline(getBaseline());
		dup.setRenderType(getRenderType());
		dup.setSmoothingType(getSmoothingType());

		return dup;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
		renderImage();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		renderImage();
	}

	public TextAlignment getAlign() {
		return align;
	}

	public void setAlign(TextAlignment align) {
		this.align = align;
		renderImage();
	}

	public VPos getBaseline() {
		return baseline;
	}

	public void setBaseline(VPos baseline) {
		this.baseline = baseline;
		renderImage();
	}

	public FontSmoothingType getSmoothingType() {
		return smoothingType;
	}

	public void setSmoothingType(FontSmoothingType smoothingType) {
		this.smoothingType = smoothingType;
		renderImage();
	}

	public RenderType getRenderType() {
		return renderType;
	}

	public void setRenderType(RenderType renderType) {
		this.renderType = renderType;
		renderImage();
	}

}
