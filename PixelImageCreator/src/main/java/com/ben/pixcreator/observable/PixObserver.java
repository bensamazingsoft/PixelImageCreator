
package com.ben.pixcreator.observable;

public interface PixObserver
{

      public void update();


      public void setObservable(PixObservable obs);


      public PixObservable getObservable();

}
