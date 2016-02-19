package com.funeral.kris.init;

import com.funeral.kris.model.User;

public class AppContext {

	protected static final ThreadLocal threadLocal = new ThreadLocal();

	
	public static User getUser() throws Exception {

		System.out.println(AppContext.class + "   --- getUser threadLocal= " + threadLocal);
		
		if( threadLocal==null){
			throw new Exception("user is null!"); 
		}
		

		return (User) threadLocal.get();
	}

	public static void setUser(User user) {
		
		if(user!=null){
			threadLocal.set(user);
		}

		System.out.println(AppContext.class + "   --- setUser threadLocal= " + threadLocal + " user=" + user);

		

	}

}
