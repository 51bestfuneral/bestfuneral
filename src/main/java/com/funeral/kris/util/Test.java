package com.funeral.kris.util;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
	
		
		String test="1";
		
		System.out.println(new BigDecimal(test).setScale(2, BigDecimal.ROUND_HALF_UP));
		
	}

}
