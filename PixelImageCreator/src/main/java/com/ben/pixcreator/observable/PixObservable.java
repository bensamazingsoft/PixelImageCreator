
package com.ben.pixcreator.observable;

public interface PixObservable
{

      public void addObserver(PixObserver obs);


      public void removeObserver(PixObserver obs);


      public void setChanged(boolean bool);


      public void check();


      public void notifyObservers();

}
