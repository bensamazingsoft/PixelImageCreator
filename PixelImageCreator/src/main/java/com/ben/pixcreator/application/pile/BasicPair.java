package com.ben.pixcreator.application.pile;

import java.io.Serializable;
import java.util.UUID;

/**
 * utility class that encapsulates an index-item association.
 * 
 * @author bmo
 *
 */
public class BasicPair<T> implements Serializable, Pair<T>, Comparable<Pair<T>> {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final UUID			uuid;
	int							idx;
	T							item;

	public BasicPair(int idx, T item) {

		this.idx = idx;
		this.item = item;
		this.uuid = UUID.randomUUID();
	}

	@Override
	public void incr() {

		idx++;
	}

	@Override
	public void decr() {

		idx--;
	}

	@Override
	public int getIdx() {

		return idx;
	}

	@Override
	public T getItem() {

		return item;
	}

	@Override
	public String toString() {

		return "BasicPair [idx=" + idx + ", item=" + item + "]";
	}

	@Override
	public int compareTo(Pair<T> o) {

		Pair<T> pair = (BasicPair<T>) o;
		return this.idx - pair.getIdx();
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		@SuppressWarnings("unchecked")
		BasicPair<T> other = (BasicPair<T>) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	protected void setItem(T item) {

		this.item = item;
	}

}