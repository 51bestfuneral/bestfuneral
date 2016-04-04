package com.funeral.kris.util;

import com.funeral.kris.pay.dao.PayDAOImpl;

public class Test {

	public static void main(String[] args) {
	
		
		PayDAOImpl  dao=new PayDAOImpl();
		
		System.out.println(dao.getOrderByOrderNo("1").getOrderId());
		
	}

}
