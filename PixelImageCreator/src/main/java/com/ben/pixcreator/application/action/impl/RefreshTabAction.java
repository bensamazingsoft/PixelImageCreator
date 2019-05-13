
package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.draw.factory.DrawImageFactory;
import com.ben.pixcreator.gui.controls.tab.PixTab;

import javafx.scene.canvas.Canvas;

public class RefreshTabAction implements IAction
{

      private static final Logger log = LoggerFactory.getLogger(RefreshTabAction.class);

      private final PixImage	  image;
      private final Canvas	  canvas;


      public RefreshTabAction(PixTab pxTab)
      {

	    canvas = pxTab.getCanvas();
	    image = pxTab.getImage();
      }


      @Override
      public void execute() throws Exception
      {

	    log.debug(image.toString());
	    canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

	    Executor.getInstance().executeAction(new UpdateSelectionAction(image));
	    DrawImageFactory.getDrawImage(image).draw(canvas);

      }
}
