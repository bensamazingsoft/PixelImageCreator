
package com.ben.pixcreator.application.image.coords;

import java.util.Comparator;

public class Coord implements Comparable<Coord>
{

      private int			    x, y;
      public static final Comparator<Coord> COMPARATOR = Comparator.comparingInt(Coord::getX).thenComparingInt(Coord::getX);;


      public Coord(int x, int y)
      {

	    super();
	    this.x = x;
	    this.y = y;
      }


      public Coord()
      {

	    super();
	    this.x = this.y = 0;

      }


      @Override
      public String toString()
      {

	    return "Coord [x=" + x + ", y=" + y + "]";
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
	    Coord other = (Coord) obj;
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


      @Override
      public int compareTo(Coord oth)
      {

	    // if (obj instanceof Coord)
	    // {
	    // Coord other = (Coord) obj;
	    //
	    // if (this.getX() == other.getX())
	    // {
	    //
	    // if (this.getY() < other.getY())
	    // {
	    // return -1;
	    // }
	    //
	    // if (this.getY() > other.getY())
	    // {
	    // return 1;
	    // }
	    // return 0;
	    //
	    // }
	    // else
	    // {
	    //
	    // if (this.getX() < other.getX())
	    // {
	    // return -1;
	    // }
	    // else
	    // {
	    // return 1;
	    // }
	    // }
	    //
	    // }
	    return COMPARATOR.compare(this, oth);
      }

      // public static class CoordComparator implements Comparator<Coord>
      // {
      //
      // @Override
      // public int compare(Coord o1, Coord o2)
      // {
      //
      // return o1.compareTo(o2);
      // }
      //
      // }
}
