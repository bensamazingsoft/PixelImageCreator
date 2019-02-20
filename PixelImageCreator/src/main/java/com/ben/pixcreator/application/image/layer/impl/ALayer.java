
package com.ben.pixcreator.application.image.layer.impl;

import java.io.Serializable;
import java.util.UUID;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.ILayer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;

public abstract class ALayer implements ILayer, Serializable
{

      private static final long			serialVersionUID = 1L;

      protected transient SimpleBooleanProperty	visible		 = new SimpleBooleanProperty();
      protected final UUID			uuid;


      public ALayer()
      {

	    super();
	    this.uuid = UUID.randomUUID();
      }


      @Override
      public void draw(Canvas canvas, int xGridResolution, int yGridResolution)
      {

      }

      public abstract class Memento
      {

	    protected ALayer layer;


	    protected Memento(ALayer layer)
	    {

		  this.layer = layer;

		  init(layer);
	    }


	    protected abstract void init(ALayer layer);


	    public abstract void restore();

      }


      public abstract Memento getMemento();


      public final SimpleBooleanProperty visibleProperty()
      {

	    return this.visible;
      }


      public final boolean isVisible()
      {

	    return this.visibleProperty().get();
      }


      public final void setVisible(final boolean visible)
      {

	    this.visibleProperty().set(visible);
      }


      @Override
      public boolean equals(Object obj)
      {

	    if (obj instanceof ALayer)
	    {
		  return this.getUUID().equals(((ALayer) obj).getUUID());
	    }
	    return false;
      }


      @Override
      public int hashCode()
      {

	    return getUUID().hashCode();
      }


      public abstract ALayer offset(Coord min);


      public UUID getUUID()
      {

	    return uuid;
      }

}
