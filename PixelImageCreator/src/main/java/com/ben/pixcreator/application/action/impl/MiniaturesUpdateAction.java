
package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class MiniaturesUpdateAction implements IAction
{

      private static final Logger log = LoggerFactory.getLogger(MiniaturesUpdateAction.class);

      private final PixImage	  image;


      public MiniaturesUpdateAction(PixTab pxTab)
      {

	    image = pxTab.getImage();
      }


      @Override
      public void execute() throws Exception
      {

	    log.debug("update");
	    for (ALayer layer : image.getLayerPile().getAllItems())
	    {

		  GuiFacade.getInstance().getMiniatureManager().update(layer);
	    }
      }
}