
package com.ben.pixcreator.application.pile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents an item stack backed with an Array of index-item pairs.
 * 
 * If a max size is set > 0, the pile pops out the lower indexed item after add
 * operations.
 * 
 * @author bmo
 *
 */
public class BasicPile<T> implements Serializable, Pile<T> {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final Set<BasicPair<T>>		items				= new HashSet<>();
	private int					maxSize				= 0;

	public BasicPile() {

	}

	public BasicPile(int max) {

		this();
		setMaxSize(max);
	}

	public BasicPile(Pile<T> pile) {

		setMaxSize(pile.getMaxSize());

		for (int i = 0; i < pile.getAllItems().size(); i++) {

			add(pile.getItem(i));

		}

	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#add(T)
	 */
	@Override
	public boolean add(T item) {

		final boolean result = items.add(new BasicPair<T>(items.size(), item));

		if (maxSize > 0) {
			constraintToMaxSize();
		}

		return result;

	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#getItem(int)
	 */
	@Override
	public T getItem(int idx) {

		for (BasicPair<T> basicPair : items) {
			if (basicPair.getIdx() == idx) {
				return basicPair.getItem();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#getIdx(T)
	 */
	@Override
	public int getIdx(T item) {

		for (BasicPair<T> basicPair : items) {
			if (basicPair.getItem().equals(item)) {
				return basicPair.getIdx();
			}
		}
		return -1;
	}

	private void constraintToMaxSize() {

		while (items.size() > maxSize) {
			removeOfIndex(0);
		}

	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#removeOfIndex(int)
	 */
	@Override
	public boolean removeOfIndex(int idx) {

		boolean success = false;
		T item = getItem(idx);

		if (getAllItems().contains(item)) {

			for (BasicPair<T> basicPair : new ArrayList<>(items)) {
				if (basicPair.getItem().equals(item)) {
					success = items.remove(basicPair);
				}
			}

			if (success) {
				for (BasicPair<T> basicPair : items) {
					if (basicPair.getIdx() >= idx) {
						basicPair.decr();
					}
				}
			}
		}
		return success;

	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#removeOfItem(T)
	 */
	@Override
	public boolean removeOfItem(T item) {

		boolean success = false;
		int idx = getIdx(item);

		if (getAllItems().contains(item)) {

			for (BasicPair<T> basicPair : new HashSet<>(items)) {
				if (basicPair.getItem().equals(item)) {
					success = items.remove(basicPair);
				}
			}

			if (success) {
				for (BasicPair<T> basicPair : items) {
					if (basicPair.getIdx() >= idx) {
						basicPair.decr();
					}
				}
			}
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#moveUp(T)
	 */
	@Override
	public int moveUp(T item) {

		if (getIdx(item) > -1 && getIdx(item) < (items.size() - 1)) {
			getPairOfIdx(getIdx(item) + 1).decr();
			getPairOfItem(item).incr();

			return getIdx(item);

		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#moveDown(T)
	 */
	@Override
	public int moveDown(T item) {

		if (getIdx(item) > 0) {
			getPairOfIdx(getIdx(item) - 1).incr();
			getPairOfItem(item).decr();
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#insertOver(T, T)
	 */
	@Override
	public int insertOver(T item, T newitem) {

		int idx = getIdx(item);
		if (idx > -1) {
			items.add(new BasicPair<T>(++idx, newitem));
			for (BasicPair<T> basicPair : items) {
				if (basicPair.getIdx() >= idx && !basicPair.getItem().equals(newitem)) {
					basicPair.incr();
				}
			}
			if (maxSize > 0) {
				constraintToMaxSize();
			}
			return getIdx(newitem);
		} else {
			items.add(new BasicPair<T>(0, newitem));
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#insertUnder(T, T)
	 */
	@Override
	public int insertUnder(T item, T newitem) {

		int idx = getIdx(item);
		if (idx > -1) {
			items.add(new BasicPair<T>(idx, newitem));
			for (BasicPair<T> basicPair : items) {
				if (basicPair.getIdx() >= idx && !basicPair.getItem().equals(newitem)) {
					basicPair.incr();
				}
			}
			if (maxSize > 0) {
				constraintToMaxSize();
			}
			return getIdx(newitem);
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#replace(int, T)
	 */
	@Override
	public boolean replace(int i, T other) {

		for (BasicPair<T> basicPair : items) {
			if (basicPair.getIdx() == i) {
				basicPair.setItem(other);
				return true;
			}
		}
		return false;
	}

	// /**
	// * inserts the item at specified index
	// *
	// * @param i
	// * @param other
	// * @return true if the item was successfully inserted.</br>
	// * False if the index is not 'free'.
	// */
	// private boolean insertAt(int idx, T item)
	// {
	//
	// if (getItem(idx) == null)
	// {
	// items.add(new BasicPair(idx, item));
	// }
	// return false;
	// }

	private BasicPair<T> getPairOfIdx(int idx) {

		for (BasicPair<T> basicPair : items) {
			if (basicPair.getIdx() == idx) {
				return basicPair;
			}
		}
		return null;
	}

	private BasicPair<T> getPairOfItem(T item) {

		for (BasicPair<T> basicPair : items) {
			if (basicPair.getItem().equals(item)) {
				return basicPair;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#getItems()
	 */
	@Override
	public Set<BasicPair<T>> getItems() {

		return items;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#isEmpty()
	 */
	@Override
	public boolean isEmpty() {

		return items.isEmpty();

	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#getAllItems()
	 */
	@Override
	public Set<T> getAllItems() {

		Set<T> list = new HashSet<T>();
		list.addAll(items.stream().map(BasicPair::getItem).collect(Collectors.toSet()));
		return list;
	}

	@Override
	public String toString() {

		return "BasicPile [items=" + items + "]";
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#getMaxSize()
	 */
	@Override
	public int getMaxSize() {

		return maxSize;
	}

	/* (non-Javadoc)
	 * @see com.ben.pixcreator.application.pile.Pile#setMaxSize(int)
	 */
	@Override
	public void setMaxSize(int maxSize) {

		this.maxSize = Math.max(0, maxSize);
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + maxSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BasicPile)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		BasicPile<T> other = (BasicPile<T>) obj;
		if (items == null) {
			if (other.items != null) {
				return false;
			}
		}

		for (T item : getAllItems()) {

			if (!other.getAllItems().contains(item)) {
				return false;
			}

			if (getIdx(item) != other.getIdx(item)) {
				return false;
			}

		}

		if (getAllItems().size() != other.getAllItems().size()) {
			return false;
		}

		if (maxSize != other.maxSize) {
			return false;
		}
		return true;
	}

}