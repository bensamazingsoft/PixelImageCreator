
package com.ben.pixcreator.gui.facade;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.layer.panel.LayerPanel;
import com.ben.pixcreator.gui.controls.menu.bar.PixMenuBar;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;

import javafx.scene.Scene;
import javafx.scene.control.Toggle;

public class GuiFacade
{

      private static GuiFacade instance;

      private Scene	       scene;
      private PixMenuBar       pixMenuBar;
      private PixToolBar       pixToolBar;
      private LayerPanel       layerPanel;


      private GuiFacade()
      {

      }


      public static GuiFacade getInstance()
      {

	    if (instance == null)
	    {

		  instance = new GuiFacade();
	    }

	    return instance;
      }


      public void toggleToolTo(PixTool pixTool) throws IOException
      {

	    // toggle tool in AppContext and Gui Controls

	    AppContext.getInstance().setCurrTool(pixTool);

	    List<Toggle> list = pixToolBar.getToggleGroup().getToggles()
			.stream()
			.filter(togBut -> ((PixTool) togBut.getUserData()).name().equals(pixTool.name()))
			.map(togl -> {
			      (PixTool) togl.getUserData();
			      })
				    .collect(Collectors.toList());

	    if (list.size() > 0)
	    {
		  Toggle toggle = list.get(0);
		  pixToolBar.getToggleGroup().selectToggle(toggle);
	    }

      }


      public PixImage getActiveImage()
      {

	    PixImage activeImage;
	    // TODO retrieve current active image
	    return activeImage;
      }


      public Scene getScene()
      {

	    return scene;
      }


      public void setScene(Scene scene)
      {

	    this.scene = scene;
      }


      public PixMenuBar getPixMenuBar()
      {

	    return pixMenuBar;
      }


      public void setPixMenuBar(PixMenuBar pixMenuBar)
      {

	    this.pixMenuBar = pixMenuBar;
      }

}
