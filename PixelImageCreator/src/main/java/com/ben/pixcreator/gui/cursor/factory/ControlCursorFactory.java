
package com.ben.pixcreator.gui.cursor.factory;

import com.ben.pixcreator.application.context.AppContext;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;

public class ControlCursorFactory implements CursorFactory
{

      /*
       * (non-Javadoc)
       * 
       * @see com.ben.pixcreator.gui.cursor.factory.CursorFactory#getCursor(java.lang. Boolean)
       */
      @Override
      public Cursor getCursor()
      {

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
		  return new ImageCursor(makeImage("move.png"), 11, 4);
	    case NONE:
		  break;
	    case PAN:
		  return new ImageCursor(makeImage("hand.png"), 11, 4);
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
		  ImageCursor pickCursor = new ImageCursor(makeImage("pick.png", Xpick, yPick, 4), 3, 28);

		  return pickCursor;
	    case RESIZE:
		  break;
	    case SELECT:

		  return new ImageCursor(makeImage("select.png"), 14, 16);
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
