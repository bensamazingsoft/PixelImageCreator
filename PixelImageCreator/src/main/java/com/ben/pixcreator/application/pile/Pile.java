package com.ben.pixcreator.application.pile;

import java.util.Set;

public interface Pile<T> {

	/**
	 * adds a new item to this itemList on "top" of the others. The index
	 * assigned to this new item is the current size of the list, no check is
	 * done on the existing indexes.
	 * 
	 * @param item
	 * @return true if the new item was successfully added to this item list.
	 */
	boolean add(T item);

	/**
	 * @param idx
	 * @return the item or null.
	 */
	T getItem(int idx);

	/**
	 * @param item
	 * @return the index of the item or -1 if the item is not present in this
	 *         itemList
	 */
	int getIdx(T item);

	/**
	 * removes the item associated with the index in this item list. \nReturns
	 * false if the index was either not found or the removal was unsuccessful.
	 * 
	 * @param idx
	 * @return true if the item was successfully removed from this item list
	 */
	boolean removeOfIndex(int idx);

	/**
	 * removes the item from this item list. <br/>
	 * Returns false if the item was either not found or the removal was
	 * unsuccessful.
	 * 
	 * @param item
	 * @return true if the item was successfully removed from this item list
	 */
	boolean removeOfItem(T item);

	/**
	 * moves the item one slot up in this item list.
	 * 
	 * @param item
	 * @return the new index of the item after the move or -1 if the item is not
	 *         present in this item list
	 */
	int moveUp(T item);

	/**
	 * moves the item one slot down in this item list or nothing if the item has
	 * the index 0.
	 * 
	 * @param item
	 * @return the new index of the item after the move or -1 if the item is not
	 *         present in this item list or has the index 0
	 */
	int moveDown(T item);

	/**
	 * inserts a new item in this item list right above another given item.<br/>
	 * Indexes are modified accordingly.
	 * 
	 * @param item
	 * @param newitem
	 * @return the index of the new item after the move or -1 if the item is not
	 *         present in this item list
	 */
	int insertOver(T item, T newitem);

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
	int insertUnder(T item, T newitem);

	/**
	 * replaces the item at specified index with supplied replacement.
	 * 
	 * @param i
	 * @param other
	 * @return true if the item was successfully replaced
	 */
	boolean replace(int i, T other);

	Set<BasicPair<T>> getItems();

	boolean isEmpty();

	/**
	 * Convenience method, return a java.util.List holding the effects of the
	 * layer
	 * 
	 * @return java.util.List<T>
	 */
	Set<T> getAllItems();

	int getMaxSize();

	/**
	 * Cant be <0
	 * 
	 * @param maxSize
	 */
	void setMaxSize(int maxSize);

}