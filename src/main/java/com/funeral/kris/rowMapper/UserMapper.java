package com.funeral.kris.rowMapper;

import java.sql.ResultSet;  
import java.sql.SQLException;  
  
import org.springframework.jdbc.core.RowMapper;  
  
import com.funeral.kris.model.User;
  
public class UserMapper implements RowMapper<User> {  
  
    public User mapRow(ResultSet rs, int index) throws SQLException {  
    	User user = new User();  
    	user.setUsrId(rs.getInt("user_id"));
    	user.setUserName(rs.getString("user_name"));
    	user.setUserType(rs.getString("User_type"));
    	user.setUserRef(rs.getString("user_ref"));
    	user.setEmail(rs.getString("e_mail"));
    	user.setAddress(rs.getString("address"));
    	user.setPassword(rs.getString("pwd"));
    	user.setPhone(rs.getString("phone"));
    	user.setCreateDate(rs.getDate("createdate"));
    	user.setUpdatedDate(rs.getDate("updateddate"));
    	user.setContactor(rs.getString("contactor"));
    	user.setContactorMail(rs.getString("contactor_mail"));
    	user.setContactorPhone(rs.getString("contactor_phone"));
        return user;  
    }  
  
} 
