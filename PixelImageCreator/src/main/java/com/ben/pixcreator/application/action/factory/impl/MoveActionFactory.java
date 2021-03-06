
package com.ben.pixcreator.application.action.factory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.NoOpAction;
import com.ben.pixcreator.application.action.impl.TranslateLayerAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public class MoveActionFactory implements IActionFactory
{

      @SuppressWarnings("unused")
      private static final Logger		log = LoggerFactory.getLogger(MoveActionFactory.class);

      public static boolean			moveStarted;
      public static Coord			startCoord;
      public static Map<ALayer, ALayer.Memento>	startState;

      private PixTab				tab = GuiFacade.getInstance().getActiveTab();;


      @Override
      public IAction getAction(Event event)
      {

	    if (event instanceof MouseEvent)
	    {

		  final Executor exec = Executor.getInstance();
		  switch (event.getEventType().getName())
		  {

		  case ("MOUSE_PRESSED"):
		  {
			exec.startOperation();
			moveStarted = true;
			startCoord = eventCoord((MouseEvent) event);
			startState = new HashMap<>();

			GuiFacade gui = GuiFacade.getInstance();
			GroupLock groupLock = AppContext.getInstance().getGroupLocks().get(gui.getActiveImage());

			ALayer activeLayer = gui.getActiveLayer();
			Set<ALayer> layers = groupLock.getLockedLayers(activeLayer);
			layers.add(activeLayer);

			for (ALayer layer : layers)
			{
			      startState.put(layer, layer.getMemento());
			}
			// log.debug(event.getEventType().getName());
			// log.debug(startCoord.toString());

			break;
		  }

		  case ("MOUSE_DRAGGED"):
		  {

			GuiFacade gui = GuiFacade.getInstance();
			GroupLock groupLock = AppContext.getInstance().getGroupLocks().get(gui.getActiveImage());

			PixImage image = gui.getActiveImage();
			ALayer activeLayer = gui.getActiveLayer();

			Set<ALayer> layers = groupLock.getLockedLayers(activeLayer);
			layers.add(activeLayer);

			Selection selection = GuiFacade.getInstance().getSelections().get(image);

			Coord eventCoord = eventCoord((MouseEvent) event);

			int translateX = eventCoord.getX() - startCoord.getX();
			int translateY = eventCoord.getY() - startCoord.getY();
			// log.debug("" + translateX);
			for (ALayer layer : layers)
			{
			      // log.debug(startState.toString());
			      try
			      {
				    // restore the initial (move start) state of the layer
				    // to compute the translation from there
				    startState.get(layer).restore();
				    exec
						.continueOperation(new TranslateLayerAction(translateX, translateY, layer, selection));
				    exec.executeAction(new RefreshTabAction(tab));
			      }
			      catch (Exception e)
			      {
				    try
				    {
					  exec.abortOperation();
				    }
				    catch (Exception e1)
				    {
					  new ExceptionPopUp(e1);
				    }
				    new ExceptionPopUp(e);
			      }

			}

			break;
		  }

		  case ("MOUSE_RELEASED"):
		  {
			exec.endOperation();
			moveStarted = false;
			startCoord = null;
			// log.debug(event.getEventType().getName());

			break;
		  }
		  case ("MOUSE_ENTERED"):
		  {

			return new NoOpAction();
		  }
		  case ("MOUSE_MOVED"):
		  {

			return new NoOpAction();
		  }
		  case ("MOUSE_EXITED"):
		  {

			return new NoOpAction();
		  }
		  }

	    }

	    return new NoOpAction();
      }

}
