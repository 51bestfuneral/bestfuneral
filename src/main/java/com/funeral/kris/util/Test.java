package com.funeral.kris.util;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
		String id="3";
		
		String rerurnUrl="<script> window.location='/funeral/pages/configuration/cemeteryConfiguration/updateCemetery.html?id=$' ;window.close();</script>";
		rerurnUrl=rerurnUrl.replaceAll("$", id);
	
         System.out.println(rerurnUrl);
	}

}
