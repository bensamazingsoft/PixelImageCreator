
package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class ActionUpdateSelection implements IAction
{

      private static final Logger log = LoggerFactory.getLogger(ActionUpdateSelection.class);

      private PixImage		  image;
      private ColorRGB		  color;


      public ActionUpdateSelection(PixImage image)
      {

	    this.image = image;

	    color = new ColorRGB(AppContext.getInstance().propertyContext().getSelectionColor());
      }


      @Override
      public void execute() throws Exception
      {

	    log.debug("execute");
	    Selection selection = GuiFacade.getInstance().getSelections().get(image);
	    image.getSelect().getGrid().clear();

	    if (null != selection)
	    {
		  log.debug("selected " + selection.getCoords().size() + " cells");
		  for (Coord coord : selection.getCoords())
		  {

			image.getSelect().getGrid().put(coord, color);

		  }

	    }

      }

}
