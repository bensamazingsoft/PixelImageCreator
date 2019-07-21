
package com.ben.pixcreator.application.action.impl;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.gui.miniature.manager.MiniatureManager;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.web.PixelGridDto;
import com.ben.pixcreator.web.exception.WebException;
import com.ben.pixcreator.web.target.provider.RestTargetProvider;

public class ExportGridOnWebAction implements IAction
{

      private static final Logger  log = LoggerFactory.getLogger(ExportGridOnWebAction.class);

      private LogInfo		   logInfo;
      private Map<Coord, ColorRGB> grid;
      private PixLayer		   layer;


      public ExportGridOnWebAction(LogInfo logInfo, ALayer layer)
      {

	    this.layer = (PixLayer) layer;
	    this.logInfo = logInfo;
	    this.grid = this.layer.getGrid();

      }


      @Override
      public void execute() throws Exception
      {

	    log.debug("");

	    RestTargetProvider restTargetProvider = new RestTargetProvider();
	    WebTarget target = restTargetProvider.getBaseTarget().path("add");

	    PixelGridDto pixelGridDto = new PixelGridDto(logInfo.getEmail(), grid, MiniatureManager.backup.get(layer));
	    Response response = target.request(MediaType.APPLICATION_XML).put(Entity.entity(pixelGridDto, MediaType.APPLICATION_XML));

	    int status = response.getStatus();
	    log.debug("Server replied : " + status);

	    if (status == 200)
	    {
		  PixelGridDto resp = response.readEntity(PixelGridDto.class);
		  log.debug(resp.toString());
	    }
	    else
	    {
		  throw new WebException("Unable to upload data\n" + response.readEntity(String.class));
	    }

      }

}
