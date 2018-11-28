package com.ben.pixcreator.observable;

public interface PixObserver {

	private PixObservable observable;
	
	public void update();
	
	
	public default void setObservable(PixObservable obs){
		
		observable=obs;
		
	}
	
	public default PixObservable getObservable(){
		return observable;
	}
	
	
}
