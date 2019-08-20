
package com.ben.pixcreator.server;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProvider
{

      private static EntityManagerProvider instance;
      private EntityManager		   em;


      private EntityManagerProvider()
      {

	    em = Persistence.createEntityManagerFactory("PIXELCREATOR").createEntityManager();

      }


      public EntityManager getManager()
      {

	    return em;
      }


      public static EntityManagerProvider getInstance()
      {

	    if (null == instance)
	    {
		  instance = new EntityManagerProvider();
	    }
	    return instance;
      }

}
