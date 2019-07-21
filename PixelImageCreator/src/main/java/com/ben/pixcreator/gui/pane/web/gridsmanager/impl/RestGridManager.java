
package com.ben.pixcreator.gui.pane.web.gridsmanager.impl;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.gridsmanager.IGridsService;
import com.ben.pixcreator.web.PixelGridDto;
import com.ben.pixcreator.web.exception.WebException;
import com.ben.pixcreator.web.target.provider.RestTargetProvider;

public class RestGridManager implements IGridsService
{

      private RestTargetProvider restTargetProvider = new RestTargetProvider();
      private WebTarget		 baseTarget	    = restTargetProvider.getBaseTarget();


      @Override
      public Set<PixelGrid> getGrids(LogInfo logInfo, boolean isUserGridsOnly, Set<SearchFilters> filters) throws Exception
      {

	    Set<PixelGrid> res = new HashSet<>();

	    WebTarget target = baseTarget.path("filteredgrids").queryParam("isuseronly", isUserGridsOnly);

	    for (SearchFilters filter : filters)
	    {
		  target.queryParam("filters", filter.name());
	    }

	    Response response = target.request(MediaType.APPLICATION_XML).get();

	    if (response.getStatus() == 200)
	    {

		  Set<PixelGridDto> resultGrids = response.readEntity(new GenericType<Set<PixelGridDto>>() {});
		  for (PixelGridDto gridDto : resultGrids)
		  {
			res.add(new PixelGrid(
				    gridDto.getGrid(),
				    gridDto.getFilters(),
				    gridDto.getName(),
				    gridDto.getOwner(),
				    gridDto.getDescription(),
				    PixelGrid.fxImage(gridDto.getMiniatureBytes())));
		  }

		  return res;
	    }
	    else
	    {
		  throw new WebException(response.readEntity(String.class));
	    }

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
