package com.funeral.kris.rowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;  
  
import com.funeral.kris.model.User;
  
public class UserMapper implements RowMapper<User> {
	public UserMapper() {
		filedMap.put("usrId", "user_id");
		filedMap.put("userName", "user_name");
		filedMap.put("userType", "User_type");
		filedMap.put("userRef", "user_ref");
		filedMap.put("email", "e_mail");
		filedMap.put("address", "address");
		filedMap.put("password", "pwd");
		filedMap.put("phone", "phone");
		filedMap.put("contactor", "contactor");
		filedMap.put("contactorMail", "contactor_mail");
		filedMap.put("contactorPhone", "contactor_phone");
		filedMap.put("updatedDate", "updateddate");
		filedMap.put("createDate", "createdate");
	}
	public static Map<String, String> filedMap = new HashMap<String, String>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public User mapRow(ResultSet rs, int index) throws SQLException {
    	Class c = null;
    	Object o = null;
    	try {
    		c = Class.forName("com.funeral.kris.model.User");
    		o = c.newInstance();
    		Field f[]=c.getDeclaredFields();
    		for(int i=0;i<f.length;i++) {
    			String fieldName = f[i].getName();
    			String filedNameInMethod = getFiledNameInMethod(fieldName);
    			if (f[i].getType().getName().equals("java.lang.String")) {
    				Method method = c.getDeclaredMethod("set"+filedNameInMethod, String.class);
    				method.invoke(o, rs.getString(filedMap.get(fieldName)));
    			}
    			else if (f[i].getType().getName().equals("java.lang.Integer")) {
    				Method method = c.getDeclaredMethod("set"+filedNameInMethod, Integer.class);
    				method.invoke(o, rs.getInt(filedMap.get(fieldName)));
    			}
    			else if (f[i].getType().getName().equals("java.util.Date")) {
    				Method method = c.getDeclaredMethod("set"+filedNameInMethod, Date.class);
    				method.invoke(o, rs.getDate(filedMap.get(fieldName)));
    			}
    		}
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
		return (User)o; 
    }
    
    private String getFiledNameInMethod(String fieldName) {
    	String firstCharacter = fieldName.substring(0, 1);
    	firstCharacter = firstCharacter.toUpperCase();
    	String filedNameInMethod = firstCharacter + fieldName.substring(1, fieldName.length());
    	return filedNameInMethod;
    }
  
} 
