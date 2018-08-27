package com.ksh.core.dao;

import java.util.List;
import java.util.Map;

import com.ksh.core.entity.BaseEntity;
import com.ksh.core.entity.Lookup;
import com.ksh.core.exception.KSHException;

public interface BaseDAO {

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, String orderBy);

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, int firstResult, int maxResults);

	public <T extends BaseEntity> T getEntity(Class<T> clazz, Long id);

	public <T extends BaseEntity> T getEntityLookup(Class<T> clazz, Long id, String property);

	public <T extends BaseEntity> T getEntity(Class<T> clazz, String paramName, Object value);

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, String paramName, Object value, String orderBy);

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, Map<String, Object> params, String orderBy)
			throws KSHException ;
	public <T extends BaseEntity> void addEntity(T entity);

	public <T extends BaseEntity> T updateEntity(T entity);

	public <T extends BaseEntity> void removeEntity(T entity);

	public <T extends BaseEntity> void removeEntity(Class<T> clazz, String paramName, Object value)
			throws KSHException;
	public <T extends Object> List<T> getResultList(String namedQueryName, Map<String, Object> params);

	public <T extends Object> T getSingleResultObject(String namedQueryName, Map<String, Object> params);

	public <T extends BaseEntity> Integer executeUpdate(String namedQueryName, Map<String, Object> params);

	public <T extends Object> List<T> executeNativeQuery(String queryString, Map<String, Object> params,
			Class<T> clazz);

	public <T extends BaseEntity> void removeEntity(Class<T> clazz, Long entityId);

	public <T extends BaseEntity> List<T> getEntityAutoCompleteList(Class<T> clazz, String paramName, Object value,
			String orderBy, int firstResult, int maxResults);

	public <T extends BaseEntity> List<Long> getEntityRecidList(Class<T> clazz) throws KSHException;

	public void addLookup(Lookup lookup);

	public Lookup updateLookup(Lookup lookup);

	public Lookup getLookup(Long id) throws KSHException;

	public <T extends BaseEntity> void removeTableEntity(Class<T> clazz, Long entityId) throws KSHException;

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, String paramName, Boolean isNull,
			String orderBy) throws KSHException;

	public <T extends BaseEntity> List<T> getEntityLookupList(Class<T> clazz,String property,Map<String, Object> params, String orderBy) throws KSHException;

	public <T extends BaseEntity> List<T> getEntityConstractList(Class<T> clazz, List<String> properities, Map<String, Object> params,
			String orderBy) throws KSHException;
	
}
