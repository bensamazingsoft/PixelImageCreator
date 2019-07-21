
package com.ben.pixcreator.server.resources;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;

import com.ben.pixcreator.server.data.model.PixelGridDto;
import com.ben.pixcreator.server.data.services.impl.GridHibService;
import com.ben.pixcreator.server.exception.DataServiceException;

@Path("filteredgrids")
public class FilteredGrids
{

      private final Logger	  log			 = LogManager.getLogger(FilteredGrids.class);

      private static final String AUTHORIZATION_PROPERTY = "Authorization";
      private static final String AUTHENTICATION_SCHEME	 = "Basic";

      @Context
      HttpHeaders		  headers;


      @GET
      @Produces(MediaType.APPLICATION_XML)
      public Set<PixelGridDto> getFilteredGrids(@QueryParam(value = "isuseronly") Boolean userOnly, @QueryParam(value = "filters") Set<String> filters)
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

}
