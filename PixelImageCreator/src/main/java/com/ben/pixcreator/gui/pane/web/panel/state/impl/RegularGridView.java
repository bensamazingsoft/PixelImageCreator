
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Set;

import com.ben.pixcreator.gui.pane.web.IGridsManager;
import com.ben.pixcreator.gui.pane.web.PixelGrid;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RegularGridView implements IGridViewer
{

      private IGridsManager gridsManager;


      public RegularGridView(IGridsManager gridsManager)
      {

	    this.gridsManager = gridsManager;
      }


      @Override
      public Node build(Set<PixelGrid> grids)
      {

	    VBox box = new VBox();

	    for (PixelGrid grid : grids)
	    {

		  box.getChildren().add(new RegularGridSticker(grid, gridsManager));
	    }

	    return box;
      }

      public class RegularGridSticker extends BorderPane
      {

	    private PixelGrid	  grid;
	    private IGridsManager gridsManager;

	    private StackPane	  miniaturePane;
	    private StackPane	  deleteButton;


	    public RegularGridSticker(PixelGrid grid, IGridsManager gridsManager)
	    {

		  super();
		  this.grid = grid;
		  this.gridsManager = gridsManager;

	    }

      }
}
