
package com.ben.pixcreator.gui.pane.web;

import java.util.Set;

public interface IGridsManager
{

      public Set<PixelGrid> getGrids(boolean isUserGridsOnly, Set<SearchFilters> filters);


      public Set<PixelGrid> getAllGrids();


      public boolean saveGrid(LogInfo logInfo, PixelGrid grid);


      public boolean deleteGrid(PixelGrid grid);

}
