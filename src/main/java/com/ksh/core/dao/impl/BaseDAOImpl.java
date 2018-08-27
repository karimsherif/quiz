package com.ksh.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.inject.Singleton;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.ksh.core.BaseObject;
import com.ksh.core.dao.BaseDAO;
import com.ksh.core.entity.BaseEntity;
import com.ksh.core.entity.Lookup;
import com.ksh.core.entity.LookupType;
import com.ksh.core.exception.KSHException;
import com.ksh.core.util.Utils;

//@Singleton
public class BaseDAOImpl extends BaseObject implements BaseDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;


	public BaseDAOImpl() {

	}


	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, String orderBy) throws KSHException {
		try {
			StringBuffer queryString = new StringBuffer("select o from ").append(clazz.getSimpleName()).append(" o ");
			if (Utils.isNotEmpty(orderBy)) {
				queryString.append(" order by o.").append(orderBy);
			}
			Query query = entityManager.createQuery(queryString.toString());
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> List<Long> getEntityRecidList(Class<T> clazz) throws KSHException {
		try {
			StringBuffer queryString = new StringBuffer("select o.recid from ").append(clazz.getSimpleName())
					.append(" o ");
			Query query = entityManager.createQuery(queryString.toString());
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, int firstResult, int maxResults)
			throws KSHException {
		try {
			Query query = entityManager.createQuery("select o from " + clazz.getSimpleName() + " o ");
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends Object> List<T> getResultList(CriteriaQuery<T> criteriaQuery) throws KSHException {
		List<T> results = null;
		try {
			results = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (KSHException e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return results;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		try {
			return entityManager.getCriteriaBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T extends BaseEntity> T getEntity(Class<T> clazz, Long id) throws KSHException {
		try {
			return (T) entityManager.find(Class.forName(clazz.getName()), id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public Lookup getLookup(Long id) throws KSHException {
		try {
			return entityManager.find(Lookup.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, String paramName, Object value, String orderBy)
			throws KSHException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("paramName", value);
			StringBuffer queryString = new StringBuffer("select o from ").append(clazz.getSimpleName())
					.append(" o where o.").append(paramName).append("=:").append("paramName");
			if (Utils.isNotEmpty(orderBy)) {
				queryString.append(" order by o.").append(orderBy);
			}
			return this.executeQuery(queryString.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, String paramName, Boolean isNull,
			String orderBy) throws KSHException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("paramName", value);
			StringBuffer queryString = new StringBuffer("select o from ").append(clazz.getSimpleName()).append(" o ");
			if (isNull != null && Boolean.FALSE.equals(isNull))
				queryString.append(" where o.").append(paramName).append(" is not null ");

			if (isNull != null && Boolean.TRUE.equals(isNull))
				queryString.append(" where o.").append(paramName).append(" is  null ");
			if (Utils.isNotEmpty(orderBy)) {
				queryString.append(" order by o.").append(orderBy);
			}
			return this.executeQuery(queryString.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> List<T> getEntityList(Class<T> clazz, Map<String, Object> params, String orderBy)
			throws KSHException {
		try {
			StringBuffer queryString = new StringBuffer("select o from  ").append(clazz.getSimpleName()).append(" o ");
			// .append(" o where
			// o.").append(paramName).append("=:").append("paramName")
			if (params != null) {
				Set<String> keys = params.keySet();
				Iterator<String> keysIterator = keys.iterator();
				String key = null;
				queryString.append(" where ");
				int paramCounter = 0;
				while (keysIterator.hasNext()) {
					key = keysIterator.next();
					if (paramCounter >= 1) {
						queryString.append(" and ");
					}
					queryString.append("o.").append(key).append(" ").append("=:").append(key);
					paramCounter++;
				}
			}

			if (Utils.isNotEmpty(orderBy)) {
				queryString.append(" order by o.").append(orderBy);
			}
			return this.executeQuery(queryString.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> List<T> getEntityAutoCompleteList(Class<T> clazz, String paramName, Object value,
			String orderBy, int firstResult, int maxResults) throws KSHException {
		try {

			StringBuffer queryString = new StringBuffer("select new ").append(clazz.getSimpleName())
					.append("(o.recid,o.").append(paramName).append(") from ").append(clazz.getSimpleName())
					.append(" o where o.").append(paramName).append(" like :").append("paramName");
			if (Utils.isNotEmpty(orderBy)) {
				queryString.append(" order by o.").append(orderBy);
			}
			Query query = entityManager.createQuery(queryString.toString()).setParameter("paramName",
					"%" + value + "%");
			if (firstResult != -1 && maxResults != -1) {
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
			}
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> T getEntity(Class<T> clazz, String paramName, Object value) throws KSHException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("paramName", value);
			return (T) this.executeQueryWithSingleResult(
					new StringBuffer("select o from ").append(clazz.getSimpleName()).append(" o where o.")
							.append(paramName).append("=:").append("paramName").toString(),
					params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> T getEntity(Class<T> clazz, Map<String, Object> params) throws KSHException {
		try {
			// Map<String, Object> params = new HashMap<String, Object>();
			// params.put("paramName", value);
			return (T) this.executeQueryWithSingleResult(
					new StringBuffer("select o from o").append(clazz.getSimpleName()).append(" o ").toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> void addEntity(T entity) throws KSHException {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> T updateEntity(T entity) throws KSHException {
		try {
			T oldEntity = (T) getEntity(entity.getClass(), entity.getRecid());

			return entityManager.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> void removeEntity(T entity) throws KSHException {
		this.removeEntity(entity.getClass(), entity.getRecid());

	}

	public <T extends BaseEntity> void removeEntity(Class<T> clazz, Long entityId) throws KSHException {
		try {

			this.executeUpdateQuery(new StringBuffer("delete from ").append(clazz.getSimpleName())
					.append(" o where o.recid=").append(entityId).toString(), null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> void removeEntity(Class<T> clazz, String paramName, Object value)
			throws KSHException {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("paramName", value);

			this.executeUpdateQuery(new StringBuffer("delete from ").append(clazz.getSimpleName()).append(" o where o.")
					.append(paramName).append("=:").append("paramName").toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends Object> List<T> getResultList(String namedQueryName, Map<String, Object> params)
			throws KSHException {
		List<T> results = null;
		try {
			results = (List<T>) executeNamedQuery(namedQueryName, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return results;
	}

	public <T extends Object> T getSingleResultObject(String namedQueryName, Map<String, Object> params)
			throws KSHException {
		T result = null;
		try {
			result = (T) executeNamedQueryWithSingleResult(namedQueryName, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return result;
	}

	public <T extends BaseEntity> Integer executeUpdate(String namedQueryName, Map<String, Object> params)
			throws KSHException {
		Integer result = null;
		try {
			// namedQueryFootPrint(namedQueryName, params);
			Query query = entityManager.createNamedQuery(namedQueryName);
			if (params != null) {
				addPrametersToQuery(query, params);
			}
			result = query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return result;
	}

	public <T extends BaseEntity> Integer executeUpdateQuery(String queryString, Map<String, Object> params)
			throws KSHException {
		Integer result = null;
		try {
			Query query = entityManager.createQuery(queryString);
			if (params != null) {
				addPrametersToQuery(query, params);
			}
			result = query.executeUpdate();
			// namedQueryFootPrint(queryString, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return result;
	}

	public <T extends Object> List<T> executeNativeQuery(String queryString, Map<String, Object> params, Class<T> clazz)
			throws KSHException {
		List<T> result = null;
		try {
			Query query = null;
			if (clazz != null) {
				query = entityManager.createNativeQuery(queryString, clazz);
			} else {
				query = entityManager.createNativeQuery(queryString);
			}
			if (params != null) {
				addPrametersToQuery(query, params);
			}
			result = (List<T>) query.getResultList();
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return result;
	}

	private <T extends Object> List<T> executeQuery(String queryString, Map<String, Object> queryParams)
			throws KSHException {
		List<T> results = null;
		try {
			Query query = entityManager.createQuery(queryString);
			if (queryParams != null) {
				addPrametersToQuery(query, queryParams);
			}
			results = query.getResultList();
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return results;
	}

	private <T extends Object> T executeQueryWithSingleResult(String queryString, Map<String, Object> queryParams)
			throws KSHException {
		T result = null;
		try {
			Query query = entityManager.createQuery(queryString);
			if (queryParams != null) {
				addPrametersToQuery(query, queryParams);
			}
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return result;
	}

	private <T extends Object> List<T> executeNamedQuery(String namedQueryName, Map<String, Object> queryParams)
			throws KSHException {
		List<T> results = null;
		try {
			Query query = entityManager.createNamedQuery(namedQueryName);
			if (queryParams != null) {
				addPrametersToQuery(query, queryParams);
			}
			results = query.getResultList();
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return results;
	}

	private <T extends Object> T executeNamedQueryWithSingleResult(String namedQueryName,
			Map<String, Object> queryParams) throws KSHException {
		T result = null;
		try {
			Query query = entityManager.createNamedQuery(namedQueryName);
			if (queryParams != null) {
				addPrametersToQuery(query, queryParams);
			}
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return result;
	}

	private Query addPrametersToQuery(Query query, Map<String, Object> queryParams) {
		if (queryParams != null) {
			Set<String> keys = queryParams.keySet();
			Iterator<String> keysIterator = keys.iterator();
			String key = null;
			while (keysIterator.hasNext()) {
				key = keysIterator.next();
				query.setParameter(key, queryParams.get(key));
			}
		}
		return query;
	}

	public <T extends Object> T getSingleResultObject(CriteriaQuery<T> criteriaQuery) throws KSHException {
		T result = null;
		try {
			Query query = this.createQuery(criteriaQuery);
			result = (T) query.getSingleResult();
		} catch (NoResultException e) {
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
		return result;
	}

	public <T extends Object> Query createQuery(CriteriaQuery<T> criteriaQuery) {
		return entityManager.createQuery(criteriaQuery);
	}

	private Boolean hasTransientAnnotation(Field field) {
		Transient trans = field.getAnnotation(Transient.class);
		if (trans != null)
			return true;

		return false;
	}

	private <T extends BaseEntity> Method getMethod(T entity, String methodName)
			throws SecurityException, NoSuchMethodException {

		Method method = null;
		Class entityClass = entity.getClass();

		method = entity.getClass().getMethod(methodName, null);

		return method;
	}

	private String getExpectedMethodName(Field field) {

		String fieldName = field.getName();
		char ch = fieldName.charAt(0);
		char[] nameAsChar = fieldName.toCharArray();

		String methodPrefix = "get";
		nameAsChar[0] = Character.toUpperCase(ch);
		fieldName = new String(nameAsChar);

		String name = field.getType().getSimpleName();
		// System.out.println(name);
		if (field.getType().getSimpleName().equals("boolean"))
			methodPrefix = "is";

		String methodName = methodPrefix + fieldName;

		return methodName;
	}

	private <T extends BaseEntity> String getMethodVal(Field field, T entity) {

		String val = null;
		String methodName = getExpectedMethodName(field);
		try {
			// Method method = getMethod(entity, methodName);
			field.setAccessible(true);
			Object result = field.get(entity); // method.invoke(entity, null);
			if (result instanceof BaseEntity) {
				result = ((BaseEntity) result);
				if (result != null && !isLazy(field))
					val = ((BaseEntity) result).getRecid().toString();
			} else if (result instanceof Date) {
				Date tempDate = (Date) result;
				val = tempDate.getDay() + "-" + tempDate.getMonth() + "-" + tempDate.getYear();
			} else if (result != null) {
				val = result.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (val == null)
			return "";
		else
			return val;
	}

	private Boolean isLazy(Field field) {
		ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
		if (manyToOne != null && manyToOne.fetch() == FetchType.LAZY)
			return true;

		return false;
	}

	private String getColumnName(Field field) {
		Column col = field.getAnnotation(Column.class);
		if (col != null && col.name() != null && !col.name().isEmpty())
			return col.name();

		return field.getName();
	}

	private String getTableName(Class entityClass) {
		Table table = (Table) entityClass.getAnnotation(Table.class);
		return table.name();
	}

	/**
	 * This method used to parsing hql statement to get fields from it and
	 * parameterId for each field
	 * 
	 * @param baseEntity
	 *            base entity to get field from it
	 * @param updateFields
	 *            String that parsing it and must has syntax like(SET
	 *            alias.entityfield=:val)
	 * @return map
	 * @throws Exception
	 *             if any run time Exception occur
	 */
	private Map<Field, String> getNamedQueryFields(BaseEntity baseEntity, String updateFields) throws Exception {

		Map<Field, String> map = new HashMap<Field, String>();
		String[] fields = updateFields.split(",");
		String fieldName = null;
		Field field = null;
		String parameterName;
		for (String s : fields) {
			fieldName = s.split("=")[0];
			int len = fieldName.split("\\.").length;
			fieldName = len <= 1 ? fieldName.split("\\.")[0] : fieldName.split("\\.")[1];
			try {
				field = baseEntity.getClass().getDeclaredField(fieldName);
				parameterName = fieldName = s.split("=")[1];
				parameterName = parameterName.replace(":", "");
				map.put(field, parameterName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * This Method used to get entity simpleName from given query
	 * 
	 * @param query
	 *            query to get entity from it query syntax(Update entity r )
	 * @return empty String if not found or entity name
	 */
	private String entityName(String query) {
		String[] splitString = query.split(" ");
		int flag = 0;
		int cond = 0;
		if (isDeleteHql(query))
			cond = 2;
		if (isUpdateHql(query))
			cond = 1;

		for (String s : splitString) {
			if (!s.isEmpty() && flag == cond)
				return s;
			else if (!s.isEmpty())
				flag++;
		}
		return "";
	}

	private String getUpdatedFieldVal(String keyName, Map<String, Object> params) {
		Object value = params.get(keyName);
		if (value instanceof BaseEntity)
			return ((BaseEntity) value).getRecid().toString();
		else if (value != null)
			return value.toString();
		else
			return keyName;
	}

	private <T extends BaseEntity> List<T> getEntities(String query, T entity, Map<String, Object> params)
			throws KSHException {
		List<T> list = null;
		StringBuilder sb = new StringBuilder();
		String condition = null;
		String[] str = null;
		String updateQuery = null;
		String aliasName = null;
		str = query.split(" WHERE ");
		updateQuery = str[0];
		updateQuery = updateQuery.split(" SET ")[0];
		if (str.length > 1)
			condition = str[1];

		str = updateQuery.split(" * ");
		if (isDeleteHql(query))
			aliasName = str[3];
		else
			aliasName = str[2];

		updateQuery = updateQuery.replaceFirst("UPDATE ", " ");
		sb.append("select ").append(aliasName).append(" from ").append(entity.getClass().getSimpleName()).append("  ")
				.append(aliasName);
		if (condition != null)
			sb.append(" WHERE ").append(condition);

		list = executeQuery(sb.toString(), getRequiredParameter(params, sb.toString()));

		return list;
	}

	/**
	 * This method used to check if hql is updated hql or not
	 * 
	 * @param hql
	 * @return true it's updated hql false if otherwise
	 */
	private Boolean isUpdateHql(String hql) {
		if (hql.contains("UPDATE "))
			return true;

		return false;
	}

	/**
	 * This method used to check if hql is delete hql or not
	 * 
	 * @param hql
	 * @return true it's delete hql false if otherwise
	 */
	private Boolean isDeleteHql(String hql) {
		if (hql.contains("delete "))
			return true;

		return false;
	}

	/**
	 * This method used to get required paramter for select statement from
	 * original param map
	 * 
	 * @param oldParams
	 * @param query
	 * @return
	 */
	private Map<String, Object> getRequiredParameter(Map<String, Object> oldParams, String query) {

		Map<String, Object> result = new HashMap<String, Object>();
		String key = null;
		Set<String> keys = oldParams.keySet();
		Iterator<String> keysIterator = keys.iterator();

		while (keysIterator.hasNext()) {
			key = keysIterator.next();
			if (query.contains(":" + key))
				result.put(key, oldParams.get(key));

		}
		return result;
	}

	// /////////////////// --------- /////////
	public static <T> List<T> map(Class<T> type, List<Object[]> records) {
		List<T> result = new LinkedList<T>();
		for (Object[] record : records) {
			result.add(map(type, record));
		}
		return result;
	}

	public static <T> T map(Class<T> type, Object[] tuple) {
		// Object[] tuple2 = new Object[tuple.length];
		List<Class<?>> tupleTypes = new ArrayList<Class<?>>();

		// for (Object field : tuple) {
		for (int i = 0; i < tuple.length; i++) {
			// if (tuple[i]==null){
			// tupleTypes.add(
			// }
			if (tuple[i].getClass().equals(java.math.BigInteger.class)
					|| tuple[i].getClass().equals(java.lang.Integer.class)) {
				tuple[i] = Long.parseLong(tuple[i] + "");
				// tuple2[i] = Long.parseLong(tuple[i] + "");
				tupleTypes.add(Long.class);
			} else {
				// tuple2[i] = tuple[i];
				tupleTypes.add(tuple[i].getClass());
			}
		}
		try {
			Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
			return ctor.newInstance(tuple);
			// return ctor.newInstance(tuple2);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<LookupType> getLookupTypeListByParentId(Long parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		StringBuffer queryString = new StringBuffer("select lt from LookupType lt where lt.parentId=:parentId ");
		return this.executeQuery(queryString.toString(), params);
	}

	public List<Lookup> getLookupEntityListByLookupTypeId(Long lookupTypeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lookupTypeId", lookupTypeId);
		StringBuffer queryString = new StringBuffer("select l from Lookup l where l.lookupTypeId=:lookupTypeId ");
		return this.executeQuery(queryString.toString(), params);
	}

	@Override
	public <T extends BaseEntity> T getEntityLookup(Class<T> clazz, Long recid, String property) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("recid", recid);

			StringBuffer queryString = new StringBuffer("select new ").append(clazz.getSimpleName())
					.append("(o.recid,o.").append(property).append(") from ").append(clazz.getSimpleName())
					.append(" o where o.recid=:recid");

			return this.executeQueryWithSingleResult(queryString.toString(), params);

		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	@Override
	public <T extends BaseEntity> List<T> getEntityLookupList(Class<T> clazz, String property,
			Map<String, Object> params, String orderBy) throws KSHException {

		try {
			StringBuffer queryString = new StringBuffer("select new ").append(clazz.getSimpleName())
					.append("(o.recid,o.").append(property).append(") from ").append(clazz.getSimpleName())
					.append(" o where 1=1 ");

			if (params != null) {
				Set<String> keys = params.keySet();
				Iterator<String> keysIterator = keys.iterator();
				String key = null;
				while (keysIterator.hasNext()) {
					key = keysIterator.next();
					queryString.append(" and o.").append(key).append(" ").append("=:").append(key);
				}
			}

			if (Utils.isNotEmpty(orderBy)) {
				queryString.append(" order by o.").append(orderBy);
			}
			return this.executeQuery(queryString.toString(), params);

		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	@Override
	public <T extends BaseEntity> List<T> getEntityConstractList(Class<T> clazz, List<String> properties,
			Map<String, Object> params, String orderBy) throws KSHException {

		try {
			StringBuffer queryString = new StringBuffer("select new ").append(clazz.getSimpleName());

			if (Utils.isNotEmpty(properties)) {
				queryString.append("( ");
				Boolean isFirstProperty = Boolean.TRUE;

				for (String property : properties) {
					if (isFirstProperty) {
						queryString.append(" o.").append(property);
						isFirstProperty = Boolean.FALSE;
					} else {
						queryString.append(" , o.").append(property);
					}

				}
				queryString.append(" ) ");
			}
			queryString.append(" from ").append(clazz.getSimpleName()).append(" o ");

			if (params != null) {
				queryString.append(" where 1=1 ");
				Set<String> keys = params.keySet();
				Iterator<String> keysIterator = keys.iterator();
				String key = null;
				while (keysIterator.hasNext()) {
					key = keysIterator.next();
					queryString.append(" and o.").append(key).append(" ").append("=:").append(key);
				}
			}

			if (Utils.isNotEmpty(orderBy)) {
				queryString.append(" order by o.").append(orderBy);
			}
			return this.executeQuery(queryString.toString(), params);

		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	@Override
	public void addLookup(Lookup lookup) {
		try {
			entityManager.persist(lookup);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	@Override
	public Lookup updateLookup(Lookup lookup) {
		try {
			Lookup oldEntity = getLookup(lookup.getRecid());

			return entityManager.merge(lookup);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}

	public <T extends BaseEntity> void removeTableEntity(Class<T> clazz, Long entityId) throws KSHException {
		try {

			this.executeUpdateQuery(new StringBuffer("delete from ").append(clazz.getSimpleName()).toString(), null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KSHException(e);
		}
	}
}
