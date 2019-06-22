
package com.ben.pixcreator.gui.mainapp;

import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.controls.color.roster.ColorRoster;
import com.ben.pixcreator.gui.controls.layer.panel.LayerPanel;
import com.ben.pixcreator.gui.controls.menu.bar.PixMenuBar;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;
import com.ben.pixcreator.gui.pane.tabpane.PixTabPane;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController implements Initializable
{

      @FXML
      private PixMenuBar  menuBar;
      @FXML
      private PixToolBar  toolBar;
      // @FXML
      // private LayerPanel layerPanel;
      @FXML
      private PixTabPane  tabPane;
      @FXML
      private ColorRoster colorRoster;
      @FXML
      private SplitPane	  splitPane;
      @FXML
      private TabPane	  webAndLayerPanel;


      @Override
      public void initialize(URL location, ResourceBundle resources)
      {

	    splitPane.setDividerPosition(0, 0.73);

	    Tab layerTab = new Tab(AppContext.getInstance().getBundle().getString("layers"));
	    layerTab.setContent(new LayerPanel());
	    layerTab.setClosable(false);
	    Tab webTab = new Tab(AppContext.getInstance().getBundle().getString("web"));
	    webTab.setContent(new WebPanel());
	    webTab.setClosable(false);

	    webAndLayerPanel.getTabs().add(layerTab);
	    webAndLayerPanel.getTabs().add(webTab);

      }

}
