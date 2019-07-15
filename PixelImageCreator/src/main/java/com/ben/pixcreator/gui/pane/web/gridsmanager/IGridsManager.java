
package com.ben.pixcreator.gui.pane.web.gridsmanager;

import java.util.Set;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;

public interface IGridsManager
{

      public Set<PixelGrid> getGrids(boolean isUserGridsOnly, Set<SearchFilters> filters);


      public Set<PixelGrid> getAllGrids();


      public boolean saveGrid(LogInfo logInfo, PixelGrid grid);


      public boolean deleteGrid(PixelGrid grid);


      public void updateGrid(PixelGrid grid);

}
