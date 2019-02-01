
package com.ben.pixcreator.gui.mainapp;

import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.gui.controls.color.roster.ColorRoster;
import com.ben.pixcreator.gui.controls.layer.panel.LayerPanel;
import com.ben.pixcreator.gui.controls.menu.bar.PixMenuBar;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.pane.tabpane.PixTabPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable {

	@FXML
	private PixMenuBar	menuBar;
	@FXML
	private PixToolBar	toolBar;
	@FXML
	private LayerPanel	layerPanel;
	@FXML
	private PixTabPane	tabPane;
	@FXML
	private ColorRoster	colorRoster;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		GuiFacade.getInstance().setColorRoster(colorRoster);
		GuiFacade.getInstance().setLayerPanel(layerPanel);
		GuiFacade.getInstance().setPixTabPane(tabPane);

	}
}
