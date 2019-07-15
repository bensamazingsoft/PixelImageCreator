
package com.ben.pixcreator.gui.pane.web.gridsmanager;

import java.util.Set;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;

public interface IGridsManager
{

      public Set<PixelGrid> getGrids(LogInfo logInfo, boolean isUserGridsOnly, Set<SearchFilters> filters);


      public Set<PixelGrid> getAllGrids(LogInfo logInfo);


      public boolean saveGrid(LogInfo logInfo, PixelGrid grid);


      public boolean deleteGrid(LogInfo logInfo, PixelGrid grid);


      public void updateGrid(LogInfo logInfo, PixelGrid grid);

}
