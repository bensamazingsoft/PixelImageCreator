
package com.ben.pixcreator.application.image.layer.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class TextLayer extends PicLayer
{

      /**
       * 
       */
      private static final long	  serialVersionUID = 1L;

      @SuppressWarnings("unused")
      private static final Logger log		   = LoggerFactory.getLogger(TextLayer.class);

      private FontSmoothingType	  smoothingType	   = FontSmoothingType.GRAY;

      private String		  text		   = AppContext.getInstance().getBundle()
		  .getString("TextLayerDefaultText");

      // font parameters
      private Font		  font		   = Font.getDefault();
      private ColorRGB		  color		   = new ColorRGB();
      private int		  fontSize	   = 48;
      private TextAlignment	  align		   = TextAlignment.LEFT;
      private VPos		  baseline	   = VPos.TOP;
      private RenderType	  renderType	   = RenderType.FILL;
      private FontPosture	  posture	   = FontPosture.REGULAR;
      private FontWeight	  weight	   = FontWeight.NORMAL;

      private enum RenderType {
	    FILL, STROKE;
      }


      public TextLayer(File imageFile, String text)
      {

	    super(imageFile);
	    this.text = text;
	    font = Font.font(fontSize);
	    renderImage();
      }


      public TextLayer()
      {

	    super();
	    font = Font.font(fontSize);
	    renderImage();
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    // renderImage();
	    // super.draw(canvas, xGridResolution, yGridResolution);

	    GraphicsContext graph = canvas.getGraphicsContext2D();
	    graph.setFont(Font.font(font.getFamily(), weight, posture, fontSize * getZoomFactor()));

	    graph.setTextAlign(getAlign());
	    graph.setTextBaseline(getBaseline());
	    graph.setFontSmoothingType(getSmoothingType());

	    switch (renderType)
	    {
	    case FILL:
		  graph.setFill(color.getFxColor());
		  graph.fillText(text, 0, 0, Double.MAX_VALUE);
		  break;
	    case STROKE:
		  graph.setStroke(color.getFxColor());
		  graph.strokeText(text, 0, 0, Double.MAX_VALUE);
		  break;
	    }

      }


      private void renderImage()
      {

	    Canvas renderCanvas = new Canvas();

	    renderCanvas.setWidth(GuiFacade.getInstance().getActiveImage().getxSize());
	    renderCanvas.setHeight(GuiFacade.getInstance().getActiveImage().getySize());

	    // Scene scene = new Scene(new StackPane(renderCanvas));
	    GraphicsContext graph = renderCanvas.getGraphicsContext2D();

	    graph.setFont(font);

	    graph.setTextAlign(getAlign());
	    graph.setTextBaseline(getBaseline());
	    graph.setFontSmoothingType(getSmoothingType());

	    switch (renderType)
	    {
	    case FILL:
		  graph.setFill(color.getFxColor());
		  graph.fillText(text, 0, 0, Double.MAX_VALUE);
		  break;
	    case STROKE:
		  graph.setStroke(color.getFxColor());
		  graph.strokeText(text, 0, 0, Double.MAX_VALUE);
		  break;
	    }

	    SnapshotParameters snapshotParameters = new SnapshotParameters();
	    snapshotParameters.setFill(Color.rgb(0, 0, 0, 0));
	    setImage(renderCanvas.snapshot(snapshotParameters, null));
	    // log.debug("getImage().getWidth()" + renderCanvas.getWidth());

      }


      @Override
      public String toString()
      {

	    return "TextLayer [" + text + "]";
      }


      @Override
      public ALayer duplicate()
      {

	    TextLayer dup = new TextLayer();

	    dup.setVisible(isVisible());
	    dup.setPosition(getPosition());
	    dup.setSizeFactorX(getSizeFactorX());
	    dup.setSizeFactorY(getSizeFactorY());
	    dup.setZoomFactor(getZoomFactor());
	    dup.setImage(getImage());
	    dup.setImageFile(getImageFile());
	    dup.setOpacity(getOpacity());

	    dup.setFont(getFont());
	    dup.setText(getText());
	    dup.setAlign(getAlign());
	    dup.setBaseline(getBaseline());
	    dup.setRenderType(getRenderType());
	    dup.setSmoothingType(getSmoothingType());

	    return dup;
      }


      public Font getFont()
      {

	    return font;
      }


      public void setFont(Font font)
      {

	    this.font = font;

      }


      public String getText()
      {

	    return text;
      }


      public void setText(String text)
      {

	    this.text = text;

      }


      public TextAlignment getAlign()
      {

	    return align;
      }


      public void setAlign(TextAlignment align)
      {

	    this.align = align;

      }


      public VPos getBaseline()
      {

	    return baseline;
      }


      public void setBaseline(VPos baseline)
      {

	    this.baseline = baseline;

      }


      public FontSmoothingType getSmoothingType()
      {

	    return smoothingType;
      }


      public void setSmoothingType(FontSmoothingType smoothingType)
      {

	    this.smoothingType = smoothingType;

      }


      public RenderType getRenderType()
      {

	    return renderType;
      }


      public void setRenderType(RenderType renderType)
      {

	    this.renderType = renderType;

      }


      public int getFontSize()
      {

	    return fontSize;
      }


      public void setFontSize(int fontSize)
      {

	    this.fontSize = fontSize;
      }


      public ColorRGB getColor()
      {

	    return color;
      }


      public void setColor(ColorRGB color)
      {

	    this.color = color;
      }

}
