package com.funeral.kris.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	public Connection conn;

	public MySQL() {

		String url = null;
		String user = null;
		String password = null;

		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载错误");
			e.printStackTrace();// 打印出错详细信息
		}
		try {

			// #DB properties:
			// db.driver=com.mysql.jdbc.Driver
			// db.url=jdbc:mysql://121.42.182.117/spring_tst
			//
			// db.username=spring_tst
			//
			// db.password=Ebaotech250
			//
			// #Hibernate Configuration:
			// hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
			// hibernate.show_sql=true
			// entitymanager.packages.to.scan=com.funeral.kris.model
			url = "jdbc:mysql://121.42.182.117/spring_tst?user=spring_tst&password=Ebaotech250&useUnicode=true&&characterEncoding=UTF-8&autoReconnect = true";
			// 简单写法：url = "jdbc:myqsl://localhost/test(数据库名)?
			// user=root(用户)&password=yqs2602555(密码)";
			user = "spring_tst";
			password = "Ebaotech250";
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("数据库链接错误");
			e.printStackTrace();
		}

	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	
}