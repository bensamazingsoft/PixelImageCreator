
package com.ben.pixcreator.gui.pane.web.controls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.gui.pane.web.SearchFilters;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SelectableSearchFilters extends VBox
{

      private Set<SearchFilters> filters;

      private Label		 label;


      public SelectableSearchFilters(
		  Set<SearchFilters> filters)
      {

	    this.filters = filters;
	    displayMode();

      }


      private void modifyMode()
      {

	    getChildren().clear();

	    TilePane selectPane = new TilePane();
	    HashMap<SearchFilters, Boolean> AllFilters = new HashMap<>();

	    Arrays.asList(SearchFilters.values()).stream().forEach(filter -> AllFilters.put(filter, false));

	    AllFilters.forEach((filter, selected) -> {
		  CheckBox filterCb = new CheckBox(filter.name());
		  filterCb.setSelected(filters.contains(filter));
		  filterCb.selectedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal)
			{
			      filters.add(filter);
			}
			else
			{
			      filters.remove(filter);
			}
		  });
		  selectPane.getChildren().add(filterCb);
	    });

	    VBox container = new VBox();
	    container.getChildren().add(selectPane);

	    Button okBtn = new Button("OK");
	    okBtn.setAlignment(Pos.BASELINE_RIGHT);
	    okBtn.setOnAction(event -> {
		  displayMode();
	    });

	    container.getChildren().add(okBtn);

	    getChildren().add(container);

      }


      private void displayMode()
      {

	    getChildren().clear();

	    label = new Label(String.join(",", filters.stream().map(SearchFilters::name).collect(Collectors.toSet())));

	    label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		  if (event.getEventType().getName().equals("MOUSE_CLICKED") && event.getClickCount() > 1)
		  {

			modifyMode();

		  }
	    });

	    getChildren().add(label);
      }


      public Set<SearchFilters> getFilters()
      {

	    return filters;
      }


      public void setFilters(Set<SearchFilters> filters)
      {

	    this.filters = filters;
      }

}
