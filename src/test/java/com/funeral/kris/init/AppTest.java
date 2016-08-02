package com.funeral.kris.init;

public class AppTest {

	private static ThreadLocal threadLocal = new ThreadLocal();

	public static void setName(String name) {

		threadLocal.set(name);

	}

	public static String getName() {
		return (String) threadLocal.get();

	}

}
