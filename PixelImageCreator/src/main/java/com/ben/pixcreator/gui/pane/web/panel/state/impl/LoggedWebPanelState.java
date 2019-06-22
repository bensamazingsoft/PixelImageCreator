
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.pane.web.IGridsManager;
import com.ben.pixcreator.gui.pane.web.MockGridRetriever;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.SearchFilters;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.gui.pane.web.panel.state.WebPanelState;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

public class LoggedWebPanelState implements WebPanelState
{

      private WebPanel			  webPanel;

      private BorderPane		  pane;

      private boolean			  userGridsOnly;
      private Map<SearchFilters, Boolean> filters;

      private IGridsManager		  gridsManager;
      private IGridViewer		  gridPreviewPane;


      public LoggedWebPanelState(WebPanel webPanel)
      {

	    this.webPanel = webPanel;

	    // TODO inject REST impl
	    gridsManager = new MockGridRetriever();

	    userGridsOnly = true;

	    filters = new HashMap<>();

	    Arrays.asList(SearchFilters.values()).stream().forEach(filter -> filters.put(filter, false));

	    pane = new BorderPane();
	    buildTopPane();

	    queryDb();

	    gridPreviewPane = new RegularGridView(gridsManager);

	    updateCenterPane();
      }


      private void updateCenterPane()
      {

	    Set<PixelGrid> grids = webPanel.getPixelGridBean().getData();

	    pane.setCenter(gridPreviewPane.build(grids));

      }


      private void buildTopPane()
      {

	    TilePane top = new TilePane();

	    CheckBox userGridsCb = new CheckBox(AppContext.getInstance().getBundle().getString("my_designs"));
	    userGridsCb.setSelected(true);
	    userGridsCb.selectedProperty().addListener((obs, oldVal, newVal) -> queryDb());
	    top.getChildren().add(userGridsCb);

	    filters.forEach((filter, selected) -> {
		  CheckBox filterCb = new CheckBox(filter.name());
		  filterCb.setSelected(filters.get(filter));
		  filterCb.selectedProperty().addListener((obs, oldVal, newVal) -> queryDb());
		  top.getChildren().add(filterCb);
	    });

	    pane.setTop(top);

      }


      private void queryDb()
      {

	    Set<SearchFilters> filterSet = filters.entrySet().stream()
			.filter(e -> e.getValue().equals(Boolean.TRUE))
			.map(Entry::getKey)
			.collect(Collectors.toSet());

	    webPanel.getPixelGridBean().setData(gridsManager.getGrids(isUserGridsOnly(), filterSet));
      }


      @Override
      public Node load()
      {

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
