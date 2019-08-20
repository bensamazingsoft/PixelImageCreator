
package com.ben.pixcreator.server.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "COORD")
@XmlRootElement(name = "coord")
@XmlAccessorType(XmlAccessType.FIELD)
public class CoordDto implements Serializable
{

      /**
       * 
       */
      @XmlTransient
      private static final long	serialVersionUID = 1L;

      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE)
      @Column(name = "ID")
      @XmlAttribute
      private Integer		id;

      @Column(name = "X")
      private int		x;

      @Column(name = "Y")
      private int		y;


      public CoordDto(int x, int y)
      {

	    super();
	    this.x = x;
	    this.y = y;
      }


      public CoordDto()
      {

	    super();
	    this.x = this.y = 0;

      }


      @Override
      public String toString()
      {

	    return "CoordDto [id=" + id + ", x=" + x + ", y=" + y + "]";
      }


      @Override
      public int hashCode()
      {

	    final int prime = 31;
	    int result = 1;
	    result = prime * result + x;
	    result = prime * result + y;
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
	    CoordDto other = (CoordDto) obj;
	    if (x != other.x)
		  return false;
	    if (y != other.y)
		  return false;
	    return true;
      }


      public int getX()
      {

	    return x;
      }


      public void setX(int x)
      {

	    this.x = x;
      }


      public int getY()
      {

	    return y;
      }


      public void setY(int y)
      {

	    this.y = y;
      }

}
