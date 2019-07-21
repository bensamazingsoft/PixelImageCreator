
package com.ben.pixcreator.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.gui.pane.web.SearchFilters;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

@XmlRootElement(name = "pixelgrid")
@XmlAccessorType(XmlAccessType.FIELD)
public class PixelGridDto implements Serializable
{

      /**
       * 
       */
      @XmlTransient
      private static final long	   serialVersionUID = 1L;

      @XmlAttribute
      private Integer		   id;
      @XmlAttribute
      private String		   name;
      @XmlAttribute
      private String		   owner;

      private Map<Coord, ColorRGB> grid;

      @XmlElementWrapper(name = "filters")
      @XmlElement(name = "filter")
      private Set<SearchFilters>   filters;

      @XmlElement
      private String		   description;

      byte[]			   miniatureBytes;


      public PixelGridDto()
      {

	    filters = new HashSet<>();
	    name = "new PixelGrid";
	    owner = "admin@pxl.com";
	    description = "new Pixel grid description";
	    miniatureBytes = new byte[8];
	    filters = new HashSet<>();
      }


      public PixelGridDto(String email, Map<Coord, ColorRGB> grid, Image image) throws IOException
      {

	    this();
	    this.owner = email;
	    this.grid = grid;
	    this.miniatureBytes = getBytes(image);
      }


      private byte[] getBytes(Image image) throws IOException
      {

	    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ImageIO.write(bImage, "png", bos);

	    return bos.toByteArray();
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


      public byte[] getMiniatureBytes()
      {

	    return miniatureBytes;
      }


      public void setMiniatureBytes(byte[] miniatureBytes)
      {

	    this.miniatureBytes = miniatureBytes;
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
