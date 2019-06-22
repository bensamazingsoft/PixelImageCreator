
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
import javafx.scene.paint.Color;

public class MiniatureManager
{

      @SuppressWarnings("unused")
      private static final Logger log	     = LoggerFactory.getLogger(MiniatureManager.class);

      private static double	  MINIATUREHEIGHT;

      private static double	  MINIATUREWIDTH;

      private Map<ALayer, Canvas> miniatures = new HashMap<>();


      public void addMiniature(ALayer layer, Canvas canvas)
      {

	    MINIATUREHEIGHT = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));
	    MINIATUREWIDTH = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));

	    miniatures.put(layer, canvas);

      }


      public void update(ALayer layer) throws EffectException
      {

	    if (miniatures.containsKey(layer))
	    {
		  Canvas canvas = miniatures.get(layer);

		  canvas.setHeight(MINIATUREHEIGHT);
		  canvas.setWidth(MINIATUREWIDTH);

		  canvas.getGraphicsContext2D().setFill(Color.WHITE);
		  canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		  int xGridResolution = 0;
		  int yGridResolution = 0;

		  if (layer instanceof PixLayer)
		  {

			int minX = ((PixLayer) layer).getGrid().keySet().stream()
				    .mapToInt(Coord::getX)
				    .min()
				    .orElse(0);
			// log.debug("minX : " + minX);
			int maxX = ((PixLayer) layer).getGrid().keySet().stream()
				    .mapToInt(Coord::getX)
				    .max()
				    .orElse(0);
			// log.debug("maxX : " + maxX);
			int minY = ((PixLayer) layer).getGrid().keySet().stream()
				    .mapToInt(Coord::getY)
				    .min()
				    .orElse(0);
			// log.debug("minY : " + minY);
			int maxY = ((PixLayer) layer).getGrid().keySet().stream()
				    .mapToInt(Coord::getY)
				    .max()
				    .orElse(0);
			// log.debug("maxY : " + maxY);

			xGridResolution = maxX - minX + 1;
			yGridResolution = maxY - minY + 1;

			layer = ((PixLayer) layer).duplicate().offset(new Coord(minX, minY));

			if (xGridResolution != yGridResolution)
			{
			      adaptPixCanvasRatio(canvas, xGridResolution, yGridResolution);
			}

		  }
		  else if (layer instanceof PicLayer)
		  {

			if (!(layer instanceof TextLayer))
			{
			      final PicLayer picLayer = (PicLayer) layer;
			      layer = makeDrawPicLayer((PicLayer) picLayer.duplicate(), canvas);
			}

			if (layer instanceof TextLayer)
			{

			      PicLayer drawLayer = new PicLayer();
			      drawLayer.setImage(((TextLayer) layer).getImage());
			      layer = makeDrawPicLayer((PicLayer) drawLayer, canvas);
			}

			if (layer instanceof BakeLayer)
			{

			      PicLayer drawLayer = new PicLayer();
			      drawLayer.setImage(((BakeLayer) layer).getBakedSnapshot());
			      layer = makeDrawPicLayer((PicLayer) drawLayer, canvas);

			}

		  }

		  layer.draw(canvas, xGridResolution, yGridResolution);

	    }

      }


      private PicLayer makeDrawPicLayer(PicLayer duplicate, Canvas canvas)
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


      private void adaptPixCanvasRatio(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    if (xGridResolution > yGridResolution)
	    {

		  double factorX = (double) yGridResolution / (double) xGridResolution;
		  // log.debug("factorX : " + factorX);
		  canvas.setHeight(canvas.getHeight() * factorX);
		  // log.debug("new canvas size : " + canvas.getWidth() + "X" +
		  // canvas.getHeight());

	    }

	    if (xGridResolution < yGridResolution)
	    {

		  double factorY = (double) xGridResolution / (double) yGridResolution;
		  // log.debug("factorY : " + factorY);
		  canvas.setWidth(canvas.getWidth() * factorY);
		  // log.debug("new canvas size : " + canvas.getWidth() + "X" +
		  // canvas.getHeight());
	    }
      }

}
