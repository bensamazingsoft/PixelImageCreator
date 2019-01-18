package com.ben.pixcreator.application.image.layer.impl;

import java.io.Serializable;

import com.ben.pixcreator.application.image.layer.ILayer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;

public abstract class ALayer implements ILayer, Serializable {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	/**
	 * 
	 */

	private SimpleBooleanProperty	visible				= new SimpleBooleanProperty();

	@Override
	public void show(Canvas canvas, int xGridResolution, int yGridResolution) {

	}

	public final SimpleBooleanProperty visibleProperty() {
		return this.visible;
	}

	public final boolean isVisible() {
		return this.visibleProperty().get();
	}

	public final void setVisible(final boolean visible) {
		this.visibleProperty().set(visible);
	}

}
