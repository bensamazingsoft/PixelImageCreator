
package com.ben.pixcreator.image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ben.pixcreator.image.coords.Coord;
import com.ben.pixcreator.image.layer.ILayer;
import com.ben.pixcreator.image.layer.impl.PicLayer;
import com.ben.pixcreator.image.layer.impl.PixLayer;

import javafx.scene.canvas.Canvas;

public class PixImage
{

      private static final int DEFAULTSIZE	     = 800;
      private static final int DEFAULTGRIDRESOLUTION = 80;

      private String	       name;
      private LocalDate	       dateCre;
      private double	       basePicSizeFactor;
      private int	       xSize, ySize;
      private int	       xGridResolution, yGridResolution;

      private ILayer	       ghost, select;
      private PicLayer	       basePic;
      private List<ILayer>     gridLayers;

      private Coord	       basePicPos;


      public PixImage()
      {

	    name = "sans_titre";
	    dateCre = LocalDate.now();
	    ghost = new PixLayer();
	    select = new PixLayer();
	    gridLayers = new ArrayList<ILayer>();
	    basePicPos = new Coord();
	    xSize = ySize = DEFAULTSIZE;
	    basePicSizeFactor = 1d;
	    xGridResolution = yGridResolution = DEFAULTGRIDRESOLUTION;

      }


      public PixImage(String name)
      {

	    this();
	    this.name = name;
      }


      public PixImage(String name, int xSize, int ySize)
      {

	    super();
	    this.name = name;
	    this.xSize = xSize;
	    this.ySize = ySize;
      }


      public PixImage(String name, PicLayer basePic)
      {

	    this();
	    this.name = name;
	    this.basePic = basePic;
      }


      public void draw(Canvas canvas)
      {

	    // TODO implement this shit

      }


      @Override
      public String toString()
      {

	    return "PixImage [name=" + name + ", dateCre=" + dateCre + ", xSize=" + xSize + ", ySize=" + ySize + "]";
      }


      @Override
      public int hashCode()
      {

	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((basePic == null) ? 0 : basePic.hashCode());
	    result = prime * result + ((basePicPos == null) ? 0 : basePicPos.hashCode());
	    long temp;
	    temp = Double.doubleToLongBits(basePicSizeFactor);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
	    result = prime * result + ((dateCre == null) ? 0 : dateCre.hashCode());
	    result = prime * result + ((ghost == null) ? 0 : ghost.hashCode());
	    result = prime * result + ((gridLayers == null) ? 0 : gridLayers.hashCode());
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
	    result = prime * result + ((select == null) ? 0 : select.hashCode());
	    result = prime * result + xGridResolution;
	    result = prime * result + xSize;
	    result = prime * result + yGridResolution;
	    result = prime * result + ySize;
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
	    if (!(obj instanceof PixImage))
	    {
		  return false;
	    }
	    PixImage other = (PixImage) obj;
	    if (basePic == null)
	    {
		  if (other.basePic != null)
		  {
			return false;
		  }
	    }
	    else if (!basePic.equals(other.basePic))
	    {
		  return false;
	    }
	    if (basePicPos == null)
	    {
		  if (other.basePicPos != null)
		  {
			return false;
		  }
	    }
	    else if (!basePicPos.equals(other.basePicPos))
	    {
		  return false;
	    }
	    if (Double.doubleToLongBits(basePicSizeFactor) != Double.doubleToLongBits(other.basePicSizeFactor))
	    {
		  return false;
	    }
	    if (dateCre == null)
	    {
		  if (other.dateCre != null)
		  {
			return false;
		  }
	    }
	    else if (!dateCre.equals(other.dateCre))
	    {
		  return false;
	    }
	    if (ghost == null)
	    {
		  if (other.ghost != null)
		  {
			return false;
		  }
	    }
	    else if (!ghost.equals(other.ghost))
	    {
		  return false;
	    }
	    if (gridLayers == null)
	    {
		  if (other.gridLayers != null)
		  {
			return false;
		  }
	    }
	    else if (!gridLayers.equals(other.gridLayers))
	    {
		  return false;
	    }
	    if (name == null)
	    {
		  if (other.name != null)
		  {
			return false;
		  }
	    }
	    else if (!name.equals(other.name))
	    {
		  return false;
	    }
	    if (select == null)
	    {
		  if (other.select != null)
		  {
			return false;
		  }
	    }
	    else if (!select.equals(other.select))
	    {
		  return false;
	    }
	    if (xGridResolution != other.xGridResolution)
	    {
		  return false;
	    }
	    if (xSize != other.xSize)
	    {
		  return false;
	    }
	    if (yGridResolution != other.yGridResolution)
	    {
		  return false;
	    }
	    if (ySize != other.ySize)
	    {
		  return false;
	    }
	    return true;
      }


      public String getName()
      {

	    return name;
      }


      public void setName(String name)
      {

	    this.name = name;
      }


      public int getxSize()
      {

	    return xSize;
      }


      public void setxSize(int xSize)
      {

	    this.xSize = xSize;
      }


      public int getySize()
      {

	    return ySize;
      }


      public void setySize(int ySize)
      {

	    this.ySize = ySize;
      }


      public ILayer getGhost()
      {

	    return ghost;
      }


      public void setGhost(ILayer ghost)
      {

	    this.ghost = ghost;
      }


      public ILayer getSelect()
      {

	    return select;
      }


      public void setSelect(ILayer select)
      {

	    this.select = select;
      }


      public PicLayer getBasePic()
      {

	    return basePic;
      }


      public void setBasePic(PicLayer basePic)
      {

	    this.basePic = basePic;
      }


      public List<ILayer> getGridLayers()
      {

	    return gridLayers;
      }


      public void setGridLayers(List<ILayer> gridLayers)
      {

	    this.gridLayers = gridLayers;
      }


      public Coord getBasePicPos()
      {

	    return basePicPos;
      }


      public void setBasePicPos(Coord basePicPos)
      {

	    this.basePicPos = basePicPos;
      }


      public double getBasePicSizeFactor()
      {

	    return basePicSizeFactor;
      }


      public void setBasePicSizeFactor(double basePicSizeFactor)
      {

	    this.basePicSizeFactor = basePicSizeFactor;
      }


      public int getxGridResolution()
      {

	    return xGridResolution;
      }


      public void setxGridResolution(int xGridResolution)
      {

	    this.xGridResolution = xGridResolution;
      }


      public int getyGridResolution()
      {

	    return yGridResolution;
      }


      public void setyGridResolution(int yGridResolution)
      {

	    this.yGridResolution = yGridResolution;
      }

}
