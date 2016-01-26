package com.funeral.kris.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.funeral.kris.model.User;
import com.funeral.kris.service.UserService;


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