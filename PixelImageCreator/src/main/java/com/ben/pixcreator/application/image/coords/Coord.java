
package com.ben.pixcreator.application.image.coords;

import java.io.Serializable;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "coord")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coord implements Serializable, Comparable<Coord> {

	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private Integer id;

	private int x, y;

	public static final transient Comparator<Coord> COMPARATOR = Comparator.comparingInt(Coord::getY)
			.thenComparingInt(Coord::getX);

	public Coord(int x, int y) {

		super();
		this.x = x;
		this.y = y;
	}

	public Coord() {

		super();
		this.x = this.y = 0;

	}

	@Override
	public String toString() {

		return "Coord [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getX() {

		return x;
	}

	public void setX(int x) {

		this.x = x;
	}

	public int getY() {

		return y;
	}

	public void setY(int y) {

		this.y = y;
	}

	/**
	 * @param factor
	 * @return new Coord(getX() * factor, getY() * factor)
	 */
	public Coord mult(int factor) {

		return new Coord(getX() * factor, getY() * factor);

	}

	@Override
	public int compareTo(Coord oth) {

		return COMPARATOR.compare(this, oth);
	}

	/**
	 * 
	 * @param min
	 * @return a new Coord whose coordinates are sums of this and the min coord
	 */
	public Coord add(Coord min) {

		return new Coord(getX() + min.getX(), getY() + min.getY());
	}

	public Coord min(Coord other) {
		return new Coord(getX() - other.getX(), getY() - other.getY());
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

}
