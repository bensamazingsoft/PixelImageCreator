package com.ben.pixcreator.gui.facade;

import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.pane.tabpane.PixTabPane;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public interface TabGuiFacade {

	void addTab(Tab tab);

	ObservableList<Tab> getTabs();

	void setPixTabPane(PixTabPane tabPane);

	PixTab getActiveTab();

}