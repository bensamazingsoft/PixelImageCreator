
package com.ben.pixcreator.server.data.services.impl;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;

import com.ben.pixcreator.server.EntityManagerProvider;
import com.ben.pixcreator.server.data.model.User;
import com.ben.pixcreator.server.data.services.UserService;
import com.ben.pixcreator.server.exception.DataServiceException;

public class UserHibService implements UserService
{

      @Override
      public User findUserByEmail(String email) throws DataServiceException
      {

	    EntityManager em = EntityManagerProvider.getInstance().getManager();

	    User user;
	    try
	    {

		  user = em.createNamedQuery("findUserByEmail", User.class)
			      .setParameter("email", email)
			      .getSingleResult();

	    }
	    catch (NoResultException e)
	    {

		  return null;
	    }
	    catch (NonUniqueResultException e)
	    {

		  throw new DataServiceException("Non unique result");
	    }

	    catch (IllegalStateException e)
	    {

		  throw new DataServiceException("Illegal state");
	    }
	    catch (IllegalArgumentException e)
	    {

		  throw new DataServiceException("Illegal argument");
	    }
	    catch (Exception e)
	    {

		  throw new DataServiceException(e.getMessage());
	    }

	    return user;
      }


      @Override
      public Boolean persist(User user) throws DataServiceException
      {

	    EntityManager em = EntityManagerProvider.getInstance().getManager();

	    if (null != user.getEmail() && null != user.getPassword())
	    {

		  EntityTransaction tr = em.getTransaction();
		  tr.begin();

		  try
		  {
			em.persist(user);

		  }
		  catch (EntityExistsException e)
		  {
			tr.rollback();
			throw new DataServiceException("Entity Already Exists");
		  }
		  catch (IllegalArgumentException e)
		  {
			tr.rollback();
			throw new DataServiceException("Illegal argument");

		  }

		  tr.commit();
		  return true;

	    }

	    return false;
      }


      @Override
      public Boolean delete(User user) throws DataServiceException
      {

	    EntityManager em = EntityManagerProvider.getInstance().getManager();

	    if (null != user.getEmail() && null != user.getPassword())
	    {

		  EntityTransaction tr = em.getTransaction();
		  tr.begin();

		  try
		  {
			em.remove(user);

		  }
		  catch (IllegalArgumentException e)
		  {
			tr.rollback();
			throw new DataServiceException("Illegal argument");

		  }

		  tr.commit();
		  return true;

	    }

	    return false;
      }


      @Override
      public User update(User user) throws DataServiceException
      {

	    EntityManager em = EntityManagerProvider.getInstance().getManager();
	    if (null != user.getEmail() && null != user.getPassword())
	    {
		  User oldUser = findUserByEmail(user.getEmail());

		  if (null != oldUser)
		  {

			oldUser.setPassword(user.getPassword());

			EntityTransaction tr = em.getTransaction();
			tr.begin();

			try
			{
			      em.merge(oldUser);

			}
			catch (IllegalArgumentException e)
			{
			      tr.rollback();
			      throw new DataServiceException("Illegal argument");
			}

			tr.commit();
			return user;

		  }

	    }
	    return null;
      }

}
