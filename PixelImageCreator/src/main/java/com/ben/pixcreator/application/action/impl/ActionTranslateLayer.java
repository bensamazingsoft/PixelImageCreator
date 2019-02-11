
package com.ben.pixcreator.application.action.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.selection.Selection;

import javafx.scene.paint.Color;

public class ActionTranslateLayer implements IAction, ICancelable
{

      private final int		      translateX;
      private final int		      translateY;
      private final ALayer	      layer;
      private final Selection	      selection;

      private final Map<Coord, Color> originalCells = new HashMap<>();


      public ActionTranslateLayer(int translateX, int translateY, ALayer layer, Selection selection)
      {

	    super();
	    this.translateX = translateX;
	    this.translateY = translateY;
	    this.layer = layer;
	    this.selection = selection;

	    if (!selection.getCoords().isEmpty() && layer instanceof PixLayer)
	    {

	    }

      }


      @Override
      public void execute() throws Exception
      {
	    // TODO Auto-generated method stub

      }


      @Override
      public void cancel() throws Exception
      {

	    // TODO Auto-generated method stub

      }

}
