package com.ben.pixcreator.gui.tooltip.provider;

import java.util.ResourceBundle;

import javafx.scene.control.Tooltip;

public class ToolTipProvider {

	private ResourceBundle bundle;

	public ToolTipProvider(ResourceBundle tipBundle) {
		this.bundle = tipBundle;
	}

	public Tooltip get(String string) {

		Tooltip tip = new Tooltip(bundle.getString(string));

		return tip;
	}

}
