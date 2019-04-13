
package com.ben.pixcreator.application.action.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionChangeCellColor;
import com.ben.pixcreator.application.action.impl.ActionDeleteCell;
import com.ben.pixcreator.application.action.impl.ActionNoOp;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
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


      @Override
      public IAction getAction(Event event)
      {

	    // log.debug(event.getEventType().getName());

	    if (event instanceof MouseEvent && GuiFacade.getInstance().getActiveLayer() instanceof PixLayer)
	    {

		  switch (event.getEventType().getName())
		  {

		  case ("MOUSE_PRESSED"):
		  {
			if (((MouseEvent) event).getButton() == MouseButton.PRIMARY
				    || ((MouseEvent) event).getButton() == MouseButton.SECONDARY)
			{

			      Executor.getInstance().startOperation();

			      try
			      {
				    if (((MouseEvent) event).getButton() == MouseButton.PRIMARY)
				    {
					  Executor.getInstance().continueOperation((ICancelable) getChangeCellColorAction(event));
				    }
				    if (((MouseEvent) event).getButton() == MouseButton.SECONDARY)
				    {
					  Executor.getInstance().continueOperation((ICancelable) getDeleteCellAction(event));
				    }
			      }
			      catch (Exception e)
			      {
				    new ExceptionPopUp(e);
			      }
			}
			event.consume();
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
					  Executor.getInstance().continueOperation((ICancelable) getChangeCellColorAction(event));
				    }
				    if (((MouseEvent) event).getButton() == MouseButton.SECONDARY)
				    {
					  event.consume();
					  Executor.getInstance().continueOperation((ICancelable) getDeleteCellAction(event));
				    }
			      }
			      catch (Exception e)
			      {
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
			      Executor.getInstance().endOperation();
			      event.consume();
			}
			break;
		  }

		  case ("MOUSE_ENTERED"):
		  {

			event.consume();
			return new ActionNoOp();

		  }
		  case ("MOUSE_MOVED"):
		  {
			event.consume();
			return new ActionNoOp();
		  }
		  case ("MOUSE_EXITED"):
		  {
			event.consume();
			return new ActionNoOp();
		  }

		  }
	    }

	    return new ActionNoOp();

      }


      private IAction getDeleteCellAction(Event event)
      {

	    ALayer layer = GuiFacade.getInstance().getActiveLayer();
	    PixImage image = GuiFacade.getInstance().getActiveimage();

	    Coord coord = eventCoord((MouseEvent) event);

	    return new ActionDeleteCell(image, (PixLayer) layer, coord);

      }


      private IAction getChangeCellColorAction(Event event)
      {

	    PixImage image = GuiFacade.getInstance().getActiveimage();
	    ALayer layer = GuiFacade.getInstance().getActiveLayer();
	    Color color = GuiFacade.getInstance().getActiveColor();

	    Coord eventCoord = eventCoord((MouseEvent) event);

	    return new ActionChangeCellColor(image, (PixLayer) layer, eventCoord, color);

      }

}
