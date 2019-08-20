
package com.ben.pixcreator.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ben.pixcreator.server.data.model.User;

public class Main
{

      public static void main(String[] args)
      {

	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PIXELCREATOR");

	    EntityManager em = emf.createEntityManager();

	    EntityTransaction tr = em.getTransaction();

	    tr.begin();

	    User user = new User("abc@def.com", "passw");
	    em.persist(user);

	    tr.commit();

      }

}
