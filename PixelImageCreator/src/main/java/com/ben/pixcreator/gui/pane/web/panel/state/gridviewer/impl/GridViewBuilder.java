
package com.ben.pixcreator.gui.pane.web.panel.state.gridviewer.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.controls.EditableLabel;
import com.ben.pixcreator.gui.pane.web.controls.EditableTextArea;
import com.ben.pixcreator.gui.pane.web.controls.SelectableSearchFilters;
import com.ben.pixcreator.gui.pane.web.gridsmanager.IGridsManager;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.gui.pane.web.panel.state.gridviewer.IGridViewer;
import com.ben.pixcreator.gui.tooltip.provider.ToolTipProvider;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GridViewBuilder implements IGridViewer
{

      public static DataFormat dataFormat      = new DataFormat("pixcreator.PixelGrid");

      private IGridsManager    gridsManager;

      private final String     IMAGEPATH       = "images/gui/buttons/tools/";
      private final AppContext ctx	       = AppContext.getInstance();
      private ToolTipProvider  toolTipProvider = ctx.getToolTipProvider();
      private WebPanel	       webPanel;

      public static enum view {
	    REGULAR;
      }


      public GridViewBuilder(WebPanel webPanel, IGridsManager gridsManager)
      {

	    this.webPanel = webPanel;
	    this.gridsManager = gridsManager;
      }


      @Override
      public Node build(Set<PixelGrid> grids, GridViewBuilder.view view)
      {

	    switch (view.name())
	    {

	    case "REGULAR":

		  VBox box = new VBox();

		  for (PixelGrid grid : grids)
		  {

			final boolean owned = webPanel.getLogBean().getData().getEmail().equals(grid.getOwner());
			box.getChildren().add(new RegularGridSticker(grid, gridsManager, owned));
		  }

		  return box;

	    default:
		  return new StackPane();
	    }
      }

      public class RegularGridSticker extends BorderPane
      {

	    private StackPane  miniaturePane;
	    private BorderPane descrPanel;
	    private HBox       buttonsPane;
	    private Integer    MINIATUREHEIGHT;
	    private Integer    MINIATUREWIDTH;


	    public RegularGridSticker(PixelGrid grid, IGridsManager gridsManager, boolean owned)
	    {

		  super();

		  setOnDragDetected(new DragControl(grid, this));

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
		  setCenter(descrPanel);
		  // delete button setup
		  buttonsPane = new HBox();

		  final Image deleteLayerButImg = new Image(
			      getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "deleteLayerButImg.png"));
		  Button deleteStickerBut = new Button();
		  deleteStickerBut.setGraphic(new ImageView(deleteLayerButImg));
		  deleteStickerBut.setTooltip(toolTipProvider.get("deleteWebRegularGridSticker"));
		  deleteStickerBut.setOnAction(event -> {
			gridsManager.deleteGrid(webPanel.getLogBean().getData(), grid);
			webPanel.reload();
		  });
		  buttonsPane.getChildren().add(deleteStickerBut);

		  if (owned)
		  {
			final Image updateLayerButImg = new Image(
				    getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "updateLayerButImg.png"));
			Button updateStickerBut = new Button();
			updateStickerBut.setGraphic(new ImageView(updateLayerButImg));
			updateStickerBut.setTooltip(toolTipProvider.get("updateWebRegularGridSticker"));
			updateStickerBut.setOnAction(event -> {
			      gridsManager.updateGrid(webPanel.getLogBean().getData(), grid);
			      webPanel.reload();
			});
			buttonsPane.getChildren().add(deleteStickerBut);
		  }

		  setRight(buttonsPane);
	    }

      }

      public class DragControl implements EventHandler<MouseEvent>
      {

	    private PixelGrid grid;
	    private Node      sticker;


	    public DragControl(PixelGrid grid, Node sticker)
	    {

		  this.grid = grid;
		  this.sticker = sticker;
	    }


	    @Override
	    public void handle(MouseEvent evt)
	    {

		  Dragboard dragBoard = sticker.startDragAndDrop(TransferMode.ANY);
		  ClipboardContent dragboardContent = new ClipboardContent();
		  dragboardContent.put(dataFormat, grid);
		  dragBoard.setContent(dragboardContent);

		  evt.consume();
	    }

      }
}
