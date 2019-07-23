
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.gridsmanager.IGridsService;
import com.ben.pixcreator.gui.pane.web.gridsmanager.impl.RestGridService;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.gui.pane.web.panel.state.WebPanelState;
import com.ben.pixcreator.gui.pane.web.panel.state.gridviewer.IGridViewer;
import com.ben.pixcreator.gui.pane.web.panel.state.gridviewer.impl.GridViewBuilder;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class LoggedWebPanelState implements WebPanelState
{

      private WebPanel			  webPanel;

      private BorderPane		  pane;

      private VBox			  errorBox = new VBox();

      private boolean			  userGridsOnly;
      private Map<SearchFilters, Boolean> filters;

      private IGridsService		  gridsService;
      private IGridViewer		  gridPreviewPane;


      public LoggedWebPanelState(WebPanel webPanel)
      {

	    this.webPanel = webPanel;
	    userGridsOnly = true;

	    // TODO inject REST impl
	    gridsService = new RestGridService();
	    gridPreviewPane = new GridViewBuilder(webPanel, gridsService);
	    pane = new BorderPane();
	    filters = new HashMap<>();

	    Arrays.asList(SearchFilters.values()).stream().forEach(filter -> filters.put(filter, false));

	    buildTopPane();

	    queryDb();

      }


      private void updateCenterPane()
      {

	    Set<PixelGrid> grids = webPanel.getPixelGridBean().getData();

	    pane.setCenter(new ScrollPane(gridPreviewPane.build(grids, GridViewBuilder.view.REGULAR)));

      }


      private void buildTopPane()
      {

	    TilePane top = new TilePane();

	    CheckBox userGridsCb = new CheckBox(AppContext.getInstance().getBundle().getString("my_designs"));
	    userGridsCb.setSelected(true);
	    userGridsCb.selectedProperty().addListener((obs, oldVal, newVal) -> {
		  setUserGridsOnly(newVal);
		  queryDb();
	    });
	    top.getChildren().add(userGridsCb);

	    filters.forEach((filter, selected) -> {
		  CheckBox filterCb = new CheckBox(filter.name());
		  filterCb.setSelected(filters.get(filter));
		  filterCb.selectedProperty().addListener((obs, oldVal, newVal) -> {

			filters.put(filter, newVal);
			queryDb();

		  });
		  top.getChildren().add(filterCb);
	    });

	    top.getChildren().add(errorBox);
	    pane.setTop(top);

      }


      private void queryDb()
      {

	    System.out.println("DB QUERYED");

	    webPanel.getPixelGridBean().getErrors().clear();
	    webPanel.getPixelGridBean().setMessage("");
	    errorBox.getChildren().clear();

	    Set<SearchFilters> filterSet = filters.entrySet().stream()
			.filter(e -> e.getValue().equals(Boolean.TRUE))
			.map(Entry::getKey)
			.collect(Collectors.toSet());

	    try
	    {
		  webPanel.getPixelGridBean().setData(gridsService.getGrids(isUserGridsOnly(), filterSet));
	    }
	    catch (Exception e)
	    {

		  webPanel.getPixelGridBean().getErrors().put("server", e.getMessage());
	    }

	    errorBox.getChildren().add(new Label(webPanel.getPixelGridBean().getMessage()));

	    webPanel.getPixelGridBean().getErrors().forEach((key, val) -> {
		  errorBox.getChildren().add(new Label(key + " : " + val));

	    });

	    updateCenterPane();
      }


      @Override
      public Node load()
      {

	    queryDb();
	    return pane;
      }


      @Override
      public void changeState(WebPanelState newState)
      {

	    webPanel.setState(newState);
	    webPanel.reload();

      }


      public boolean isUserGridsOnly()
      {

	    return userGridsOnly;
      }


      public void setUserGridsOnly(boolean userGridsOnly)
      {

	    this.userGridsOnly = userGridsOnly;
      }


      public Map<SearchFilters, Boolean> getFilters()
      {

	    return filters;
      }


      public void setFilters(Map<SearchFilters, Boolean> filters)
      {

	    this.filters = filters;
      }

}
