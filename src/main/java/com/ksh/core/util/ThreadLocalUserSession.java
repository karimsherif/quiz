package com.ksh.core.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUserSession {


	@SuppressWarnings("unchecked")
	private static final ThreadLocal<Map> userSessionHolder = new NamedThreadLocal<Map>("ThreadLocal User Session");

	@SuppressWarnings("unchecked")
	protected static Map userSessionHolderMap() {
		return (Map) userSessionHolder.get();
	}

	@SuppressWarnings("unchecked")
	public static void set(Object key, Object value) {
		Map sessionMap = userSessionHolderMap();
		if (sessionMap == null) {
			sessionMap = new HashMap();
			userSessionHolder.set(sessionMap);
		}
		sessionMap.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public static Object remove(Object key, boolean releaseMapIfEmpty) {
		Map sessionMap = userSessionHolderMap();
		Object value = null;
		if (sessionMap != null) {
			value = sessionMap.remove(key);
			if (releaseMapIfEmpty && sessionMap.isEmpty()) {
				userSessionHolder.set(null);
				userSessionHolder.remove();
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public static Object get(Object key) {
		Map sessionMap = userSessionHolderMap();
		Object value = null;
		if (sessionMap != null) {
			value = sessionMap.get(key);
		}
		return value;
	}
}
