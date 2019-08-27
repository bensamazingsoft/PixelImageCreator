package com.ben.pixcreator.gui.facade;

import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.pane.tabpane.PixTabPane;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public class BasicTabGuiFacade implements TabGuiFacade {

	private PixTabPane pixTabPane;

	@Override
	public void addTab(Tab tab) {
		pixTabPane.getTabs().add(tab);
		pixTabPane.getSelectionModel().select(tab);

	}

	@Override
	public ObservableList<Tab> getTabs() {
		return pixTabPane.getTabs();
	}

	@Override
	public void setPixTabPane(PixTabPane tabPane) {
		this.pixTabPane = tabPane;

	}

	@Override
	public PixTab getActiveTab() {
		return (PixTab) pixTabPane.getSelectionModel().getSelectedItem();
	}

}
