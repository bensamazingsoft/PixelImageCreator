
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.pane.web.IGridsManager;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.controls.EditableLabel;
import com.ben.pixcreator.gui.pane.web.controls.EditableTextArea;
import com.ben.pixcreator.gui.pane.web.controls.SelectableSearchFilters;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.gui.tooltip.provider.ToolTipProvider;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RegularGridView implements IGridViewer
{

      private IGridsManager    gridsManager;

      private final String     IMAGEPATH       = "images/gui/buttons/tools/";
      private final AppContext ctx	       = AppContext.getInstance();
      private ToolTipProvider  toolTipProvider = ctx.getToolTipProvider();
      private WebPanel	       webPanel;


      public RegularGridView(WebPanel webPanel, IGridsManager gridsManager)
      {

	    this.webPanel = webPanel;
	    this.gridsManager = gridsManager;
      }


      @Override
      public Node build(Set<PixelGrid> grids)
      {

	    VBox box = new VBox();

	    for (PixelGrid grid : grids)
	    {

		  box.getChildren().add(new RegularGridSticker(grid, gridsManager, webPanel.getLogBean().getData().getEmail().equals(grid.getOwner())));
	    }

	    return box;
      }

      public class RegularGridSticker extends BorderPane
      {

	    private PixelGrid	  grid;
	    private IGridsManager gridsManager;
	    private boolean	  owned;

	    private StackPane	  miniaturePane;
	    private BorderPane	  descrPanel;
	    private StackPane	  deleteButPane;
	    private Integer	  MINIATUREHEIGHT;
	    private Integer	  MINIATUREWIDTH;


	    public RegularGridSticker(PixelGrid grid, IGridsManager gridsManager, boolean owned)
	    {

		  super();
		  this.grid = grid;
		  this.gridsManager = gridsManager;
		  this.owned = owned;

		  // Miniature setup
		  MINIATUREHEIGHT = Integer.valueOf(ctx.propertyContext().get("miniatureWH"));
		  MINIATUREWIDTH = Integer.valueOf(ctx.propertyContext().get("miniatureWH"));

		  miniaturePane = new StackPane();
		  Canvas cnv = new Canvas(MINIATUREWIDTH, MINIATUREHEIGHT);
		  cnv.getGraphicsContext2D().drawImage(grid.getMiniature(), 0, 0);
		  miniaturePane.getChildren().add(cnv);
		  setLeft(miniaturePane);

		  // description setup
		  descrPanel = new BorderPane();
		  descrPanel.setTop(owned ? new EditableLabel(grid.getName()) : new Label(grid.getName()));
		  descrPanel.setCenter(owned ? new EditableTextArea(grid.getDescription()) : new TextArea(grid.getDescription()));
		  descrPanel.setBottom(
			      owned ? new SelectableSearchFilters(grid.getFilters()) : new Label(String.join(",", grid.getFilters().stream().map(SearchFilters::name).collect(Collectors.toSet()))));

		  // delete button setup
		  deleteButPane = new StackPane();

		  final Image deleteLayerButImg = new Image(
			      getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "deleteLayerButImg.png"));
		  Button deleteStickerBut = new Button();
		  deleteStickerBut.setGraphic(new ImageView(deleteLayerButImg));
		  deleteStickerBut.setTooltip(toolTipProvider.get("deleteWebRegularGridSticker"));
		  deleteButPane.getChildren().add(deleteStickerBut);

		  setRight(deleteButPane);
	    }

      }
}
