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
		
		if (request != null) {
			Class c = Class.forName(PRE_NAME+cName);
			Field f[]=c.getDeclaredFields();
			for(int i=0;i<f.length;i++) {
				String fieldName = f[i].getName();
				if (request.getParameter(fieldName) != null && !request.getParameter(fieldName).equals("")) {
					if (request.getParameter(fieldName).indexOf("-") >=0) {
						String[] strs = request.getParameter(fieldName).split("-");
						String conditionStr = "";
						for (String str: strs) {
							if (conditionStr.equals("")) {
							    conditionStr = "'"+str+"'";
							}
							else {
								conditionStr = conditionStr+ ",'" + str + "'";
							}
						}
						completeSql = completeSql + " and "+ fieldName + " in ("+ conditionStr + ")";
					}
					else {
					    completeSql = completeSql + " and " + fieldName + " = '" + request.getParameter(fieldName) +"' ";
					}
				}
			}

			if (request.getParameter("orderBy") != null && !request.getParameter("orderBy").equals("")) {
				completeSql = completeSql + " order by " + request.getParameter("orderBy");

				if (request.getParameter("reverse") != null) {
					completeSql = completeSql + " desc";
				}
			}
		}
		return completeSql;
	}

	@SuppressWarnings("rawtypes")
	public static String getSqlFromMap(String cName, Map<String, String> paramMap) throws Exception{
		String completeSql = "select q from "+ cName +" q where 1= 1";
		
		if (paramMap != null) {
			Class c = Class.forName(PRE_NAME+cName);
			Field f[]=c.getDeclaredFields();
			for(int i=0;i<f.length;i++) {
				String fieldName = f[i].getName();
				if (paramMap.get(fieldName) != null && !paramMap.get(fieldName).equals("")) {
					if (paramMap.get(fieldName).indexOf("-") >=0) {
						String[] strs = paramMap.get(fieldName).split("-");
						String conditionStr = "";
						for (String str: strs) {
							if (conditionStr.equals("")) {
							    conditionStr = "'"+str+"'";
							}
							else {
								conditionStr = conditionStr+ ",'" + str + "'";
							}
						}
						completeSql = completeSql + " and "+ fieldName + " in ("+ conditionStr + ")";
					}
					else {
					    completeSql = completeSql + " and " + fieldName + " = '" + paramMap.get(fieldName) +"' ";
					}
				}
			}
		}
		return completeSql;
	}

	private static final String PRE_NAME = "com.funeral.kris.model.";
}