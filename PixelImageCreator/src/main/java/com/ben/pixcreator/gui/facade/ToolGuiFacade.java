package com.ben.pixcreator.gui.facade;

import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;

import javafx.beans.property.SimpleBooleanProperty;

public interface ToolGuiFacade {

	void toggleToolTo(PixTool pixTool);

	void setPixToolBar(PixToolBar pixToolBar);

	PixToolBar getPixToolBar();

	PixTool getSelectedToolInToolbar();

	SimpleBooleanProperty showGridProperty();

	boolean isShowGrid();

	void setShowGrid(boolean showGrid);

	SimpleBooleanProperty panModeProperty();

	boolean isPanMode();

	void setPanMode(boolean panMode);

}