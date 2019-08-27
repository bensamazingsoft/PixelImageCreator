package com.ben.pixcreator.gui.facade;

import com.ben.pixcreator.application.action.impl.ChangeToolAction;
import com.ben.pixcreator.application.action.impl.SetCursorsAction;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

import javafx.beans.property.SimpleBooleanProperty;

public class BasicToolGuiFacade implements ToolGuiFacade {

	private final Executor exec = Executor.getInstance();

	private PixToolBar				pixToolBar;
	private SimpleBooleanProperty	showGrid	= new SimpleBooleanProperty();
	private SimpleBooleanProperty	panMode		= new SimpleBooleanProperty();

	@Override
	public void toggleToolTo(PixTool pixTool) {

		try {

			exec.executeAction(new ChangeToolAction(pixTool));
			exec.executeAction(new SetCursorsAction());

		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@Override
	public void setPixToolBar(PixToolBar pixToolBar) {
		this.pixToolBar = pixToolBar;

	}

	@Override
	public PixToolBar getPixToolBar() {
		return pixToolBar;
	}

	@Override
	public PixTool getSelectedToolInToolbar() {
		return getPixToolBar().getSelectedToggleData();
	}

	@Override
	public SimpleBooleanProperty showGridProperty() {
		return this.showGrid;
	}

	@Override
	public boolean isShowGrid() {
		return this.showGridProperty().get();
	}

	@Override
	public void setShowGrid(boolean showGrid) {
		this.showGridProperty().set(showGrid);

	}

	@Override
	public SimpleBooleanProperty panModeProperty() {
		return this.panMode;
	}

	@Override
	public boolean isPanMode() {
		return this.panModeProperty().get();
	}

	@Override
	public void setPanMode(boolean panMode) {
		this.panModeProperty().set(panMode);

	}

}
