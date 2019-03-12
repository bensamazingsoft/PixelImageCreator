
package com.ben.pixcreator.application.pile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an item stack backed with an Array of index-item pairs.
 * 
 * @author bmo
 *
 */
public class Pile<T> implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	List<Pair>					items				= new ArrayList<>();

	public Pile() {

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

	/**
	 * adds a new item to this itemList on "top" of the others. The index
	 * assigned to this new item is the current size of the list, no check is
	 * done on the existing indexes.
	 * 
	 * @param item
	 * @return true if the new item was successfully added to this item list.
	 */
	public boolean add(T item) {

		return items.add(new Pair(items.size(), item));

	}

	/**
	 * removes the item associated with the index in this item list. \nReturns
	 * false if the index was either not found or the removal was unsuccessful.
	 * 
	 * @param idx
	 * @return true if the item was successfully removed from this item list
	 */
	public boolean removeOfIndex(int idx) {

		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				return items.remove(pair);
			}

		}

		return false;
	}

	/**
	 * removes the item from this item list. <br/>
	 * Returns false if the item was either not found or the removal was
	 * unsuccessful.
	 * 
	 * @param item
	 * @return true if the item was successfully removed from this item list
	 */
	public boolean removeOfitem(T item) {

		for (Pair pair : items) {
			if (pair.getItem().equals(item)) {
				return items.remove(pair);
			}

		}
		return false;
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

		if (items.add(new Pair(i, other))) {
			removeOfIndex(i);
			return true;
		}
		return false;
	}

	/**
	 * utility class that represents an index-item association.
	 * 
	 * @author bmo
	 *
	 */
	public class Pair implements Serializable {

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
		public int hashCode() {

			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + idx;
			result = prime * result + ((item == null) ? 0 : item.hashCode());
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {

			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (idx != other.idx)
				return false;
			if (item == null) {
				if (other.item != null)
					return false;
			} else if (!item.equals(other.item))
				return false;
			return true;
		}

		private Pile<T> getOuterType() {

			return Pile.this;
		}

		@Override
		public String toString() {

			return "Pair [idx=" + idx + ", item=" + item + "]";
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

	public List<Pair> getItems() {

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
	public List<T> getAllItems() {

		List<T> list = new ArrayList<T>();
		list.addAll(items.stream().map(Pair::getItem).collect(Collectors.toList()));
		return list;
	}

	@Override
	public String toString() {

		return "Pile [items=" + items + "]";
	}

	/**
	 * deletes the item from this pile.</br>
	 * performs removeOfItem and then dercrement indexes if necessary.
	 * 
	 * @param item
	 * @return true if the item was successfully deleted from this pile.
	 */
	public boolean deleteOfitem(T item) {

		int idx = getIdx(item);

		if (removeOfitem(item)) {

			for (Pair pair : items) {
				if (pair.getIdx() > idx) {
					pair.decr();
				}
			}

		}

		return false;

	}

}