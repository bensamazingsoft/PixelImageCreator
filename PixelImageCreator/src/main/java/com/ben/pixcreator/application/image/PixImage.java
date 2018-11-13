
package com.ben.pixcreator.application.image;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.layer.ILayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.collection.LayerList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PixImage
{

      private static final int DEFAULTSIZE	     = 800;
      private static final int DEFAULTGRIDRESOLUTION = 80;

      private String	       name;
      private LocalDate	       dateCre;

      private int	       xSize, ySize;
      private int	       xGridResolution, yGridResolution;

      private ILayer	       ghost, select;
//      private PicLayer	       basePic;
      private List<ILayer>     layers;
      private boolean	       showGrid;


      public PixImage()
      {

	    name = "sans_titre";
	    dateCre = LocalDate.now();
	    ghost = new PixLayer();
	    select = new PixLayer();
	    layers = new LayerList<ILayer>();

	    xSize = ySize = DEFAULTSIZE;

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
//	    this.basePic = basePic;
      }


      public void draw(Canvas canvas) throws IOException
      {

//	    if (basePic != null)
//	    {
//		  basePic.show(canvas, xGridResolution, yGridResolution);
//	    }

	    for (int i = 0; i < layers.size(); i++)
	    {
		  layers.get(i).show(canvas, xGridResolution, yGridResolution);
	    }

	    select.show(canvas, xGridResolution, yGridResolution);

	    ghost.show(canvas, xGridResolution, yGridResolution);

	    if (showGrid)
	    {
		  showGrid(canvas);
	    }

      }


      // show layer grid in canvas if option is toggled on
      private void showGrid(Canvas canvas) throws IOException
      {

	    GraphicsContext graphics = canvas.getGraphicsContext2D();

	    double xCanvasSize = canvas.getWidth();
	    int xCellSize = (int) xCanvasSize / xGridResolution;
	    double yCanvasSize = canvas.getHeight();
	    int yCellSize = (int) yCanvasSize / yGridResolution;

	    graphics.setStroke(AppContext.getInstance().getGridColor());

	    for (int x = xCellSize; x < xCanvasSize; x += xCellSize)
	    {
		  graphics.strokeLine(x, yCellSize, x, yCanvasSize);

	    }
	    for (int y = yCellSize; y < yCanvasSize; y += yCellSize)
	    {
		  graphics.strokeLine(xCellSize, y, xCanvasSize, y);
	    }

      }


   


      @Override
	public String toString() {
		return "PixImage [name=" + name + ", xSize=" + xSize + ", ySize="
				+ ySize + ", xGridResolution=" + xGridResolution
				+ ", yGridResolution=" + yGridResolution + "]";
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


//      public PicLayer getBasePic()
//      {
//
//	    return basePic;
//      }
//
//
//      public void setBasePic(PicLayer basePic)
//      {
//
//	    this.basePic = basePic;
//      }


      public List<ILayer> getGridLayers()
      {

	    return layers;
      }


      public void setGridLayers(List<ILayer> gridLayers)
      {

	    this.layers = gridLayers;
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


      public boolean isShowGrid()
      {

	    return showGrid;
      }


      public void setShowGrid(boolean showGrid)
      {

	    this.showGrid = showGrid;
      }


      public void toggleShowGrid()
      {

	    showGrid = !showGrid;
      }

}