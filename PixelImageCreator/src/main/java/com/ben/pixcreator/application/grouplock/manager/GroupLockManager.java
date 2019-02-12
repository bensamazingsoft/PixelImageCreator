
package com.ben.pixcreator.application.grouplock.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class GroupLockManager {

	private static GroupLockManager instance;

	private Map<ALayer, Set<ALayer>> groups;

	private GroupLockManager() {

		groups = new HashMap<>();

	}

	/**
	 * Get the set of layers locked to the layer
	 * 
	 * @param layer
	 * @return a set of layers locked to the layer
	 */
	public Set<ALayer> getGroupLock(ALayer layer) {

		return groups.computeIfAbsent(layer, key -> new HashSet<ALayer>());

	}

	/**
	 * Lock a layer to the current active layer
	 * 
	 * @param layer
	 * @return true if the layer was successfully added to the active layer set
	 *         of locked layers
	 */
	public boolean lockToActiveLayer(ALayer layer) {

		// lockToActiveLayer
		boolean success = false;

		Set<ALayer> set = groups.computeIfAbsent(GuiFacade.getInstance().getActiveLayer(),
				key -> new HashSet<ALayer>());

		return set.add(layer);
	}

	/**
	 * 
	 * removes a layer from the active layer lock-group
	 * 
	 * @param layer
	 * @return true if the layer was successfully removed from the set
	 */
	public boolean unlock(ALayer layer) {

		boolean success = false;

		Set<ALayer> set = groups.computeIfAbsent(GuiFacade.getInstance().getActiveLayer(),
				key -> new HashSet<ALayer>());

		if (set.contains(layer)) {
			set.remove(layer);
			success = true;

		}
		return success;

	}

	public static GroupLockManager getInstance() {

		if (instance == null) {
			instance = new GroupLockManager();
		}
		return instance;
	}

}
