
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
public class Pile<T> implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final Set<Pair>		items				= new HashSet<>();
	private int					maxSize				= 0;

	public Pile() {

	}

	public Pile(int max) {

		this();
		setMaxSize(max);
	}

	/**
	 * adds a new item to this itemList on "top" of the others. The index
	 * assigned to this new item is the current size of the list, no check is
	 * done on the existing indexes.
	 * 
	 * @param item
	 * @return true if the new item was successfully added to this item list.
	 */
	public boolean add(T item) {

		final boolean result = items.add(new Pair(items.size(), item));

		if (maxSize > 0) {
			constraintToMaxSize();
		}

		return result;

	}

	/**
	 * @param idx
	 * @return the item or null.
	 */
	public T getItem(int idx) {

		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				return pair.getItem();
			}
		}
		return null;
	}

	/**
	 * @param item
	 * @return the index of the item or -1 if the item is not present in this
	 *         itemList
	 */
	public int getIdx(T item) {

		for (Pair pair : items) {
			if (pair.getItem().equals(item)) {
				return pair.getIdx();
			}
		}
		return -1;
	}

	private void constraintToMaxSize() {

		while (items.size() > maxSize) {
			removeOfIndex(0);
		}

	}

	/**
	 * removes the item associated with the index in this item list. \nReturns
	 * false if the index was either not found or the removal was unsuccessful.
	 * 
	 * @param idx
	 * @return true if the item was successfully removed from this item list
	 */
	public boolean removeOfIndex(int idx) {

		boolean success = false;
		T item = getItem(idx);

		if (getAllItems().contains(item)) {

			for (Pair pair : new ArrayList<>(items)) {
				if (pair.getItem().equals(item)) {
					success = items.remove(pair);
				}
			}

			if (success) {
				for (Pair pair : items) {
					if (pair.getIdx() >= idx) {
						pair.decr();
					}
				}
			}
		}
		return success;

	}

	/**
	 * removes the item from this item list. <br/>
	 * Returns false if the item was either not found or the removal was
	 * unsuccessful.
	 * 
	 * @param item
	 * @return true if the item was successfully removed from this item list
	 */
	public boolean removeOfItem(T item) {

		boolean success = false;
		int idx = getIdx(item);

		if (getAllItems().contains(item)) {

			for (Pair pair : new ArrayList<>(items)) {
				if (pair.getItem().equals(item)) {
					success = items.remove(pair);
				}
			}

			if (success) {
				for (Pair pair : items) {
					if (pair.getIdx() >= idx) {
						pair.decr();
					}
				}
			}
		}
		return success;
	}

	/**
	 * moves the item one slot up in this item list.
	 * 
	 * @param item
	 * @return the new index of the item after the move or -1 if the item is not
	 *         present in this item list
	 */
	public int moveUp(T item) {

		if (getIdx(item) > -1 && getIdx(item) < (items.size() - 1)) {
			getPairOfIdx(getIdx(item) + 1).decr();
			getPairOfItem(item).incr();

			return getIdx(item);

		}
		return -1;
	}

	/**
	 * moves the item one slot down in this item list or nothing if the item has
	 * the index 0.
	 * 
	 * @param item
	 * @return the new index of the item after the move or -1 if the item is not
	 *         present in this item list or has the index 0
	 */
	public int moveDown(T item) {

		if (getIdx(item) > 0) {
			getPairOfIdx(getIdx(item) - 1).incr();
			getPairOfItem(item).decr();
		}
		return -1;
	}

	/**
	 * inserts a new item in this item list right above another given item.<br/>
	 * Indexes are modified accordingly.
	 * 
	 * @param item
	 * @param newitem
	 * @return the index of the new item after the move or -1 if the item is not
	 *         present in this item list
	 */
	public int insertOver(T item, T newitem) {

		int idx = getIdx(item);
		if (idx > -1) {
			items.add(new Pair(++idx, newitem));
			for (Pair pair : items) {
				if (pair.getIdx() >= idx && !pair.getItem().equals(newitem)) {
					pair.incr();
				}
			}
			if (maxSize > 0) {
				constraintToMaxSize();
			}
			return getIdx(newitem);
		}
		return -1;
	}

	/**
	 * * inserts a new item in this item list right under another given
	 * item.<br/>
	 * Indexes are modified accordingly.
	 * 
	 * @param item
	 * @param newitem
	 * @return the index of the new item after the move or -1 if the item is not
	 *         present in this item list
	 */
	public int insertUnder(T item, T newitem) {

		int idx = getIdx(item);
		if (idx > -1) {
			items.add(new Pair(idx, newitem));
			for (Pair pair : items) {
				if (pair.getIdx() >= idx && !pair.getItem().equals(newitem)) {
					pair.incr();
				}
			}
			if (maxSize > 0) {
				constraintToMaxSize();
			}
			return getIdx(newitem);
		}
		return -1;
	}

	/**
	 * replaces the item at specified index with supplied replacement.
	 * 
	 * @param i
	 * @param other
	 * @return true if the item was successfully replaced
	 */
	public boolean replace(int i, T other) {

		if (items.remove(new Pair(i, getItem(i)))) {
			return insertAt(i, other);
		}
		return false;
	}

	/**
	 * inserts the item at specified index
	 * 
	 * @param i
	 * @param other
	 * @return true if the item was successfully inserted.</br>
	 *         False if the index is not 'free'.
	 */
	private boolean insertAt(int idx, T item) {

		if (getItem(idx) == null) {
			items.add(new Pair(idx, item));
		}
		return false;
	}

	// /**
	// * deletes the item from this pile.</br>
	// * performs removeOfItem and then dercrement indexes if necessary.
	// *
	// * @param item
	// * @return true if the item was successfully deleted from this pile.
	// */
	// public boolean deleteOfitem(T item) {
	//
	// int idx = getIdx(item);
	//
	// if (removeOfItem(item)) {
	//
	// for (Pair pair : items) {
	// if (pair.getIdx() > idx) {
	// pair.decr();
	// }
	// }
	//
	// }
	//
	// return false;
	//
	// }

	/**
	 * utility class that represents an index-item association.
	 * 
	 * @author bmo
	 *
	 */
	public class Pair implements Serializable, Comparable {

		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
		int							idx;
		T							item;

		public Pair(int idx, T item) {

			this.idx = idx;
			this.item = item;
		}

		public void incr() {

			idx++;
		}

		public void decr() {

			Math.max(0, idx--);
		}

		public int getIdx() {

			return idx;
		}

		public T getItem() {

			return item;
		}

		@Override
		public String toString() {

			return "Pair [idx=" + idx + ", item=" + item + "]";
		}

		@Override
		public int hashCode() {

			final int prime = 31;
			int result = 1;
			result = prime * result + idx;
			result = prime * result + ((item == null) ? 0 : item.hashCode());
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
			Pair other = (Pair) obj;
			if (idx != other.idx) {
				return false;
			}
			if (item == null) {
				if (other.item != null) {
					return false;
				}
			} else if (!item.equals(other.item)) {
				return false;
			}
			return true;
		}

		private Pile getOuterType() {

			return Pile.this;
		}

		@Override
		public int compareTo(Object arg0) {

			Pile<T>.Pair pair = (Pair) arg0;
			return this.idx - pair.idx;

		}

	}

	private Pair getPairOfIdx(int idx) {

		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				return pair;
			}
		}
		return null;
	}

	private Pair getPairOfItem(T item) {

		for (Pair pair : items) {
			if (pair.getItem().equals(item)) {
				return pair;
			}
		}
		return null;
	}

	public Set<Pair> getItems() {

		return items;
	}

	public boolean isEmpty() {

		return items.isEmpty();

	}

	/**
	 * Convienience method, return a java.util.List holding the effects of the
	 * layer
	 * 
	 * @return java.util.List<T>
	 */
	public Set<T> getAllItems() {

		Set<T> list = new HashSet<T>();
		list.addAll(items.stream().map(Pair::getItem).collect(Collectors.toSet()));
		return list;
	}

	@Override
	public String toString() {

		return "Pile [items=" + items + "]";
	}

	public int getMaxSize() {

		return maxSize;
	}

	/**
	 * Cant be <0
	 * 
	 * @param maxSize
	 */
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
		if (!(obj instanceof Pile)) {
			return false;
		}
		Pile other = (Pile) obj;
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