
package com.ben.pixcreator.application.image.layer.sampler;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

public class LayerSampler
{

      private PixLayer	  pixLayer;
      private final Coord origin, end, max;


      public LayerSampler(PixLayer pixLayer)
      {

	    this.pixLayer = pixLayer;

	    // determine the min/max coord to get the workable area
	    origin = pixLayer.getGrid().keySet().stream().min(Coord.COMPARATOR).get();
	    end = pixLayer.getGrid().keySet().stream().max(Coord.COMPARATOR).get();

	    // get the max X and Y coord
	    int maxX = pixLayer.getGrid()
			.keySet()
			.stream()
			.map(Coord::getX)
			.max((a, b) -> Integer.compare(a, b))
			.get();

	    int maxY = pixLayer.getGrid()
			.keySet()
			.stream()
			.map(Coord::getY)
			.max((a, b) -> Integer.compare(a, b))
			.get();

	    max = new Coord(maxX, maxY);
      }


      public PixLayer div(int xDivFactor, int yDivFactor)
      {

	    PixLayer resultLayer = new PixLayer();
	    // TODO resample layer by dividing grid by factors

	    // les nouvelle coord sont les multiples de divfact

	    return resultLayer;
      }


      public PixLayer mult(int xMultFactor, int yMultFactor)
      {

	    PixLayer resultLayer = new PixLayer();
	    // TODO resample layer by multiplying grid by factors

	    return resultLayer;
      }

}
