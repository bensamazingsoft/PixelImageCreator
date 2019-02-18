
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class ActionUpdateSelection implements IAction
{

      private PixImage image;
      private ColorRGB color;


      public ActionUpdateSelection(PixImage image)
      {

	    this.image = image;

	    color = new ColorRGB(AppContext.getInstance().propertyContext().getSelectionColor());
      }


      @Override
      public void execute() throws Exception
      {

	    Selection selection = GuiFacade.getInstance().getSelections().get(image);
	    image.getSelect().getGrid().clear();

	    if (null != selection)
	    {

		  for (Coord coord : selection.getCoords())
		  {

			image.getSelect().getGrid().put(coord, color);

		  }

	    }

      }

}
