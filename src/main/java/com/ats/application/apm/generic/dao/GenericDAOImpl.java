package com.ats.application.apm.generic.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ats.application.apm.exceptions.GenericDAOException;

@Repository
public abstract class GenericDAOImpl<E, K extends Serializable> implements InterGenericDAO<E, K> {

	private static final Logger logger = Logger.getLogger(GenericDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public GenericDAOImpl() {
	}

	@Override
	public E save(E entity) throws GenericDAOException {
		logger.info("Saving to database");
		return (E) getSession().save(entity);
	}

	@Override
	public boolean update(E entity) throws GenericDAOException {
		logger.info("Updating to database");
		getSession().update(entity);
		return true;
	}

	@Override
	public boolean delete(E entity) throws GenericDAOException {
		logger.info("Deleting from database");
		getSession().delete(entity);
		return true;
	}

	@Override
	public E findByID(E entity, K id, String strParam) throws GenericDAOException {
		logger.info("Searching in database");
		Criteria ctr = getSession().createCriteria(entity.getClass());
		ctr.add(Restrictions.eq(strParam, id));
		return (E) ctr.uniqueResult();
	}

	@Override
	public List<E> findAll(E entity) throws GenericDAOException {
		logger.info("Finding from database");
		Criteria ctr = getSession().createCriteria(entity.getClass());
		return ctr.list();
	}

	@Override
	public List<E> findByCriteria(E entity, Integer iFirst, Integer iMax, String strOrderProperty, Boolean blAscDesc)
			throws GenericDAOException {
		logger.info("Finding from database based on criteria");
		Criteria ctr = getSession().createCriteria(entity.getClass());
		iFirst = (iFirst == null) ? 0 : iFirst;
		iMax = (iMax == null) ? 25 : iMax;
		ctr.setFirstResult(iFirst);
		ctr.setMaxResults(iMax);
		if (!StringUtils.isEmpty(strOrderProperty)) {
			if (blAscDesc != null && blAscDesc == false) {
				ctr.addOrder(Order.desc(strOrderProperty));
			} else {
				ctr.addOrder(Order.asc(strOrderProperty));
			}
		}
		return ctr.list();
	}

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
