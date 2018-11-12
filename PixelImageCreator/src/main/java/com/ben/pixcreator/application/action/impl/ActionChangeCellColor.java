
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

import javafx.scene.paint.Color;

public class ActionChangeCellColor implements IAction
{

      private PixLayer layer;
      private Coord    coord;
      private Color    color;

      private Color    prevColor;


      public ActionChangeCellColor(PixLayer layer, Coord coord, Color color)
      {

	    this.layer = layer;
	    this.coord = coord;
	    this.color = color;

	    prevColor = layer.getGrid().get(coord);

      }


      public void execute()
      {

	    layer.getGrid().put(coord, color);

      }


      public void cancel()
      {

	    layer.getGrid().put(coord, prevColor);

      }


      @Override
      public String toString()
      {

	    return "ActionChangeCellColor [layer=" + layer + ", coord=" + coord + ", color=" + color + "]";
      }


      @Override
      public int hashCode()
      {

	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((color == null) ? 0 : color.hashCode());
	    result = prime * result + ((coord == null) ? 0 : coord.hashCode());
	    result = prime * result + ((layer == null) ? 0 : layer.hashCode());
	    result = prime * result + ((prevColor == null) ? 0 : prevColor.hashCode());
	    return result;
      }


      @Override
      public boolean equals(Object obj)
      {

	    if (this == obj)
	    {
		  return true;
	    }
	    if (obj == null)
	    {
		  return false;
	    }
	    if (!(obj instanceof ActionChangeCellColor))
	    {
		  return false;
	    }
	    ActionChangeCellColor other = (ActionChangeCellColor) obj;
	    if (color == null)
	    {
		  if (other.color != null)
		  {
			return false;
		  }
	    }
	    else if (!color.equals(other.color))
	    {
		  return false;
	    }
	    if (coord == null)
	    {
		  if (other.coord != null)
		  {
			return false;
		  }
	    }
	    else if (!coord.equals(other.coord))
	    {
		  return false;
	    }
	    if (layer == null)
	    {
		  if (other.layer != null)
		  {
			return false;
		  }
	    }
	    else if (!layer.equals(other.layer))
	    {
		  return false;
	    }
	    if (prevColor == null)
	    {
		  if (other.prevColor != null)
		  {
			return false;
		  }
	    }
	    else if (!prevColor.equals(other.prevColor))
	    {
		  return false;
	    }
	    return true;
      }

}
