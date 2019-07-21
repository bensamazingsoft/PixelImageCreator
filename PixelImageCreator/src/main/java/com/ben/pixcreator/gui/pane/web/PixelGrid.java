
package com.ben.pixcreator.gui.pane.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class PixelGrid implements Serializable
{

      /**
       * 
       */
      private static final long	   serialVersionUID = 1L;

      private int		   id;

      private Map<Coord, ColorRGB> grid;

      private Set<SearchFilters>   filters;

      private String		   name;

      private String		   owner;

      private String		   description;

      private final String	   IMAGEPATH	    = "images/gui/web/";
      private transient Image	   miniature	    = new Image(getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "dummyMiniature.png"));


      public PixelGrid()
      {

	    filters = new HashSet<>();
	    name = "new PixelGrid";
	    owner = "admin@pxl.com";
	    description = "new Pixel grid description";

      }


      public static WritableImage fxImage(byte[] miniatureBytes) throws IOException
      {

	    return SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream(miniatureBytes)), null);
      }


      public PixelGrid(Map<Coord, ColorRGB> grid, Set<SearchFilters> filters, String name, String owner, String description, Image miniature)
      {

	    super();
	    this.grid = grid;
	    this.filters = filters;
	    this.name = name;
	    this.owner = owner;
	    this.description = description;
	    this.miniature = miniature;
      }


      public Map<Coord, ColorRGB> getGrid()
      {

	    return grid;
      }


      public void setGrid(Map<Coord, ColorRGB> grid)
      {

	    this.grid = grid;
      }


      public Set<SearchFilters> getFilters()
      {

	    return filters;
      }


      public void setFilters(Set<SearchFilters> filters)
      {

	    this.filters = filters;
      }


      public String getDescription()
      {

	    return description;
      }


      public void setDescription(String description)
      {

	    this.description = description;
      }


      @Override
      public String toString()
      {

	    return "PixelGrid [id=" + id + ", grid=" + grid + ", filters=" + filters + ", name=" + name + ", description=" + description + "]";
      }


      public int getId()
      {

	    return id;
      }


      public void setId(int id)
      {

	    this.id = id;
      }


      public Image getMiniature()
      {

	    return miniature;
      }


      public void setMiniature(Image miniature) throws IOException
      {

	    this.miniature = miniature;

      }


      public String getName()
      {

	    return name;
      }


      public void setName(String name)
      {

	    this.name = name;
      }


      public String getOwner()
      {

	    return owner;
      }


      public void setOwner(String owner)
      {

	    this.owner = owner;
      }

}
