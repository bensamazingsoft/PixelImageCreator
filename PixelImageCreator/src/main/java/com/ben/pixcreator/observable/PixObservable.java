package com.ben.pixcreator.observable;



public interface PixObservable {

	private Set<PixObserver> observers = new HashSet<>();
	private Boolean changed = false;
	
	
	public default void PixObserver addObserver(PixObserver obs){
	
		observers.add(obs);
		
	}
	
	public default void removeObserver(PixObserver obs){
		
		
		if (observers.contains(obs)){
			
			observers.remove(obs);
		}
		
		
		
	}
	
	public default void setChanged(Boolean bool){
		changed = bool;
	}
	
	public default void check(){
		
		if (changed){
			notifyObservers();
			setChanged(false);
		}
		
	}
	
	public default void notifyObservers(){
		
		for (PixObserver obs : observers){
			obs.update();
		}
		
	}
	
	
	
}
