package com.ats.application.apm.generic.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ats.application.apm.exceptions.GenericDAOException;

public interface InterGenericDAO<E, K extends Serializable> {

	/**
	 * This method will used to save the object into database
	 * @param entity object which will save
	 * @return created entity
	 * @throws GenericDAOException throws Exception
	 */
	E save(E entity) throws GenericDAOException;
	
	/**
	 * This method will update
	 * @param entity
	 * @return
	 * @throws GenericDAOException
	 */
	boolean update(E entity) throws GenericDAOException;
	/**
	 * This will delete the entity from database
	 * @param entity will delete the entity
	 * @return True will be returned, any failure will return false
	 * @throws GenericDAOException
	 */
	boolean delete(E entity) throws GenericDAOException;
	/**
	 * 
	 * @param entity type of entity
	 * @param id primary id
	 * @return
	 * @throws GenericDAOException
	 */
	E findByID(E entity, K id, String strParam) throws GenericDAOException;
	/**
	 * Find all by entity
	 * @param entity
	 * @return
	 * @throws GenericDAOException
	 */
	List<E> findAll(E entity) throws GenericDAOException;
	/**
	 * List all entity based on criteria
	 * @param entity
	 * @param iFirst
	 * @param iMax
	 * @param strOrderProperty
	 * @param blAscDesc	Ascending TRUE/Descending FALSE
	 * @return
	 * @throws GenericDAOException
	 */
	List<E> findByCriteria(E entity, Integer iFirst, Integer iMax, String strOrderProperty, Boolean blAscDesc) throws GenericDAOException;
}
