
package com.ben.pixcreator.server.data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PIXELGRID")
@XmlRootElement(name = "pixelgrid")
@XmlAccessorType(XmlAccessType.FIELD)
public class PixelGridDto implements Serializable
{

      /**
       * 
       */
      @XmlTransient
      private static final long		 serialVersionUID = 1L;

      @Id
      @Column(name = "ID")
      @GeneratedValue(strategy = GenerationType.AUTO)
      @XmlAttribute
      private Integer			 id;

      @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
      // @JoinTable(
      // name = "GRID",
      // joinColumns = @JoinColumn(name = "PIXELGRID_id"),
      // inverseJoinColumns = @JoinColumn(name = "COLORRGB_id")
      // )
      // @MapKeyJoinColumn(name = "COORD_id")
      private Map<CoordDto, ColorRGBDto> grid;

      @ElementCollection
      @XmlElementWrapper(name = "filters")
      @XmlElement(name = "filter")
      private Set<String>		 filters;

      @Column(name = "NAME")
      @XmlAttribute
      private String			 name;

      @Column(name = "OWNER")
      @XmlAttribute
      private String			 owner;

      @Column(name = "DESCR", nullable = true)
      @XmlElement
      private String			 description;

      @Column(name = "MINI_BYTES")
      byte[]				 miniatureBytes;


      public PixelGridDto()
      {

	    filters = new HashSet<>();
	    name = "new PixelGrid";
	    owner = "";
	    description = "new Pixel grid description";
	    miniatureBytes = new byte[8];
      }


      public Map<CoordDto, ColorRGBDto> getGrid()
      {

	    return grid;
      }


      public void setGrid(Map<CoordDto, ColorRGBDto> grid)
      {

	    this.grid = grid;
      }


      public Set<String> getFilters()
      {

	    return filters;
      }


      public void setFilters(Set<String> filters)
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
