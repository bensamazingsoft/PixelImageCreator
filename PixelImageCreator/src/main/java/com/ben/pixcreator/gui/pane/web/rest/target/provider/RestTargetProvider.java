
package com.ben.pixcreator.gui.pane.web.rest.target.provider;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

public class RestTargetProvider
{

      // private static final Logger log = LoggerFactory.getLogger(RestTargetProvider.class);
      private static RestTargetProvider	instance;

      private URI			baseURI;
      private WebTarget			target;


      public RestTargetProvider()
      {

	    try
	    {
		  baseURI = new URI(AppContext.getInstance().propertyContext().get("baseWebTargetURI"));
	    }
	    catch (URISyntaxException e)
	    {
		  new ExceptionPopUp(e);
	    }

	    target = ClientBuilder.newClient().target(baseURI);

      }


      public WebTarget getBaseTarget()
      {

	    return target;
      }


      public static RestTargetProvider getInstance()
      {

	    if (null == instance)
	    {
		  instance = new RestTargetProvider();
	    }
	    return instance;
      }
}
