
package com.ben.pixcreator.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ben.pixcreator.application.image.layer.ILayer;

public class LayerList extends HashMap<ILayer, Boolean>
{

      private static final long	   serialVersionUID = 1L;

      private Map<Integer, ILayer> idx;


      public LayerList()
      {

	    super();
	    idx = new TreeMap<>();
      }


      public LayerList(List<ILayer> gridLayers)
      {

	    // TODO Auto-generated constructor stub
      }


      public void moveUp(ILayer layer)
      {

	    // TODO move the item one slot up in the list
      }


      public void moveDown(ILayer layer)
      {

	    // TODO move the item one slot down in the list
      }


      public void insertOver(ILayer layer)
      {

	    // TODO insert an item over a target item in the list
      }


      public void insertUnder(ILayer layer)
      {

	    // TODO insert an item under a target item in the list
      }


      public void toogle(ILayer layer)
      {

	    // TODO toggle layer boolean value
      }


      public Map<Integer, ILayer> getIdx()
      {

	    return idx;
      }


      public void setIdx(Map<Integer, ILayer> idx)
      {

	    this.idx = idx;
      }


      @Override
      public Boolean put(ILayer arg0, Boolean arg1)
      {

	    // TODO Auto-generated method stub
	    return super.put(arg0, arg1);
      }


      @Override
      public void putAll(Map<? extends ILayer, ? extends Boolean> arg0)
      {

	    // TODO Auto-generated method stub
	    super.putAll(arg0);
      }


      @Override
      public boolean remove(Object arg0, Object arg1)
      {

	    // TODO Auto-generated method stub
	    return super.remove(arg0, arg1);
      }


      @Override
      public Boolean remove(Object arg0)
      {

	    // TODO Auto-generated method stub
	    return super.remove(arg0);
      }


      public ILayer getLayerById(int i)
      {

	    // TODO Auto-generated method stub
	    return null;
      }

}