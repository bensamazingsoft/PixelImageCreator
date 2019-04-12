
package com.ben.pixcreator.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;

/**
 * Represents the layer stack with an Array of index-layer pairs.
 * 
 * @author bmo
 *
 */
public class LayerList implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	List<Pair>					items				= new ArrayList<>();

	public LayerList() {

	}

	/**
	 * @param idx
	 * @return the index of the layer or null.
	 */
	public ALayer getLayer(int idx) {

		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				return pair.getLayer();
			}
		}
		return null;
	}

	/**
	 * @param layer
	 * @return the index of the layer or -1 if the layer is not present in this
	 *         LayerList
	 */
	public int getIdx(ALayer layer) {

		for (Pair pair : items) {
			if (pair.getLayer().equals(layer)) {
				return pair.getIdx();
			}
		}
		return -1;
	}

	/**
	 * adds a new layer to this LayerList on "top" of the others. The index
	 * assigned to this new layer is the current size of the list, no check is
	 * done on the existing indexes.
	 * 
	 * @param layer
	 * @return true if the new layer was successfully added to this layer list.
	 */
	public boolean add(ALayer layer) {

		return items.add(new Pair(items.size(), layer));

	}

	/**
	 * removes the layer associated with the index in this layer list. \nReturns
	 * false if the index was either not found or the removal was unsuccessful.
	 * 
	 * @param idx
	 * @return true if the layer was successfully removed from this layer list
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
	 * removes the layer from this layer list. <br/>
	 * Returns false if the layer was either not found or the removal was
	 * unsuccessful.
	 * 
	 * @param layer
	 * @return true if the layer was successfully removed from this layer list
	 */
	public boolean removeOfLayer(ALayer layer) {

		for (Pair pair : items) {
			if (pair.getLayer().equals(layer)) {
				return items.remove(pair);
			}
		}
		return false;
	}

	/**
	 * moves the layer one slot up in this layer list.
	 * 
	 * @param layer
	 * @return the new index of the layer after the move or -1 if the layer is
	 *         not present in this layer list
	 */
	public int moveUp(ALayer layer) {

		if (getIdx(layer) > -1) {
			getPair(getIdx(layer)).decr();
			getPair(layer).incr();

			return getIdx(layer);

		}
		return -1;
	}

	/**
	 * moves the layer one slot down in this layer list or nothing if the layer
	 * has the index 0.
	 * 
	 * @param layer
	 * @return the new index of the layer after the move or -1 if the layer is
	 *         not present in this layer list or has the index 0
	 */
	public int moveDown(ALayer layer) {

		if (getIdx(layer) > 0) {
			getPair(getIdx(layer)).incr();
			getPair(layer).decr();
		}
		return -1;
	}

	/**
	 * inserts a new layer in this layer list right above another given
	 * layer.<br/>
	 * Indexes are modified accordingly.
	 * 
	 * @param layer
	 * @param newLayer
	 * @return the index of the new layer after the move or -1 if the layer is
	 *         not present in this layer list
	 */
	public int insertOver(ALayer layer, ALayer newLayer) {

		int idx = getIdx(layer);
		if (idx > -1) {
			items.add(new Pair(++idx, newLayer));
			for (Pair pair : items) {
				if (pair.getIdx() > idx) {
					pair.incr();
				}
			}
			return getIdx(newLayer);
		}
		return -1;
	}

	/**
	 * * inserts a new layer in this layer list right under another given
	 * layer.<br/>
	 * Indexes are modified accordingly.
	 * 
	 * @param layer
	 * @param newLayer
	 * @return the index of the new layer after the move or -1 if the layer is
	 *         not present in this layer list
	 */
	public int insertUnder(ALayer layer, ALayer newLayer) {

		int idx = getIdx(layer);
		if (idx > -1) {
			items.add(new Pair(idx, newLayer));
			for (Pair pair : items) {
				if (pair.getIdx() >= idx) {
					pair.incr();
				}
			}
			return getIdx(newLayer);
		}
		return -1;
	}

	/**
	 * utility class that represents an index-layer association.
	 * 
	 * @author bmo
	 *
	 */
	private class Pair implements Serializable {

		/**
		 * 
		 */
		private static final long	serialVersionUID	= 1L;
		int							idx;
		ALayer						layer;

		public Pair(int idx, ALayer layer) {

			this.idx = idx;
			this.layer = layer;
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

		public ALayer getLayer() {

			return layer;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + idx;
			result = prime * result + ((layer == null) ? 0 : layer.hashCode());
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
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (idx != other.idx)
				return false;
			if (layer == null) {
				if (other.layer != null)
					return false;
			} else if (!layer.equals(other.layer))
				return false;
			return true;
		}

		private LayerList getOuterType() {
			return LayerList.this;
		}

	}

	private Pair getPair(int idx) {

		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				return pair;
			}
		}
		return null;
	}

	private Pair getPair(ALayer layer) {

		for (Pair pair : items) {
			if (pair.getLayer().equals(layer)) {
				return pair;
			}
		}
		return null;
	}

	public List<Pair> getItems() {

		return items;
	}

	public List<ALayer> getAllLayers() {

		return items.stream().map(Pair::getLayer).collect(Collectors.toList());
	}

}