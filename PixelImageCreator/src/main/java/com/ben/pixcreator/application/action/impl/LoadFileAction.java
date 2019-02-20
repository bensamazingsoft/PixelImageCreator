
package com.ben.pixcreator.application.action.impl;

import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.file.PixFile;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class LoadFileAction implements IAction
{

      private final File file;


      public LoadFileAction(File file)
      {

	    this.file = file;
      }


      @Override
      public void execute() throws Exception
      {

	    try (FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);)
	    {

		  PixFile pixFile = (PixFile) in.readObject();

		  PixImage image = getImage(pixFile);

		  Executor.getInstance().executeAction(new OpenTabAction(image));

	    }
	    catch (IOException e)
	    {
		  new ExceptionPopUp(e);
	    }

      }


      private PixImage getImage(PixFile pixFile)
      {

	    GuiFacade gui = GuiFacade.getInstance();
	    GroupLock groupLock = AppContext.getInstance().getGroupLocks().get(gui.getActiveImage());
	    Map<ALayer, Set<ALayer>> lock = groupLock.getGroups();

	    PixImage image = pixFile.getImage();
	    Map<UUID, Set<UUID>> locks = pixFile.getLocks();
	    Map<UUID, Boolean> visibility = pixFile.getVisibility();

	    List<ALayer> imageLayers = image.getLayerList().getAllItems();
	    for (ALayer layer : imageLayers)
	    {

		  // set each layers visibility
		  layer.setVisible(visibility.get(layer.getUUID()));

		  // set the lock relations between each "active" layers and the
		  // others
		  if (locks.containsKey(layer.getUUID()))
		  {
			if (!locks.get(layer.getUUID()).isEmpty())
			{
			      Set<ALayer> locked = locks.get(layer.getUUID()).stream()
					  .map(uuid -> getLayerByUUID(uuid, imageLayers))
					  .collect(toSet());
			      lock.put(layer, locked);
			}
		  }

	    }

	    // set image colors in the gui facade map (checked when a tab is opened)
	    Set<ColorRGB> colors = pixFile.getColors();
	    gui.getImagesColors().put(image, colors.stream().map(ColorRGB::getColorProperty).collect(toSet()));

	    return image;
      }


      private ALayer getLayerByUUID(UUID uuid, List<ALayer> set)
      {

	    for (ALayer layer : set)
	    {
		  if (layer.getUUID().equals(uuid))
		  {
			return layer;
		  }
	    }

	    return null;
      }

}
