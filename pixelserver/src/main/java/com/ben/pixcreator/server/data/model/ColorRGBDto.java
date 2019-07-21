
package com.ben.pixcreator.server.data.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javafx.scene.paint.Color;

/**
 * encapsulates a color in RGBA values (RGB:0-255 A:0.0-1.0). Values are stored in a 'rgb' map whose keys are String constants OPACITY,RED,GREEN and BLUE
 * 
 * @author bmo
 *
 */
@Entity
@Table(name = "COLOR_RGB")
@XmlRootElement(name = "color_rgb")
@XmlAccessorType(XmlAccessType.FIELD)
public class ColorRGBDto implements Serializable
{

      /**
       * 
       */
      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE)
      @Column(name = "ID")
      @XmlAttribute
      private Integer			    id;

      private static transient final long   serialVersionUID = 1L;

      private final transient static String RED		     = "red";

      private final transient static String GREEN	     = "green";

      private final transient static String BLUE	     = "blue";

      private final transient static String OPACITY	     = "opacity";

      @ElementCollection
      private Map<String, Double>	    rgb		     = new HashMap<>();

      @OneToOne(cascade = CascadeType.ALL)
      @XmlTransient
      private CoordDto			    coord;


      public ColorRGBDto()
      {

	    rgb.put(BLUE, 0.0);
	    rgb.put(GREEN, 0.0);
	    rgb.put(RED, 0.0);
	    rgb.put(OPACITY, 1.0);
      }


      public ColorRGBDto(Color color)
      {

	    rgb.put(BLUE, color.getBlue() * 255);
	    rgb.put(GREEN, color.getGreen() * 255);
	    rgb.put(RED, color.getRed() * 255);
	    rgb.put(OPACITY, color.getOpacity());
      }


      public ColorRGBDto(double red, double green, double blue, double opacity)
      {

	    this();

	    rgb.put(BLUE, blue);
	    rgb.put(GREEN, green);
	    rgb.put(RED, red);
	    rgb.put(OPACITY, opacity);
      }


      /**
       * @return the red value of this color (0-255)
       */
      public double getRed()
      {

	    return rgb.get(RED);

      }


      /**
       * @return the blue value of this color (0-255)
       */
      public double getBlue()
      {

	    return rgb.get(BLUE);

      }


      /**
       * @return the green value of this color (0-255)
       */
      public double getGreen()
      {

	    return rgb.get(GREEN);

      }


      /**
       * @return the opacity value of this color (0.0-1.0)
       */
      public double getOpacity()
      {

	    return rgb.get(OPACITY);

      }


      @Override
      public String toString()
      {

	    return "ColorRGBDto [id=" + id + ", rgb=" + rgb + "]";
      }


      @Override
      public int hashCode()
      {

	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((rgb == null) ? 0 : rgb.hashCode());
	    return result;
      }


      @Override
      public boolean equals(Object obj)
      {

	    if (this == obj)
		  return true;
	    if (obj == null)
		  return false;
	    if (getClass() != obj.getClass())
		  return false;
	    ColorRGBDto other = (ColorRGBDto) obj;
	    if (rgb == null)
	    {
		  if (other.rgb != null)
			return false;
	    }
	    else if (!rgb.equals(other.rgb))
		  return false;
	    return true;
      }


      public int getId()
      {

	    return id;
      }


      public void setId(int id)
      {

	    this.id = id;
      }


      public Map<String, Double> getRgb()
      {

	    return rgb;
      }


      public void setRgb(Map<String, Double> rgb)
      {

	    this.rgb = rgb;
      }


      public CoordDto getCoord()
      {

	    return coord;
      }


      public void setCoord(CoordDto coord)
      {

	    this.coord = coord;
      }


      public void setId(Integer id)
      {

	    this.id = id;
      }
}
