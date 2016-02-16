package com.funeral.kris.util;

import java.lang.reflect.Field;

public class DataExport {
	private static final String PRE_NAME = "com.funeral.kris.model.";

	public void createFile(String inputStr) {

	}

	public void generateInsertScript(String tableName) throws Exception {

		Class c = Class.forName(PRE_NAME + tableName);
		Field f[] = c.getDeclaredFields();
		StringBuffer completeSql = new StringBuffer("insert into " + tableName);

		StringBuffer fieldStr = new StringBuffer("{");

		for (int i = 0; i < f.length; i++) {
			String fieldName = f[i].getName();

			fieldStr.append(fieldName + ",");
		}

		fieldStr.append("}");
		
		
		
		

	}

}
