
package com.ben.pixcreator.gui.context.menu.provider;

import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.gui.context.menu.impl.PixLayerBoxContextMenu;

import javafx.scene.control.ContextMenu;

public class LayerBoxContextMenuProvider implements ContextMenuProvider
{

      @Override
      public ContextMenu getMenu(Object obj)
      {

	    if (obj instanceof ALayer)
	    {

		  ALayer layer = (ALayer) obj;

		  if (layer instanceof PixLayer)
		  {
			return new PixLayerBoxContextMenu(layer);
		  }
		  else
		  {

			// TODO PICLAYER case
			return null;
		  }

	    }
	    else
	    {
		  throw new ClassCastException("not a ALayer");
	    }

      }

}
