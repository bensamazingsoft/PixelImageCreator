
package com.ben.pixcreator.server.filters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.internal.util.Base64;

import com.ben.pixcreator.server.data.model.User;
import com.ben.pixcreator.server.data.services.UserService;
import com.ben.pixcreator.server.data.services.impl.UserHibService;
import com.ben.pixcreator.server.exception.DataServiceException;

public class AuthFilter implements ContainerRequestFilter
{

      @Context
      ResourceInfo		  resourceInfo;

      private static final String AUTHORIZATION_PROPERTY = "Authorization";
      private static final String AUTHENTICATION_SCHEME	 = "Basic";


      @Override
      public void filter(ContainerRequestContext ctx) throws IOException
      {

	    Method method = resourceInfo.getResourceMethod();

	    if (method.isAnnotationPresent(PermitAll.class))
	    {
		  return;
	    }

	    if (!ctx.getHeaders().containsKey(AUTHORIZATION_PROPERTY))
	    {
		  ctx.abortWith(Response.status(Status.UNAUTHORIZED).entity("No authorization infos").build());
		  return;
	    }

	    String authorization = ctx.getHeaderString(AUTHORIZATION_PROPERTY).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

	    if (authorization == null)
	    {
		  ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
			      .entity("No authorization infos").build());
		  return;
	    }

	    StringTokenizer tokenizer = new StringTokenizer(Base64.decodeAsString(authorization.getBytes()), ":");
	    String email = tokenizer.nextToken();
	    String password = tokenizer.nextToken();

	    UserService userService = new UserHibService();
	    User user = new User();
	    try
	    {
		  user = userService.findUserByEmail(email);

		  if (null == user)
		  {
			ctx.abortWith(Response.status(Status.UNAUTHORIZED).entity("User does not exists").build());
			return;
		  }

	    }
	    catch (DataServiceException e)
	    {
		  ctx.abortWith(Response.status(Status.INTERNAL_SERVER_ERROR).entity("Data access failed").build());
		  return;
	    }

	    if (!password.equals(user.getPassword()))
	    {
		  ctx.abortWith(Response.status(Status.UNAUTHORIZED).entity("Incorrect password").build());
		  return;
	    }

      }

}
