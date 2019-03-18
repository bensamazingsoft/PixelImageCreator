
package com.ben.pixcreator.gui.cursor.factory;

import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public interface CursorFactory
{

      final static String IMAGEPATH = "images/gui/cursors/";


      /**
       * @param panMode
       *              : is the active tab canvas mouse transparent ?
       * @return a Cursor
       */
      Cursor getCursor();


      /**
       * 
       * draws a colored polygon on the icon image with the current active color.
       * 
       * @param string
       *              - PNG filename
       * @param X
       *              array - containing the x coordinates of the polygon's points or null.
       * @param Y
       *              array - containing the y coordinates of the polygon's points or null.
       * @param nPoints
       *              - the number of points that make the polygon.
       * @return
       */
      default Image makeImage(String string, double[] X, double[] Y, int nPoints)
      {

	    Image image = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + string));

	    Canvas canvas = new Canvas(32, 32);
	    canvas.getGraphicsContext2D().setFill(GuiFacade.getInstance().getActiveColor());
	    canvas.getGraphicsContext2D().fillPolygon(X, Y, nPoints);
	    canvas.getGraphicsContext2D().drawImage(image, 0, 0);

	    @SuppressWarnings("unused")
	    Scene bogus = new Scene(new StackPane(canvas));
	    SnapshotParameters snapshotParameters = new SnapshotParameters();
	    snapshotParameters.setFill(Color.rgb(0, 0, 0, 0));
	    Image snap = canvas.snapshot(snapshotParameters, null);

	    return snap;
      }


      default Image makeImage(String string)
      {

	    return new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + string));
      }

}