
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.paint.Color;

public class ChangeCellCoordAction implements IAction
{

      private PixImage image;
      private PixLayer layer;
      private Coord    oldCoord;
      private Coord    newCoord;
      private ColorRGB color;


      public ChangeCellCoordAction()
      {

	    layer = new PixLayer();
	    oldCoord = new Coord();
	    newCoord = new Coord();
	    color = new ColorRGB(Color.BLACK);
      }


      public ChangeCellCoordAction(PixImage image, PixLayer layer, Coord oldCoord, Coord newCoord)
      {

	    this.layer = layer;
	    this.oldCoord = oldCoord;
	    this.newCoord = newCoord;

	    color = layer.getGrid().get(oldCoord);

      }


      public void execute() throws Exception
      {

	    if (GuiFacade.getInstance().getActiveimage() == image)
	    {
		  if (image.getLayerList().getAllItems().contains(layer))
		  {
			layer.getGrid().remove(oldCoord);
			layer.getGrid().put(newCoord, color);
		  }
	    }
      }


      public void cancel() throws Exception
      {

	    if (GuiFacade.getInstance().getActiveimage() == image)
	    {
		  if (image.getLayerList().getAllItems().contains(layer))
		  {
			layer.getGrid().remove(newCoord);
			layer.getGrid().put(oldCoord, color);
		  }
	    }
      }

}