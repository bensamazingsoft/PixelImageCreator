
package com.ben.pixcreator.gui.pane.web.panel.state.gridviewer.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.controls.EditableLabel;
import com.ben.pixcreator.gui.pane.web.controls.EditableTextArea;
import com.ben.pixcreator.gui.pane.web.controls.SelectableSearchFilters;
import com.ben.pixcreator.gui.pane.web.gridsmanager.IGridsService;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.gui.pane.web.panel.state.gridviewer.IGridViewer;
import com.ben.pixcreator.gui.tooltip.provider.ToolTipProvider;
import com.ben.pixcreator.web.exception.WebException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GridViewBuilder implements IGridViewer
{

      public static DataFormat dataFormat      = new DataFormat("pixcreator.PixelGrid");

      private IGridsService    gridsService;

      private final String     IMAGEPATH       = "images/gui/buttons/tools/";
      private final AppContext ctx	       = AppContext.getInstance();
      private ToolTipProvider  toolTipProvider = ctx.getToolTipProvider();
      private WebPanel	       webPanel;

      public static enum view {
	    REGULAR;
      }


      public GridViewBuilder(WebPanel webPanel, IGridsService gridsService)
      {

	    this.webPanel = webPanel;
	    this.gridsService = gridsService;
      }


      @Override
      public Node build(Set<PixelGrid> grids, GridViewBuilder.view view)
      {

	    switch (view.name())
	    {

	    case "REGULAR":

		  VBox box = new VBox();
		  box.getStylesheets().add("/styles/styles.css");
		  box.getStyleClass().add("RegularGridStickerVBox");
		  box.setSpacing(4);

		  for (PixelGrid grid : grids)
		  {

			final boolean owned = webPanel.getLogBean().getData().getEmail().equals(grid.getOwner());
			box.getChildren().add(new RegularGridSticker(grid, gridsService, owned));
		  }

		  return box;

	    default:
		  return new StackPane();
	    }
      }

      public class RegularGridSticker extends HBox
      {

	    private StackPane  miniaturePane;
	    private BorderPane descrPanel;
	    private HBox       buttonsPane;
	    private Integer    MINIATUREHEIGHT;
	    private Integer    MINIATUREWIDTH;


	    public RegularGridSticker(PixelGrid grid, IGridsService gridsService, boolean owned)
	    {

		  super();

		  getStylesheets().add("/styles/styles.css");
		  getStyleClass().add("RegularGridSticker");

		  setOnDragDetected(new DragControl(grid, this));

		  // Miniature setup
		  MINIATUREHEIGHT = Integer.valueOf(ctx.propertyContext().get("miniatureWH"));
		  MINIATUREWIDTH = Integer.valueOf(ctx.propertyContext().get("miniatureWH"));

		  miniaturePane = new StackPane();
		  miniaturePane.setMinWidth(MINIATUREWIDTH);
		  miniaturePane.setMinHeight(MINIATUREWIDTH);
		  miniaturePane.setMaxWidth(MINIATUREWIDTH);
		  miniaturePane.setMaxHeight(MINIATUREWIDTH);
		  // Canvas cnv = new Canvas(MINIATUREWIDTH, MINIATUREHEIGHT);
		  // cnv.getGraphicsContext2D().drawImage(grid.getMiniature(), 0, 0);
		  ImageView view = new ImageView();
		  view.setPreserveRatio(true);
		  view.setFitWidth(MINIATUREWIDTH);
		  view.setFitHeight(MINIATUREHEIGHT);
		  view.setImage(grid.getMiniature());

		  // miniaturePane.getStylesheets().add("/styles/styles.css");
		  miniaturePane.getStyleClass().add("RegularGridStickerMiniaturePane");
		  StackPane.setMargin(view, new Insets(5));
		  miniaturePane.getChildren().add(view);
		  setAlignment(Pos.CENTER);
		  getChildren().add(miniaturePane);

		  // description setup
		  descrPanel = new BorderPane();
		  HBox.setHgrow(descrPanel, Priority.ALWAYS);
		  descrPanel.getStyleClass().add("RegularGridStickerDescrPanel");
		  EditableLabel editableLabel = new EditableLabel(grid.getName());
		  editableLabel.getStyleClass().add("RegularGridStickerDescrPanelLabel");
		  Label label = new Label(grid.getName());
		  label.getStyleClass().add("RegularGridStickerDescrPanelLabel");
		  descrPanel.setTop(owned ? editableLabel : new StackPane(label));

		  EditableTextArea editableTextArea = new EditableTextArea(grid.getDescription());
		  TextArea textArea = new TextArea(grid.getDescription());
		  textArea.setMaxHeight(40);
		  textArea.setEditable(false);
		  descrPanel.setCenter(owned ? editableTextArea : textArea);

		  SelectableSearchFilters selectableSearchFilters = new SelectableSearchFilters(grid.getFilters());
		  VBox vBox = new VBox(
			      owned ? selectableSearchFilters : new Label(String.join(",", grid.getFilters().stream().map(SearchFilters::name).collect(Collectors.toSet()))));
		  vBox.getStyleClass().add("RegularGridStickerSelectableSearchFilters");
		  descrPanel.setBottom(vBox);

		  getChildren().add(descrPanel);

		  if (owned)
		  {
			buttonsPane = new HBox();
			buttonsPane.setAlignment(Pos.CENTER);

			final Image updateLayerButImg = new Image(
				    getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "updateLayerButImg.png"));
			Button updateStickerBut = new Button();
			updateStickerBut.setMaxHeight(10);
			updateStickerBut.setMaxWidth(10);
			updateStickerBut.setGraphic(new ImageView(updateLayerButImg));
			updateStickerBut.setTooltip(toolTipProvider.get("updateWebRegularGridSticker"));
			updateStickerBut.setOnAction(event -> {

			      grid.setName(editableLabel.getText());
			      grid.setDescription(editableTextArea.getText());
			      grid.setFilters(selectableSearchFilters.getFilters());

			      try
			      {
				    gridsService.updateGrid(grid);
			      }
			      catch (WebException e)
			      {
				    new ExceptionPopUp(e);
			      }

			      webPanel.reload();
			});
			buttonsPane.getChildren().add(updateStickerBut);

			final Image deleteLayerButImg = new Image(
				    getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "deleteLayerButImg.png"));
			Button deleteStickerBut = new Button();
			deleteStickerBut.setGraphic(new ImageView(deleteLayerButImg));
			deleteStickerBut.setTooltip(toolTipProvider.get("deleteWebRegularGridSticker"));
			deleteStickerBut.setMaxHeight(10);
			deleteStickerBut.setMaxWidth(10);
			deleteStickerBut.setOnAction(event -> {
			      try
			      {
				    gridsService.deleteGrid(grid);
				    webPanel.getPixelGridBean().getData().remove(grid);

			      }
			      catch (WebException e)
			      {
				    new ExceptionPopUp(e);
			      }
			      webPanel.reload();
			});
			buttonsPane.getChildren().add(deleteStickerBut);

			getChildren().add(new StackPane(buttonsPane));
		  }

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
