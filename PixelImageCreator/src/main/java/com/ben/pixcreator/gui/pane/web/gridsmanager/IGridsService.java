
package com.ben.pixcreator.gui.pane.web.gridsmanager;

import java.util.Set;

import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.web.PixelGridDto;
import com.ben.pixcreator.web.exception.WebException;

public interface IGridsService
{

      public Set<PixelGrid> getGrids(boolean isUserGridsOnly, Set<SearchFilters> filters) throws Exception;


      public Set<PixelGrid> getAllGrids();


      public void deleteGrid(PixelGrid grid) throws WebException;


      public void updateGrid(PixelGrid grid) throws WebException;


      public void saveGrid(PixelGridDto pixelGridDto) throws WebException;

}
