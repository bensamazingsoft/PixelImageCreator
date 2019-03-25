import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ben.pixcreator.application.pile.Pile;

public class PileTest
{

      @Test
      void addTest()
      {

	    Pile<String> pile1 = new Pile<>(2);

	    pile1.add("one");

	    Set<String> list = new HashSet<String>();
	    list.add("one");
	    assertEquals(pile1.getAllItems(), list);

	    pile1.add("two");
	    list.add("two");
	    assertEquals(pile1.getAllItems(), list);

	    // max size limit test
	    pile1.add("three");
	    assertTrue(pile1.getAllItems().size() == 2);

      }


      @Test
      public void getItemTest()
      {

	    Pile<String> pile1 = new Pile<>(0);
	    pile1.add("one");
	    pile1.add("two");
	    pile1.add("three");

	    assertEquals("two", pile1.getItem(1));

      }


      @Test
      public void getIdxTest()
      {

	    Pile<String> pile1 = new Pile<>(0);
	    pile1.add("one");
	    pile1.add("two");
	    pile1.add("three");

	    assertEquals(1, pile1.getIdx("two"));

      }


      @Test
      public void removeOfIndexTest()
      {

	    Pile<String> pile1 = new Pile<>(0);
	    pile1.add("one");
	    pile1.add("two");
	    pile1.add("three");

	    pile1.removeOfIndex(1);

	    Pile<String> pile2 = new Pile<>(0);
	    pile2.add("one");
	    pile2.add("three");

	    assertTrue(pile1.equals(pile2));

      }


      @Test
      public void removeOfitem()
      {

	    Pile<String> pile1 = new Pile<>(0);
	    pile1.add("one");
	    pile1.add("two");
	    pile1.add("three");

	    pile1.removeOfItem("two");

	    Pile<String> pile2 = new Pile<>(0);
	    pile2.add("one");
	    pile2.add("three");

	    assertTrue(pile1.equals(pile2));

      }


      @Test
      public void moveUp()
      {

	    Pile<String> pile1 = new Pile<>(0);
	    pile1.add("one");
	    pile1.add("two");
	    pile1.add("three");

	    pile1.moveUp("two");

	    Pile<String> pile2 = new Pile<>(0);
	    pile2.add("one");
	    pile2.add("three");
	    pile2.add("two");

	    assertTrue(pile1.equals(pile2));

      }

}
