
package com.ben.pixcreator.gui.pane.web.gridsmanager.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.gridsmanager.IGridsService;

import javafx.scene.paint.Color;

public class MockGridManager implements IGridsService
{

      @Override
      public Set<PixelGrid> getGrids(LogInfo logInfo, boolean isUserGridsOnly, Set<SearchFilters> filters)
      {

	    final HashMap<Coord, ColorRGB> grid = new HashMap<>();
	    grid.put(new Coord(1, 1), new ColorRGB(Color.BLUE));
	    Set<PixelGrid> set = new HashSet<>();
	    // set.add(new PixelGrid(grid));

	    return set;

      }


      @Override
      public Set<PixelGrid> getAllGrids(LogInfo logInfo)
      {

	    // TODO Auto-generated method stub
	    return null;
      }


      @Override
      public boolean saveGrid(LogInfo logInfo, PixelGrid grid)
      {

	    // TODO Auto-generated method stub
	    return false;
      }


      @Override
      public boolean deleteGrid(LogInfo logInfo, PixelGrid grid)
      {

	    // TODO Auto-generated method stub
	    return false;
      }


      @Override
      public void updateGrid(LogInfo logInfo, PixelGrid grid)
      {

	    // TODO Auto-generated method stub

      }

}
