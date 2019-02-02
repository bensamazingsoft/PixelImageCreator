
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.gui.controls.tab.PixTab;

import javafx.scene.canvas.Canvas;

public class RefreshTabAction implements IAction
{

      private final PixImage image;
      private final Canvas   canvas;


      public RefreshTabAction(PixTab pxTab)
      {

	    canvas = pxTab.getCanvas();
	    image = pxTab.getImage();
      }


      @Override
      public void execute() throws Exception
      {

	    canvas.getGraphicsContext2D().clearRect(0, 0, image.getxSize(), image.getySize());
	    image.draw(canvas);

      }

}
