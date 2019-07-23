
package com.ben.pixcreator.server.data.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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

	    EntityManager em = EntityManagerProvider.getInstance().getManager();
	    PixelGridDto gridDto = new PixelGridDto();
	    try
	    {
		  gridDto = em.find(PixelGridDto.class, id);
	    }
	    catch (IllegalArgumentException e)
	    {
		  throw new DataServiceException("Illegal argument");
	    }

	    return gridDto;

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
      public void delete(int id) throws DataServiceException
      {

	    EntityManager em = EntityManagerProvider.getInstance().getManager();
	    PixelGridDto gridDto = new PixelGridDto();

	    EntityTransaction tr = em.getTransaction();
	    tr.begin();

	    try
	    {
		  PixelGridDto grid = em.find(PixelGridDto.class, id);
		  if (null != grid)
		  {
			em.remove(grid);
		  }
		  else
		  {
			tr.rollback();
			return;
		  }
	    }
	    catch (IllegalArgumentException e)
	    {
		  tr.rollback();
		  throw new DataServiceException("Illegal argument");
	    }

	    tr.commit();

      }


      @Override
      public PixelGridDto update(PixelGridDto grid) throws DataServiceException
      {

	    EntityManager em = EntityManagerProvider.getInstance().getManager();
	    EntityTransaction tr = em.getTransaction();

	    tr.begin();

	    try
	    {
		  em.merge(grid);

	    }
	    catch (IllegalArgumentException e)
	    {
		  tr.rollback();
		  throw new DataServiceException("Illegal argument");

	    }

	    tr.commit();

	    return grid;
      }


      @Override
      public Set<PixelGridDto> findFiltered(String email, Boolean userOnly, Set<String> filters) throws DataServiceException
      {

	    Set<PixelGridDto> result = new HashSet<>();

	    EntityManager em = EntityManagerProvider.getInstance().getManager();

	    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    CriteriaQuery<PixelGridDto> criteria = criteriaBuilder.createQuery(PixelGridDto.class);
	    Root<PixelGridDto> root = criteria.from(PixelGridDto.class);

	    Set<Predicate> predicates = new HashSet<>();

	    if (!filters.isEmpty())
	    {
		  Set<Predicate> filterPredicates = new HashSet<>();
		  for (String filter : filters)
		  {

			filterPredicates.add(criteriaBuilder.isMember(filter, root.get(PixelGridDto_.filters)));

		  }

		  predicates.add(criteriaBuilder.or(filterPredicates.toArray(new Predicate[filterPredicates.size()])));
	    }

	    if (userOnly)
	    {
		  Predicate userPredicate = criteriaBuilder.equal(root.get(PixelGridDto_.owner), email);

		  predicates.add(userPredicate);

	    }

	    criteria.where(predicates.toArray(new Predicate[predicates.size()]));

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
