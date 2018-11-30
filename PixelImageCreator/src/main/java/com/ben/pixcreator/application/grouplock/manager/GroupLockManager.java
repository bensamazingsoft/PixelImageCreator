package com.ben.pixcreator.application.grouplock.manager;

import java.util.*;

import com.ben.pixcreator.application.image.layer.ILayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class GroupLockManager {

	
	private GroupLockManager instance;
	
	private Set<Set<ILayer>> groups;
	
	
	private GroupLockManager(){
		
		groups = new HashSet<>();
		
	}
	
	
	
	public Set<ILayer> getGroupLock(ILayer layer){
		
		Set<ILayer> emptyResult = new HashSet<>();
		
for (Set<ILayer> set : groups){
			
			if (set.contains(layer)){
				
				return set;
				
			}
		
}
		
		return emptyResult;
	}
	
	
	public boolean lockToActiveLayer(ILayer layer){
		// lockToActiveLayer
		boolean success = false
				
				for (Set<ILayer> set : groups){
					
					ILayer activeLayer = GuiFacade.getInstance().getActiveLayer();
					
					if (set.contains(activeLayer)){
						
						set.add(layer);
						
					}
				}
				
				
				
				return success;
	}
	
	public boolean unlock(ILayer layer){
		
		boolean success = false
		
		for (Set<ILayer> set : groups){
			
			if (set.contains(layer)){
				set.remove(layer);
				success=true;
			}
			
		}
		return success;
		
		
		
	}
	
	
	
	
	
	public GroupLockManager getInstance(){
		if (instance==null){
			instance = new GroupLockManager();	
		}
		return instance;
	}
	
	
}