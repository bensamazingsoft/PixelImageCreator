
package com.ben.pixcreator.application.action.impl;

import java.io.File;
import java.util.Arrays;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.controls.layer.panel.actions.LayerActions;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class LayerAction implements IAction, ICancelable
{

      private final Pile<ALayer> layerList;
      private final ALayer	 layer;
      private final LayerActions action;


      public LayerAction(PixImage image, ALayer layer, LayerActions action)
      {

	    super();
	    this.layerList = image.getLayerList();
	    this.layer = layer;
	    this.action = action;
      }


      @Override
      public void execute() throws Exception
      {
	    // TODO unlock layer if locked & delete layer from image layerList

	    switch (action)
	    {
	    case ADDNEW:
		  addNew();
		  break;
	    case ADDNEWPIC:
		  addNewPic();
		  break;
	    case DELETE:
		  delete();
		  break;
	    case DUPLICATE:
		  duplicate();
		  break;
	    case MOVEDOWN:
		  moveDown();
		  break;
	    case MOVEUP:
		  moveUp();
		  break;
	    default:
		  break;

	    }

      }


      private void addNewPic()
      {

	    FileChooser fc = new FileChooser();
	    fc.getExtensionFilters().addAll(Arrays.asList(new ExtensionFilter("pics extensions", Arrays.asList("*.jpg", "*.png", "*.bmp"))));

	    File picfile = fc.showOpenDialog(null);
	    ;

	    if (null != picfile && picfile.exists())
	    {

		  layerList.insertOver(layer, new PicLayer(picfile));
	    }

      }


      private void moveUp()
      {
	    // TODO Auto-generated method stub

      }


      private void moveDown()
      {
	    // TODO Auto-generated method stub

      }


      private void duplicate()
      {
	    // TODO Auto-generated method stub

      }


      private void delete()
      {
	    // TODO Auto-generated method stub

      }


      private void addNew()
      {

	    layerList.insertOver(layer, new PixLayer());

      }


      @Override
      public void cancel() throws Exception
      {

      }

}
