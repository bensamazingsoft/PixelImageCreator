
package com.ben.pixcreator.gui.cursor.factory;

import com.ben.pixcreator.application.context.AppContext;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;

public class CanvasCursorFactory implements CursorFactory
{

      /*
       * (non-Javadoc)
       * 
       * @see com.ben.pixcreator.gui.cursor.factory.CursorFactory#getCursor(java.lang. Boolean)
       */
      @Override
      public Cursor getCursor(Boolean panMode)
      {

	    if (panMode)
	    {

	    }

	    switch (AppContext.getInstance().getCurrTool())
	    {
	    case DRAW:
		  double[] X = {
			      16d,
			      9d,
			      23d };
		  double[] Y = {
			      2d,
			      13d,
			      13d };
		  ImageCursor cursor = new ImageCursor(makeImage("draw.png", X, Y, 3), 16, 2);
		  return cursor;
	    case MOVE:
		  break;
	    case NONE:
		  break;
	    case PAN:
		  break;
	    case PICK:

		  double[] Xpick = {
			      7,
			      28,
			      28,
			      7 };
		  double[] yPick = {
			      3,
			      3,
			      22,
			      22 };
		  ImageCursor pickCursor = new ImageCursor(makeImage("pick.png", Xpick, yPick, 4), 35, 28);

		  return pickCursor;
	    case RESIZE:
		  break;
	    case SELECT:
		  break;
	    case ZOOMIN:
		  break;
	    case ZOOMOUT:
		  break;
	    default:
		  break;

	    }

	    return Cursor.DEFAULT;
      }

}
