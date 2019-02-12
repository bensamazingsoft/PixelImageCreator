
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

	protected SimpleBooleanProperty	visible				= new SimpleBooleanProperty();

	@Override
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

	}

	public abstract class Memento {

		protected ALayer layer;

		protected Memento(ALayer layer) {

			this.layer = layer;

			init(layer);
		}

		protected abstract void init(ALayer layer);

		public abstract void restore();

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

	@Override
	public boolean equals(Object arg0) {

		return super.equals(arg0);
	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	public abstract Memento getMemento();

}
