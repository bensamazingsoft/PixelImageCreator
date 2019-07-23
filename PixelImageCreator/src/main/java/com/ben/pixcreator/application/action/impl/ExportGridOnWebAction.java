
package com.ben.pixcreator.application.action.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.gridsmanager.impl.RestGridService;
import com.ben.pixcreator.web.PixelGridDto;

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

	    grid = layer.origin(layer.minCell()).getGrid();

	    RestGridService restGridService = new RestGridService();

	    PixelGridDto pixelGridDto = new PixelGridDto(logInfo.getEmail(), grid, GuiFacade.getInstance().getMiniatureManager().getImage(layer));

	    restGridService.saveGrid(pixelGridDto);

      }

}
