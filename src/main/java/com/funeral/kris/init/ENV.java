package com.funeral.kris.init;
 

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//配置在application.properties 
public class ENV
{
  protected static Properties config = null;
 
  public static synchronized void load()
  {
	  
	  String contextPath=ENV.class.getClassLoader().getResource("/").getPath(); 	
		
		if(contextPath!=null){
			
			contextPath=contextPath.replaceAll("/WEB-INF/classes/", "");
		}
	    System.out.println(ENV.class+"-------------contextPath--"+		contextPath);
	 
	  
    FileInputStream cfg = null;
    try {
      config = new Properties();
      File temp = new File("/usr/local/tomcat/webapps/funeral/WEB-INF/classes/application.properties");
 
      cfg = new FileInputStream(temp);
      config.load(cfg);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    } finally {
      try {
        if (cfg != null)
          cfg.close();
      }
      catch (IOException e) {
      }
    }
  }
 
  public static String getValue(String key) {
   return config.getProperty(key);
  }
 

}
