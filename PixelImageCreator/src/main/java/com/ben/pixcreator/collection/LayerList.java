
package com.ben.pixcreator.collection;

import java.util.ArrayList;
import java.util.List;

import com.ben.pixcreator.application.image.layer.ILayer;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

/**
 * Represents the layer stack. Achtung : there is no '0' layer, it starts @ 1
 * 
 * @author bmo
 *
 */
public class LayerList {

	List<Pair> items = new ArrayList<>();

	public LayerList() {

	}

	public Pair getPair(int idx) {
		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				return pair;
			}
		}
		return null;
	}

	public Pair getPair(ALayer layer) {
		for (Pair pair : items) {
			if (pair.getLayer().equals(layer)) {
				return pair;
			}
		}
		return null;
	}

	public ALayer getLayer(int idx) {

		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				return pair.getLayer();
			}
		}
		return null;
	}

	private int getIdx(ILayer layer) {
		for (Pair pair : items) {
			if (pair.getLayer().equals(layer)) {
				return pair.getIdx();
			}
		}
		return 0;
	}

	public void add(ALayer layer) {
		items.add(new Pair(items.size() == 0 ? 1 : items.size() + 1, layer));
	}

	public void remove(int idx) {
		for (Pair pair : items) {
			if (pair.getIdx() == idx) {
				items.remove(pair);
			}
		}
	}

	public void remove(ALayer layer) {
		for (Pair pair : items) {
			if (pair.getLayer().equals(layer)) {
				items.remove(pair);
			}
		}
	}

	public void moveUp(ALayer layer) {

		getPair(getIdx(layer)).decr();
		getPair(layer).incr();

	}

	public void moveDown(ALayer layer) {

		getPair(getIdx(layer)).incr();
		getPair(layer).decr();
	}

	public void insertOver(ALayer layer, ALayer newLayer) {

		int idx = getIdx(layer);
		items.add(new Pair(++idx, newLayer));
		for (Pair pair : items) {
			if (pair.getIdx() > idx) {
				pair.incr();
			}
		}
	}

	public void insertUnder(ALayer layer, ALayer newLayer) {

		int idx = getIdx(layer);
		items.add(new Pair(idx, newLayer));
		for (Pair pair : items) {
			if (pair.getIdx() >= idx) {
				pair.incr();
			}
		}
	}

	private class Pair {

		int		idx;
		ALayer	layer;

		public Pair(int idx, ALayer layer) {
			this.idx = idx;
			this.layer = layer;
		}

		public void incr() {
			idx++;
		}

		public void decr() {
			idx--;
		}

		public int getIdx() {
			return idx;
		}

		public ALayer getLayer() {
			return layer;
		}

	}

	public List<Pair> getItems() {
		return items;
	}

	public void setItems(List<Pair> items) {
		this.items = items;
	}

}