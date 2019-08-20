
package com.ben.pixcreator.server.resources;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;

import com.ben.pixcreator.server.data.model.PixelGridDto;
import com.ben.pixcreator.server.data.services.impl.GridHibService;
import com.ben.pixcreator.server.exception.DataServiceException;

@Path("grids")
public class Grids
{

      private final Logger	  log			 = LogManager.getLogger(Grids.class);

      private static final String AUTHORIZATION_PROPERTY = "Authorization";
      private static final String AUTHENTICATION_SCHEME	 = "Basic";

      @Context
      HttpHeaders		  headers;


      @GET
      @Produces(MediaType.APPLICATION_XML)
      public Set<PixelGridDto> getFiltered(@QueryParam(value = "isuseronly") Boolean userOnly, @QueryParam(value = "filters") Set<String> filters)
      {

	    Set<PixelGridDto> result = new HashSet<>();
	    String email;

	    String authorization = headers.getHeaderString(AUTHORIZATION_PROPERTY).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
	    if (authorization != null)
	    {

		  StringTokenizer tokenizer = new StringTokenizer(Base64.decodeAsString(authorization.getBytes()), ":");
		  email = tokenizer.nextToken();

		  if (email == null || email.length() == 0)
		  {
			return result;
		  }

	    }
	    else
	    {
		  return result;
	    }

	    GridHibService gridService = new GridHibService();

	    try
	    {
		  result = gridService.findFiltered(email, userOnly, filters);
	    }
	    catch (DataServiceException e)
	    {
		  log.error("", e);
		  throw new ServerErrorException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
	    }

	    return result;

      }


      @PUT
      @Consumes(MediaType.APPLICATION_XML)
      @Produces(MediaType.APPLICATION_XML)
      public Response put(PixelGridDto grid)
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


      @PUT
      @Consumes(MediaType.APPLICATION_XML)
      @Produces(MediaType.APPLICATION_XML)
      @Path("{gridId}")
      public Response update(@PathParam(value = "gridId") int id, PixelGridDto grid)
      {

	    String email = checkEmail();

	    GridHibService gridService = new GridHibService();

	    try
	    {

		  PixelGridDto databaseGrid = gridService.find(id);

		  if (!databaseGrid.getOwner().equals(email))
		  {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		  }

		  // needed for Hibernate mapping
		  grid.getGrid().forEach((coord, color) -> {
			color.setCoord(coord);
		  });

		  gridService.update(grid);

		  return Response.ok().entity(grid).build();
	    }
	    catch (DataServiceException e)
	    {

		  log.error(e.getMessage(), e);
		  return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

	    }

      }


      @DELETE
      @Path("{gridId}")
      public void delete(@PathParam(value = "gridId") int id)
      {

	    String email = checkEmail();

	    GridHibService gridService = new GridHibService();

	    PixelGridDto gridDto;
	    try
	    {
		  gridDto = gridService.find(id);
	    }
	    catch (DataServiceException e)
	    {
		  throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
	    }

	    if (!email.equals(gridDto.getOwner()))
	    {
		  throw new WebApplicationException(Status.UNAUTHORIZED);
	    }

	    try
	    {
		  gridService.delete(id);
	    }
	    catch (DataServiceException e)
	    {
		  throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
	    }

      }


      /**
       * 
       * @return
       */
      private String checkEmail()
      {

	    String email;

	    String authorization = headers.getHeaderString(AUTHORIZATION_PROPERTY).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
	    if (authorization != null)
	    {

		  StringTokenizer tokenizer = new StringTokenizer(Base64.decodeAsString(authorization.getBytes()), ":");
		  email = tokenizer.nextToken();

		  if (email == null || email.length() == 0)
		  {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		  }

	    }
	    else
	    {
		  throw new WebApplicationException(Status.UNAUTHORIZED);
	    }
	    return email;
      }
}
