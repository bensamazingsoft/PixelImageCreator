
package com.ben.pixcreator.action.singletons;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ASingleton<T>
{

      private static final Logger logger = LoggerFactory.getLogger(ASingleton.class);

      private T			  instance;


      @SuppressWarnings("unchecked")
      public T getInstance() throws SingletonException
      {

	    if (instance == null)
	    {

		  try
		  {
			instance = (T) this.getClass().getConstructors()[0].newInstance();
		  }
		  catch (InstantiationException e)
		  {
			logger.error(e.getMessage(), e);
			throw new SingletonException(this.getClass().toString(), e);
		  }
		  catch (IllegalAccessException e)
		  {
			logger.error(e.getMessage(), e);
			throw new SingletonException(this.getClass().toString(), e);
		  }
		  catch (IllegalArgumentException e)
		  {
			logger.error(e.getMessage(), e);
			throw new SingletonException(this.getClass().toString(), e);
		  }
		  catch (InvocationTargetException e)
		  {
			logger.error(e.getMessage(), e);
			throw new SingletonException(this.getClass().toString(), e);
		  }
		  catch (SecurityException e)
		  {
			logger.error(e.getMessage(), e);
			throw new SingletonException(this.getClass().toString(), e);
		  }
	    }

	    return instance;

      }

}
