
package com.ben.pixcreator.application.grouplock.manager;

import java.util.HashSet;
import java.util.Set;

import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class GroupLockManager
{

      private static GroupLockManager instance;

      private Set<Set<ALayer>>	      groups;


      private GroupLockManager()
      {

	    groups = new HashSet<>();

      }


      /**
       * Get a set of locked-layers containing the layer and all layers locked to it;
       * 
       * @param layer
       * @return the lock-group containing the layer or a set containing only the layer.
       */
      public Set<ALayer> getGroupLock(ALayer layer)
      {

	    Set<ALayer> result = new HashSet<>();
	    result.add(layer);

	    for (Set<ALayer> set : groups)
	    {

		  if (set.contains(layer))
		  {

			return set;

		  }

	    }

	    return result;
      }


      /**
       * Lock a layer to the current active layer by adding this layer to all its lock-group
       * 
       * @param layer
       * @return true if the layer was successfully added to at least one lock-group
       */
      public boolean lockToActiveLayer(ALayer layer)
      {

	    // lockToActiveLayer
	    boolean success = false;
	    ALayer activeLayer = GuiFacade.getInstance().getActiveLayer();

	    for (Set<ALayer> set : groups)
	    {

		  if (set.contains(activeLayer))
		  {

			set.add(layer);

		  }
	    }

	    return success;
      }


      /**
       * 
       * removes a layer from all its lock-groups
       * 
       * @param layer
       * @return true if the layer was successfully removed from at least one set
       */
      public boolean unlock(ALayer layer)
      {

	    boolean success = false;

	    for (Set<ALayer> set : groups)
	    {

		  if (set.contains(layer))
		  {
			set.remove(layer);
			success = true;
		  }

	    }
	    return success;

      }


      public static GroupLockManager getInstance()
      {

	    if (instance == null)
	    {
		  instance = new GroupLockManager();
	    }
	    return instance;
      }

}
