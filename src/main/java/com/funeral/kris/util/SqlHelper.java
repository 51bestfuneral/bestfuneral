package com.funeral.kris.util;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;


public class SqlHelper {

	@SuppressWarnings("rawtypes")
	public static String getSqlFromRequest(String cName, HttpServletRequest request) throws Exception{
		String completeSql = "select q from "+ cName +" q where 1= 1";
		Class c = Class.forName(PRE_NAME+cName);
		Field f[]=c.getDeclaredFields();
		for(int i=0;i<f.length;i++) {
			String fieldName = f[i].getName();
			if (request.getParameter(fieldName) != null && !request.getParameter(fieldName).equals("")) {
				completeSql = completeSql + "and " + fieldName + "= '" + request.getParameter(fieldName) +"' ";
			}
		}
		return completeSql;
	}

	private static final String PRE_NAME = "com.funeral.kris.model.";
}