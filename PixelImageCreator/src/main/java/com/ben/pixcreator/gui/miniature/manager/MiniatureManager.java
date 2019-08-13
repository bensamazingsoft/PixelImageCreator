
package com.ben.pixcreator.gui.miniature.manager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.BakeLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.TextLayer;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * 
 * Provides and updates the miniatures of the layers.
 * 
 * @author ben
 *
 */
public class MiniatureManager
{

      @SuppressWarnings("unused")
      private static final Logger    log	= LoggerFactory.getLogger(MiniatureManager.class);

      private static double	     MINIATUREHEIGHT;

      private static double	     MINIATUREWIDTH;

      private Map<ALayer, ImageView> miniatures	= new HashMap<>();


      public void addMiniature(ALayer layer, ImageView imageView)
      {

	    miniatures.put(layer, imageView);

      }


      public void update(ALayer layer) throws EffectException
      {

	    resetMiniaturesWidthAndHeight();

	    if (miniatures.containsKey(layer))
	    {
		  ImageView imageView = miniatures.get(layer);
		  Canvas canvas = new Canvas();

		  resetImageViewAndCanvasDimensions(imageView, canvas);

		  int xGridResolution = 0;
		  int yGridResolution = 0;

		  blankCanvas(canvas);

		  if (layer instanceof PixLayer)
		  {

			xGridResolution = ((PixLayer) layer).maxCell().getX() - ((PixLayer) layer).minCell().getX();
			yGridResolution = ((PixLayer) layer).maxCell().getY() - ((PixLayer) layer).minCell().getY();

			xGridResolution++;
			yGridResolution++;

			layer = ((PixLayer) layer).duplicate().withNewOrigin(((PixLayer) layer).minCell());

			canvas.setWidth(xGridResolution * 4);
			canvas.setHeight(yGridResolution * 4);

		  }
		  else if (layer instanceof PicLayer)
		  {
			PicLayer drawlayer = new PicLayer();

			final PicLayer picLayer = (PicLayer) layer;
			drawlayer = (PicLayer) picLayer.duplicate();

			if (layer instanceof TextLayer)
			{
			      PicLayer drawLayer = new PicLayer();
			      drawLayer.setImage(((TextLayer) layer).getImage());

			}

			if (layer instanceof BakeLayer)
			{
			      PicLayer drawLayer = new PicLayer();
			      drawLayer.setImage(((BakeLayer) layer).getBakedSnapshot());

			}

			layer = setSizeFactorToFitInCanvas(drawlayer, canvas);
		  }

		  layer.draw(canvas, xGridResolution, yGridResolution);

		  imageView.setImage(canvas.snapshot(null, null));

	    }

      }


      /**
       * @param canvas
       */
      private void blankCanvas(Canvas canvas)
      {

	    canvas.getGraphicsContext2D().setFill(Color.WHITE);
	    canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
      }


      /**
       * @param imageView
       * @param canvas
       */
      private void resetImageViewAndCanvasDimensions(ImageView imageView, Canvas canvas)
      {

	    imageView.setPreserveRatio(true);
	    imageView.setFitWidth(MINIATUREWIDTH);
	    imageView.setFitHeight(MINIATUREHEIGHT);

	    canvas.setHeight(MINIATUREHEIGHT);
	    canvas.setWidth(MINIATUREWIDTH);
      }


      /**
       * 
       */
      private void resetMiniaturesWidthAndHeight()
      {

	    MINIATUREHEIGHT = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));
	    MINIATUREWIDTH = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));
      }


      private PicLayer setSizeFactorToFitInCanvas(PicLayer duplicate, Canvas canvas)
      {

	    duplicate.setPosition(new Coord(0, 0));
	    duplicate.setSizeFactorX(100);
	    duplicate.setSizeFactorY(100);
	    duplicate.setZoomFactor(1.0);

	    final double imageWidth = duplicate.getImage().getWidth();
	    final double imageHeight = duplicate.getImage().getHeight();

	    if (imageWidth > canvas.getWidth() || imageHeight > canvas.getHeight())
	    {

		  double max = Math.max(imageHeight, imageWidth);
		  double factor = 1.0;

		  if (max == imageHeight)
		  {

			factor = canvas.getHeight() / imageHeight;

		  }

		  if (max == imageWidth)
		  {

			factor = canvas.getWidth() / imageWidth;
		  }

		  duplicate.setSizeFactorY(duplicate.getSizeFactorY() * factor);
		  duplicate.setSizeFactorX(duplicate.getSizeFactorX() * factor);

	    }

	    return duplicate;
      }


      /**
       * Returns the miniature Image currently stored for the layer.
       * 
       * @param layer
       * @return
       */
      public Image getImage(ALayer layer)
      {

	    return miniatures.get(layer).getImage();
      }

}
