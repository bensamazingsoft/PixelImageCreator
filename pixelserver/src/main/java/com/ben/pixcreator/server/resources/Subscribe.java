
package com.ben.pixcreator.server.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ben.pixcreator.server.data.model.User;
import com.ben.pixcreator.server.data.services.UserService;
import com.ben.pixcreator.server.data.services.impl.UserHibService;
import com.ben.pixcreator.server.exception.DataServiceException;

@Path("subscribe")
public class Subscribe
{

      private final Logger log = LogManager.getLogger(Subscribe.class);


      @POST
      @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
      @PermitAll
      public Response subscribe(@FormParam(value = "email") String email, @FormParam(value = "password") String password)
      {

	    UserService userService = new UserHibService();
	    try
	    {
		  if (null == userService.findUserByEmail(email))
		  {

			User user = new User(email, password);

			userService.persist(user);

			return Response.ok().build();

		  }
	    }
	    catch (DataServiceException e)
	    {
		  log.error(e.getMessage(), e);
		  return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	    }

	    return Response.status(Status.PRECONDITION_FAILED).entity("User already  exists.").build();
      }

}
