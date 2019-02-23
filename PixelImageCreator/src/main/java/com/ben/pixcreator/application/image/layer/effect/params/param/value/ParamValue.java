
package com.ben.pixcreator.application.image.layer.effect.params.param.value;

public class ParamValue<T>
{

      protected final T	min;

      protected final T	max;
      protected final T	bypass;

      protected T	value;


      public ParamValue(T min, T max, T bypass)
      {

	    super();
	    this.min = min;
	    this.max = max;
	    this.bypass = bypass;
      }


      public ParamValue(T value)
      {

	    min = max = bypass = null;
	    this.value = value;
      }


      public T getValue()
      {

	    return value;
      }


      public void setValue(T value)
      {

	    this.value = value;
      }


      public T getMin()
      {

	    return min;
      }


      public T getMax()
      {

	    return max;
      }


      public T getBypass()
      {

	    return bypass;
      }

}
