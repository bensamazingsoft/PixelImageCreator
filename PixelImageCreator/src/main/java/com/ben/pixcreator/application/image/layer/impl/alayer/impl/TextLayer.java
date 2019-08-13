
package com.ben.pixcreator.application.image.layer.impl.alayer.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
      private String		  fontFamily	   = Font.getDefault().getFamily();
      private ColorRGB		  color		   = new ColorRGB();
      private int		  fontSize	   = 48;
      private TextAlignment	  align		   = TextAlignment.LEFT;
      private VPos		  baseline	   = VPos.TOP;
      private RenderType	  renderType	   = RenderType.FILL;
      /**
       * 
       */
      private FontPosture	  posture	   = FontPosture.REGULAR;
      private FontWeight	  weight	   = FontWeight.NORMAL;

      public static List<Integer> fontSizes	   = Arrays.asList(8, 16, 32, 48, 64, 128);

      private enum RenderType {
	    FILL, STROKE;
      }


      public TextLayer(File imageFile, String text)
      {

	    super(imageFile);
	    this.text = text;
	    renderImage();
      }


      public TextLayer()
      {

	    super();
	    renderImage();
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    int xCellSize = (int) (Math.floor(canvas.getWidth()) / xGridResolution);
	    int yCellSize = (int) (Math.floor(canvas.getHeight()) / yGridResolution);

	    GraphicsContext graph = canvas.getGraphicsContext2D();
	    graph.setFont(Font.font(getFontFamily(), weight, posture, fontSize * getZoomFactor()));
	    graph.setTextAlign(getAlign());
	    graph.setTextBaseline(getBaseline());
	    graph.setFontSmoothingType(getSmoothingType());

	    switch (renderType)
	    {
	    case FILL:
		  graph.setFill(color.getFxColor());
		  graph.fillText(text, getPosition().getX() * xCellSize, getPosition().getY() * yCellSize, Double.MAX_VALUE);
		  break;
	    case STROKE:
		  graph.setStroke(color.getFxColor());
		  graph.strokeText(text, getPosition().getX() * xCellSize, getPosition().getY() * yCellSize,
			      Double.MAX_VALUE);
		  break;
	    }

      }


      private void renderImage()
      {

	    Canvas renderCanvas = new Canvas();
	    PixImage activeImage = GuiFacade.getInstance().getActiveImage();

	    renderCanvas.setWidth(activeImage.getxSize());
	    renderCanvas.setHeight(activeImage.getySize());

	    draw(renderCanvas, activeImage.getxGridResolution(), activeImage.getyGridResolution());

	    SnapshotParameters snapshotParameters = new SnapshotParameters();
	    snapshotParameters.setFill(Color.rgb(0, 0, 0, 0));
	    setImage(renderCanvas.snapshot(snapshotParameters, null));

      }


      @Override
      public Image getImage()
      {

	    renderImage();
	    return super.getImage();
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

	    dup.setUuid(getUUID());

	    dup.setVisible(isVisible());
	    dup.setPosition(getPosition());
	    dup.setSizeFactorX(getSizeFactorX());
	    dup.setSizeFactorY(getSizeFactorY());
	    dup.setZoomFactor(getZoomFactor());
	    dup.setImage(getImage());
	    dup.setImageFile(getImageFile());
	    dup.setOpacity(getOpacity());

	    // dup.setFont(getFont());
	    dup.setText(getText());
	    dup.setAlign(getAlign());
	    dup.setBaseline(getBaseline());
	    dup.setRenderType(getRenderType());
	    dup.setSmoothingType(getSmoothingType());

	    return dup;
      }


      public Font getFont()
      {

	    return Font.font(getFontFamily(), getWeight(), getPosture(), getFontSize());
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


      public FontPosture getPosture()
      {

	    return posture;
      }


      public void setPosture(FontPosture posture)
      {

	    this.posture = posture;
      }


      public FontWeight getWeight()
      {

	    return weight;
      }


      public void setWeight(FontWeight weight)
      {

	    this.weight = weight;
      }


      public String getFontFamily()
      {

	    return fontFamily;
      }


      public void setFontFamily(String fontFamily)
      {

	    this.fontFamily = fontFamily;
      }

}
