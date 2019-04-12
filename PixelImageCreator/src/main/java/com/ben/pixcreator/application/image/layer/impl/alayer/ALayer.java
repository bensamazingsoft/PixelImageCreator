
package com.ben.pixcreator.application.image.layer.impl.alayer;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.ILayer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;

public abstract class ALayer implements ILayer, Serializable {

	/**
	   * 
	   */
	private static final long serialVersionUID = 1L;

	protected transient SimpleBooleanProperty	visible	= new SimpleBooleanProperty();
	protected UUID								uuid;
	protected double							opacity	= 1d;
	protected double							sizeFactorX;
	protected double							sizeFactorY;

	public ALayer() {

		super();
		this.uuid = UUID.randomUUID();
		setVisible(true);
	}

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

	public abstract Memento getMemento();

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
	public boolean equals(Object obj) {

		if (obj instanceof ALayer) {
			return this.getUUID().equals(((ALayer) obj).getUUID());
		}
		return false;
	}

	@Override
	public int hashCode() {

		return getUUID().hashCode();
	}

	public abstract ALayer duplicate();

	public abstract ALayer offset(Coord min);

	public UUID getUUID() {

		return uuid;
	}

	private void readObject(java.io.ObjectInputStream in)
			throws IOException, ClassNotFoundException {

		in.defaultReadObject();
		visible = new SimpleBooleanProperty();
	}

	public double getOpacity() {

		return opacity;
	}

	public void setOpacity(double opacity) {

		this.opacity = opacity;
	}

	public double getSizeFactorX() {

		return sizeFactorX;
	}

	public void setSizeFactorX(double sizeFactorX) {

		this.sizeFactorX = sizeFactorX;
	}

	public double getSizeFactorY() {

		return sizeFactorY;
	}

	public void setSizeFactorY(double sizeFactorY) {

		this.sizeFactorY = sizeFactorY;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
