
package com.ben.pixcreator.server.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.ben.pixcreator.server.application.AppId;

@Provider
public class AppIdFilter implements ContainerRequestFilter
{

      @Override
      public void filter(ContainerRequestContext ctx) throws IOException
      {

	    String id = ctx.getHeaderString("app_id");

	    if (null != id && id.equals(AppId.id))
	    {

	    }
	    else
	    {
		  ctx.abortWith(Response.status(Status.UNAUTHORIZED).entity("Request was not originated in PixelCreator Application").build());
	    }

      }

}
