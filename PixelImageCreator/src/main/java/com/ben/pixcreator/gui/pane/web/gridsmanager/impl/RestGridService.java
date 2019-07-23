
package com.ben.pixcreator.gui.pane.web.gridsmanager.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.gridsmanager.IGridsService;
import com.ben.pixcreator.web.PixelGridDto;
import com.ben.pixcreator.web.exception.WebException;
import com.ben.pixcreator.web.target.provider.RestTargetProvider;

public class RestGridService implements IGridsService
{

      private static final Logger log = LoggerFactory.getLogger(RestGridService.class);


      @Override
      public Set<PixelGrid> getGrids(boolean isUserGridsOnly, Set<SearchFilters> filters) throws WebException
      {

	    RestTargetProvider restTargetProvider = new RestTargetProvider();
	    WebTarget baseTarget = restTargetProvider.getBaseTarget();
	    WebTarget target = baseTarget.path("grids").queryParam("isuseronly", isUserGridsOnly);

	    Set<PixelGrid> res = new HashSet<>();

	    for (SearchFilters filter : filters)
	    {
		  target = target.queryParam("filters", filter.name());
	    }

	    Response response = target.request(MediaType.APPLICATION_XML).get();

	    if (response.getStatus() == 200)
	    {

		  Set<PixelGridDto> resultGrids = response.readEntity(new GenericType<Set<PixelGridDto>>() {});
		  for (PixelGridDto gridDto : resultGrids)
		  {
			try
			{
			      res.add(new PixelGrid(
					  gridDto.getId(),
					  gridDto.getGrid(),
					  gridDto.getFilters(),
					  gridDto.getName(),
					  gridDto.getOwner(),
					  gridDto.getDescription(),
					  PixelGrid.fxImage(gridDto.getMiniatureBytes())));
			}
			catch (IOException e)
			{
			      log.error("Unable to load miniature for grid id : " + gridDto.getId());
			}
		  }

		  return res;
	    }
	    else
	    {
		  throw new WebException(response.readEntity(String.class));
	    }

      }


      @Override
      public Set<PixelGrid> getAllGrids()
      {

	    // TODO Auto-generated method stub
	    return null;
      }


      @Override
      public void saveGrid(PixelGridDto pixelGridDto) throws WebException
      {

	    RestTargetProvider restTargetProvider = new RestTargetProvider();
	    WebTarget target = restTargetProvider.getBaseTarget().path("grids");
	    Response response = target.request(MediaType.APPLICATION_XML).put(Entity.entity(pixelGridDto, MediaType.APPLICATION_XML));

	    int status = response.getStatus();
	    log.debug("Server replied : " + status);

	    if (status == 200)
	    {
		  PixelGridDto resp = response.readEntity(PixelGridDto.class);
		  log.debug(resp.toString());
	    }
	    else
	    {
		  throw new WebException("Unable to upload data\n" + response.readEntity(String.class));
	    }
      }


      @Override
      public void deleteGrid(PixelGrid grid) throws WebException
      {

	    RestTargetProvider restTargetProvider = new RestTargetProvider();
	    WebTarget baseTarget = restTargetProvider.getBaseTarget();
	    WebTarget target = baseTarget.path("grids").path(String.valueOf(grid.getId()));

	    Response response = target.request().delete();

	    if (response.getStatus() != 204)

	    {
		  throw new WebException("Unable to delete data\n" + response.readEntity(String.class));
	    }

      }


      @Override
      public void updateGrid(PixelGrid grid) throws WebException
      {

	    RestTargetProvider restTargetProvider = new RestTargetProvider();
	    WebTarget baseTarget = restTargetProvider.getBaseTarget();
	    WebTarget target = baseTarget.path("grids").path(String.valueOf(grid.getId()));

	    PixelGridDto gridDto = new PixelGridDto();

	    try
	    {
		  gridDto.setId(grid.getId());
		  gridDto.setFilters(grid.getFilters());
		  gridDto.setGrid(grid.getGrid());
		  gridDto.setMiniatureBytes(PixelGridDto.getBytes(grid.getMiniature()));
		  gridDto.setName(grid.getName());
		  gridDto.setOwner(grid.getOwner());
		  gridDto.setDescription(grid.getDescription());
	    }
	    catch (Exception e)
	    {
		  throw new WebException(e.getMessage());
	    }

	    Response response = target.request(MediaType.APPLICATION_XML).put(Entity.entity(gridDto, MediaType.APPLICATION_XML));

	    int status = response.getStatus();
	    log.debug("Server replied : " + status);

	    if (status == 200)
	    {
		  PixelGridDto resp = response.readEntity(PixelGridDto.class);
		  log.debug(resp.toString());
	    }
	    else
	    {
		  throw new WebException("Unable to update data\n" + response.readEntity(String.class));
	    }

      }

}
