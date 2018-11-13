
package com.ben.pixcreator.application.image.layer.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.ILayer;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class PixLayer implements ILayer
{

      private Map<Coord, Color> grid;


      public PixLayer()
      {

	    this.grid = new HashMap<Coord, Color>();
      }


      public PixLayer(Map<Coord, Color> grid)
      {

	    super();
	    this.grid = grid;
      }


      public void show(Canvas canvas, int xGridResolution, int yGridResolution)
      {

	    // TODO Draw piximage to canvas

      }


      public Map<Coord, Color> getGrid()
      {

	    return grid;
      }


      public void setGrid(Map<Coord, Color> grid)
      {

	    this.grid = grid;
      }

}
