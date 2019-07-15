
package com.ben.pixcreator.gui.pane.web.gridsmanager.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.gridsmanager.IGridsManager;
import com.ben.pixcreator.gui.pane.web.rest.target.provider.RestTargetProvider;

public class RestGridManager implements IGridsManager
{

      private WebTarget baseTarget = RestTargetProvider.getInstance().getBaseTarget().path("grids");


      @Override
      public Set<PixelGrid> getGrids(boolean isUserGridsOnly, Set<SearchFilters> filters)
      {

	    Set<PixelGrid> res = new HashSet<>();

	    String filtPhrase = String.join(",", filters.stream().map(Enum::name).collect(Collectors.toSet()));

	    WebTarget target = baseTarget.path("get")
			.queryParam("isuseronly", isUserGridsOnly)
			.queryParam("filters", filtPhrase);

	    Builder request = target.request(MediaType.APPLICATION_JSON);

	    Set<PixelGrid> result = request.get(new GenericType<Set<PixelGrid>>()
	    {
	    });

	    res.addAll(result);

	    return res;

      }


      @Override
      public Set<PixelGrid> getAllGrids()
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
      public boolean deleteGrid(PixelGrid grid)
      {

	    // TODO Auto-generated method stub
	    return false;
      }


      @Override
      public void updateGrid(PixelGrid grid)
      {

	    // TODO Auto-generated method stub

      }

}
