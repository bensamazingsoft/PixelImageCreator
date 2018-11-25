
package com.ben.pixcreator.application.action.impl;

import java.io.IOException;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.ILayer;

public class ActionChangeLayerVisibility implements IAction
{

      private PixImage image;

      private ILayer   layer;
      private boolean  changed;
      private boolean  old;


      public ActionChangeLayerVisibility(PixImage image, ILayer layer) throws IOException, ClosedImageException, InexistantLayerException
      {

	    super();
	    this.image = image;
	    this.layer = layer;
	    old = image.getLayers().get(layer);
	    changed = !old;

	    if (!AppContext.getInstance().getOpenImages().contains(image))
	    {
		  throw new ClosedImageException();
	    }
	    else if (!image.getLayers().containsKey(layer))
	    {
		  throw new InexistantLayerException();
	    }
      }


      @Override
      public void execute() throws IOException
      {

	    if (AppContext.getInstance().getOpenImages().contains(image))
	    {
		  if (image.getLayers().containsKey(layer))
		  {
			image.getLayers().put(layer, changed);
		  }
	    }

      }


      @Override
      public void cancel() throws IOException
      {

	    if (AppContext.getInstance().getOpenImages().contains(image))
	    {
		  if (image.getLayers().containsKey(layer))
		  {
			image.getLayers().put(layer, old);
		  }
	    }
      }

}
