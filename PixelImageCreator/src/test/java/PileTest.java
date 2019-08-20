import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ben.pixcreator.application.pile.BasicPile;
import com.ben.pixcreator.application.pile.Pile;

public class PileTest {

	@Test
	void addTest() {

		Pile<String> pile1 = new BasicPile<>(2);

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
	public void getItemTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");

		assertEquals("two", pile1.getItem(1));

	}

	@Test
	public void getIdxTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");

		assertEquals(1, pile1.getIdx("two"));

	}

	@Test
	public void removeOfIndexTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");

		pile1.removeOfIndex(1);

		Pile<String> pile2 = new BasicPile<>(0);
		pile2.add("one");
		pile2.add("three");

		assertEquals(pile1, pile2);

	}

	@Test
	public void removeOfitemTest() {

		Pile<String> pile0 = new BasicPile<>(0);
		pile0.add("zero");
		pile0.removeOfItem("zero");
		assertTrue(pile0.isEmpty());

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");

		pile1.removeOfItem("two");

		Pile<String> pile2 = new BasicPile<>(0);
		pile2.add("one");
		pile2.add("three");

		assertEquals(pile1, pile2);

		//
		pile1.removeOfItem("one");

		Pile<String> pile3 = new BasicPile<>(0);
		pile3.add("three");

		assertEquals(pile1, pile3);

		//
		pile1.removeOfItem("three");

		Pile<String> pile4 = new BasicPile<>(0);

		assertEquals(pile1, pile4);

	}

	@Test
	public void moveUpTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");

		pile1.moveUp("two");

		Pile<String> pile2 = new BasicPile<>(0);
		pile2.add("one");
		pile2.add("three");
		pile2.add("two");

		assertEquals(pile1, pile2);

	}

	@Test
	public void moveDownTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");

		pile1.moveDown("three");

		Pile<String> pile2 = new BasicPile<>(0);
		pile2.add("one");
		pile2.add("three");
		pile2.add("two");

		assertEquals(pile1, pile2);

	}

	@Test
	public void insertOverTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");
		pile1.add("four");

		Pile<String> pile2 = new BasicPile<>(0);
		pile2.add("one");
		pile2.add("two");
		pile2.add("four");

		pile2.insertOver("two", "three");

		assertEquals(pile1, pile2);

	}

	@Test
	public void insertUnderTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("three");
		pile1.add("four");

		Pile<String> pile2 = new BasicPile<>(0);
		pile2.add("one");
		pile2.add("two");
		pile2.add("four");

		pile2.insertUnder("four", "three");

		assertEquals(pile1, pile2);

	}

	@Test
	public void replaceTest() {

		Pile<String> pile1 = new BasicPile<>(0);
		pile1.add("one");
		pile1.add("two");
		pile1.add("dummy");
		pile1.add("four");

		Pile<String> pile2 = new BasicPile<>(0);
		pile2.add("one");
		pile2.add("two");
		pile2.add("three");
		pile2.add("four");

		pile1.replace(2, "three");

		assertEquals(pile1, pile2);

	}

}
