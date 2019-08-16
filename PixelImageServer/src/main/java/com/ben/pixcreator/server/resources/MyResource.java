
package com.ben.pixcreator.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource
{

      @Context
      SecurityContext secContext;


      /**
       * Method handling HTTP GET requests. The returned object will be sent to the client as "text/plain" media type.
       *
       * @return String that will be returned as a text/plain response.
       */
      @GET
      @Produces(MediaType.TEXT_PLAIN)
      public String getIt()
      {

	    if (true)
		  throw new WebApplicationException("exception thrown", Status.BAD_REQUEST);

	    return "get  method returned";
      }
}
