
package com.ben.pixcreator.application.image.layer.sampler;

import com.ben.pixcreator.application.image.layer.impl.PixLayer;

public class LayerSampler
{

      private PixLayer pixLayer;


      public LayerSampler(PixLayer pixLayer)
      {

	    this.pixLayer = pixLayer;
      }


      public PixLayer div(int xDivFactor, int yDivFactor)
      {

	    PixLayer resultLayer = new PixLayer();
	    // TODO resample layer by dividing grid by factors
	    return resultLayer;
      }


      public PixLayer mult(int xMultFactor, int yMultFactor)
      {

	    PixLayer resultLayer = new PixLayer();
	    // TODO resample layer by multiplying grid by factors
	    return resultLayer;
      }

}
