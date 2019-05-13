
package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ChangeCellColorAction;
import com.ben.pixcreator.application.action.impl.DeleteCellAction;
import com.ben.pixcreator.application.action.impl.NoOpAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawActionFactory implements IActionFactory
{

      @SuppressWarnings("unused")
      private static final Logger log = LoggerFactory.getLogger(DrawActionFactory.class);
      private PixTab		  tab = GuiFacade.getInstance().getActiveTab();


      @Override
      public IAction getAction(Event event)
      {

	    // log.debug(event.getEventType().getName());

	    if (event instanceof MouseEvent && GuiFacade.getInstance().getActiveLayer() instanceof PixLayer)
	    {

		  final Executor exec = Executor.getInstance();
		  switch (event.getEventType().getName())
		  {

		  case ("MOUSE_PRESSED"):
		  {
			if (((MouseEvent) event).getButton() == MouseButton.PRIMARY
				    || ((MouseEvent) event).getButton() == MouseButton.SECONDARY)
			{

			      exec.startOperation();

			      try
			      {
				    if (((MouseEvent) event).getButton() == MouseButton.PRIMARY)
				    {
					  exec.continueOperation((ICancelable) getChangeCellColorAction(event));
					  exec.executeAction(new RefreshTabAction(tab));
				    }
				    if (((MouseEvent) event).getButton() == MouseButton.SECONDARY)
				    {
					  exec.continueOperation((ICancelable) getDeleteCellAction(event));
					  exec.executeAction(new RefreshTabAction(tab));
				    }
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

		  case ("MOUSE_DRAGGED"):
		  {

			if (((MouseEvent) event).getButton() == MouseButton.PRIMARY
				    || ((MouseEvent) event).getButton() == MouseButton.SECONDARY)
			{
			      try
			      {
				    if (((MouseEvent) event).getButton() == MouseButton.PRIMARY)
				    {
					  exec.continueOperation((ICancelable) getChangeCellColorAction(event));
					  exec.executeAction(new RefreshTabAction(tab));
				    }
				    if (((MouseEvent) event).getButton() == MouseButton.SECONDARY)
				    {

					  exec.continueOperation((ICancelable) getDeleteCellAction(event));
					  exec.executeAction(new RefreshTabAction(tab));
				    }
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
			if (((MouseEvent) event).getButton() == MouseButton.PRIMARY
				    || ((MouseEvent) event).getButton() == MouseButton.SECONDARY)
			{
			      exec.endOperation();

			}
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


      private IAction getDeleteCellAction(Event event)
      {

	    ALayer layer = GuiFacade.getInstance().getActiveLayer();
	    PixImage image = GuiFacade.getInstance().getActiveimage();

	    Coord coord = eventCoord((MouseEvent) event);

	    return new DeleteCellAction(image, (PixLayer) layer, coord);

      }


      private IAction getChangeCellColorAction(Event event)
      {

	    PixImage image = GuiFacade.getInstance().getActiveimage();
	    ALayer layer = GuiFacade.getInstance().getActiveLayer();
	    Color color = GuiFacade.getInstance().getActiveColor();

	    Coord eventCoord = eventCoord((MouseEvent) event);

	    return new ChangeCellColorAction(image, (PixLayer) layer, eventCoord, color);

      }

}
