
package com.ben.pixcreator.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ben.pixcreator.server.data.model.PixelGridDto;
import com.ben.pixcreator.server.data.services.impl.GridHibService;
import com.ben.pixcreator.server.exception.DataServiceException;

@Path("add")
public class Add
{

      private final Logger log = LogManager.getLogger(Add.class);


      @PUT
      @Consumes(MediaType.APPLICATION_XML)
      @Produces(MediaType.APPLICATION_XML)
      public Response add(PixelGridDto grid)
      {

	    GridHibService gridService = new GridHibService();

	    try
	    {

		  // needed for Hibernate mapping
		  grid.getGrid().forEach((coord, color) -> {
			color.setCoord(coord);
		  });

		  gridService.persist(grid);

		  return Response.ok().entity(grid).build();
	    }
	    catch (DataServiceException e)
	    {

		  log.error(e.getMessage(), e);
		  return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

	    }

      }

}
