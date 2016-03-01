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
			Class.forName("com.mysql.jdbc.Driver"); // 鍔犺浇mysq椹卞姩
		} catch (ClassNotFoundException e) {
			System.out.println("椹卞姩鍔犺浇閿欒");
			e.printStackTrace();// 鎵撳嵃鍑洪敊璇︾粏淇℃伅
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
			// 绠�崟鍐欐硶锛歶rl = "jdbc:myqsl://localhost/test(鏁版嵁搴撳悕)?
			// user=root(鐢ㄦ埛)&password=yqs2602555(瀵嗙爜)";
			user = "spring_tst";
			password = "Ebaotech250";
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
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