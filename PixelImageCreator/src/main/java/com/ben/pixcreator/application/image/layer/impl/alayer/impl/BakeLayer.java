
package com.ben.pixcreator.application.image.layer.impl.alayer.impl;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.draw.factory.DrawLayerFactory;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

import javafx.scene.canvas.Canvas;

public class BakeLayer extends PicLayer
{

      private static final long	serialVersionUID = 1L;
      private PixImage		hostImage;


      public BakeLayer(PixImage hostImage)
      {

	    super();
	    this.hostImage = hostImage;
	    bake();
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    bake();
	    super.draw(canvas, xGridResolution, yGridResolution);
      }


      private void bake()
      {

	    Canvas canvas = new Canvas();
	    canvas.setWidth(getHostImage().getxSize());
	    canvas.setHeight(getHostImage().getySize());

	    final Pile<ALayer> layerList = getHostImage().getLayerList();

	    int id = layerList.getIdx(this);

	    if (id > 0)
	    {

		  for (int i = 0; i < id; i++)
		  {

			final ALayer layer = layerList.getItem(i);
			Pile<Effect> effectPile = AppContext.getInstance().getEffectManager().getImageLayerEffects(
				    getHostImage(),
				    layer);
			Pile<Effect> bakeEffectPile = AppContext.getInstance().getEffectManager().getImageLayerEffects(
				    getHostImage(),
				    this);

			if (true)
			{
			      try
			      {

				    ALayer drawLayerEffect = DrawLayerFactory.getDrawLayer(effectPile, layer);
				    // ALayer drawBakedEffect = DrawLayerFactory.getDrawLayer(bakeEffectPile, drawLayerEffect);

				    drawLayerEffect.draw(
						canvas,
						getHostImage().getxGridResolution(),
						getHostImage().getyGridResolution());

			      }
			      catch (EffectException e)
			      {
				    new ExceptionPopUp(e);
			      }
			}

		  }
	    }
	    setImage(canvas.snapshot(null, null));

      }


      @Override
      public String toString()
      {

	    return "BakeLayer [image : " + getImage() + "]";
      }


      @Override
      public ALayer duplicate()
      {

	    BakeLayer clone = new BakeLayer(getHostImage());

	    clone.setUuid(getUUID());

	    clone.setVisible(isVisible());

	    clone.setPosition(getPosition());

	    clone.setSizeFactorX(getSizeFactorX());

	    clone.setSizeFactorY(getSizeFactorY());

	    clone.setZoomFactor(getZoomFactor());

	    clone.setImage(getImage());

	    clone.setImageFile(getImageFile());

	    clone.setOpacity(getOpacity());

	    return clone;
      }


      public PicLayer getBakedPicLayer()
      {

	    PicLayer baked = new PicLayer();

	    bake();

	    baked.setUuid(getUUID());

	    baked.setVisible(isVisible());

	    baked.setPosition(getPosition());

	    baked.setSizeFactorX(getSizeFactorX());

	    baked.setSizeFactorY(getSizeFactorY());

	    baked.setZoomFactor(getZoomFactor());

	    baked.setImage(getImage());

	    baked.setImageFile(getImageFile());

	    baked.setOpacity(getOpacity());

	    return baked;

      }


      public PixImage getHostImage()
      {

	    return hostImage;
      }


      public void setHostImage(PixImage hostImage)
      {

	    this.hostImage = hostImage;
      }

}
