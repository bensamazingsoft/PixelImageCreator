
package com.ben.pixcreator.web;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class RequestFilter implements ClientRequestFilter

{

      @Override
      public void filter(ClientRequestContext ctx) throws IOException
      {

	    ctx.getHeaders().add("app_id", "f6MxIVYfWaRTIMhTmNramgeMSp3SpB6FaPoXX9UjTwWg64A1FfF8p3ZilpeDWiwcmf074DsSN1Jnpy6biIm81lssHA4nrkkoR39NCTu2RAhfCDicX9b5uPllA6mosxcW");

      }

}
