
package com.ben.pixcreator.gui.pane.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

@Entity
@Table(name = "PIXELGRID")
public class PixelGrid
{

      @Id
      @Column(name = "ID")
      @GeneratedValue(strategy = GenerationType.AUTO)
      private int		   id;

      @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
      @JoinTable(
		  name = "GRID",
		  joinColumns = @JoinColumn(name = "PIXELGRID_id"),
		  inverseJoinColumns = @JoinColumn(name = "COLORRGB_id"))
      @MapKeyJoinColumn(name = "COORD_id")
      private Map<Coord, ColorRGB> grid;

      @OneToMany
      private Set<SearchFilters>   filters;

      @Column(name = "DESCR", nullable = true)
      private String		   description;

      @Transient
      private Image		   miniature;

      @Column(name = "MINI_BYTES")
      byte[]			   miniatureBytes;


      public PixelGrid()
      {

	    super();
	    filters = new HashSet<>();
	    description = "";
      }


      public PixelGrid(Map<Coord, ColorRGB> grid)
      {

	    super();
	    this.grid = grid;
      }


      public PixelGrid(Map<Coord, ColorRGB> grid, Set<SearchFilters> filters)
      {

	    super();
	    this.grid = grid;
	    this.filters = filters;
      }


      public PixelGrid(Map<Coord, ColorRGB> grid, Set<SearchFilters> filters, String description)
      {

	    super();
	    this.grid = grid;
	    this.filters = filters;
	    this.description = description;
      }


      public PixelGrid(int id, Map<Coord, ColorRGB> grid, Set<SearchFilters> filters, String description)
      {

	    super();
	    this.id = id;
	    this.grid = grid;
	    this.filters = filters;
	    this.description = description;
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

	    return "PixelGrid [id=" + id + ", grid=" + grid + ", filters=" + filters + ", description=" + description + "]";
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

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ImageIO.write(SwingFXUtils.fromFXImage(miniature, null), "PNG", out);

	    setMiniatureBytes(out.toByteArray());
      }


      public byte[] getMiniatureBytes()
      {

	    return miniatureBytes;
      }


      public void setMiniatureBytes(byte[] miniatureBytes)
      {

	    this.miniatureBytes = miniatureBytes;
      }

}
