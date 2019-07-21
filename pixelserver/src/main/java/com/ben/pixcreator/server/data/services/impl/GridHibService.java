
package com.ben.pixcreator.server.data.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ben.pixcreator.server.EntityManagerProvider;
import com.ben.pixcreator.server.data.model.PixelGridDto;
import com.ben.pixcreator.server.data.model.PixelGridDto_;
import com.ben.pixcreator.server.data.services.GridService;
import com.ben.pixcreator.server.exception.DataServiceException;

public class GridHibService implements GridService
{

      @Override
      public PixelGridDto find(int id) throws DataServiceException
      {

	    // TODO Auto-generated method stub
	    return null;
      }


      @Override
      public Boolean persist(PixelGridDto grid) throws DataServiceException
      {

	    EntityManager em = EntityManagerProvider.getInstance().getManager();

	    EntityTransaction tr = em.getTransaction();
	    tr.begin();

	    try
	    {
		  em.persist(grid);

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


      @Override
      public Boolean delete(PixelGridDto grid) throws DataServiceException
      {

	    // TODO Auto-generated method stub
	    return null;
      }


      @Override
      public PixelGridDto update(PixelGridDto grid) throws DataServiceException
      {

	    // TODO Auto-generated method stub
	    return null;
      }


      @Override
      public Set<PixelGridDto> findFiltered(String email, Boolean userOnly, Set<String> filters) throws DataServiceException
      {

	    Set<PixelGridDto> result = new HashSet<>();

	    EntityManager em = EntityManagerProvider.getInstance().getManager();

	    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    CriteriaQuery<PixelGridDto> criteria = criteriaBuilder.createQuery(PixelGridDto.class);
	    Root<PixelGridDto> root = criteria.from(PixelGridDto.class);

	    if (userOnly)
	    {

		  criteria.where(criteriaBuilder.equal(root.get(PixelGridDto_.owner), email));

	    }

	    for (String filter : filters)
	    {

		  criteria.where(criteriaBuilder.isMember(filter, root.get(PixelGridDto_.filters)));

	    }

	    List<PixelGridDto> queryResult;
	    try
	    {
		  queryResult = em.createQuery(criteria).getResultList();
	    }
	    catch (Exception e)
	    {
		  throw new DataServiceException(e.getMessage());
	    }

	    result.addAll(queryResult);

	    return result;
      }

}
