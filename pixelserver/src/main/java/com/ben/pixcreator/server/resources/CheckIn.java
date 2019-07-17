
package com.ben.pixcreator.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("checkin")
public class CheckIn
{

      @GET
      public Response checkUser()
      {

	    return Response.ok().entity("Login successful").build();
      }

}
