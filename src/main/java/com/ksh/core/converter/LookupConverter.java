package com.ksh.core.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.ksh.core.entity.Lookup;
import com.ksh.core.util.CDIApplicationContext;
import com.ksh.core.util.Util;

public class LookupConverter extends BaseConverter {


	private static final long serialVersionUID = 5902189531709500204L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Lookup lookup = null;
		if (value == null || (value.trim().length() == 0) || !Util.isNumeric(value)) {
			return lookup;
		}
		try {
//			NICManagement nicManagement = (NICManagement) CDIApplicationContext.getBean(NICManagement.class);
//			lookup = nicManagement.getLookupEntity(Lookup.class, Long.parseLong(value.trim()));
			 lookup = new Lookup(Long.parseLong(value.trim()));
		} catch (NumberFormatException e) {
//			logger.error("" + e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lookup;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof Lookup) {
			return Long.toString(((Lookup) value).getRecid());
		} else
			return null;
	}
}
