package com.funeral.kris.util;
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
public class WordUtils {
	
	 private Configuration configuration = null;  
     
	    public WordUtils(){  
	        configuration = new Configuration();  
	        configuration.setDefaultEncoding("UTF-8");  
	    }  
	      
	    public static void main(String[] args) {  
	        WordUtils test = new WordUtils();  
	        test.createWord();  
	    }  
	      
	    public void createWord(){  
	        Map<String,Object> dataMap=new HashMap<String,Object>();  
	        getData(dataMap);  
	        configuration.setClassForTemplateLoading(this.getClass(), "/com/funeral/kris/util/");  //FTL鏂囦欢鎵�瓨鍦ㄧ殑浣嶇疆  
	        Template t=null;  
	        try {  
	            t = configuration.getTemplate("funeral_service_contract_template.ftl"); //鏂囦欢鍚� 
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        File outFile = new File("/funeral/generateFiles/"+Math.random()*10000+".doc");  
	        Writer out = null;  
	        try {  
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
	        } catch (FileNotFoundException e1) {  
	            e1.printStackTrace();  
	        }  
	           
	        try {  
	            t.process(dataMap, out);  
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    private void getData(Map<String, Object> dataMap) {  
	        dataMap.put("name1", "");  
	        dataMap.put("name2", "");  
	        dataMap.put("amount", "2000");  
	       
	          
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
	        for (int i = 0; i < 10; i++) {  
	            Map<String,Object> map = new HashMap<String,Object>();  
	            map.put("number", i);  
	            map.put("content", "鍐呭"+i);  
	            list.add(map);  
	        }  
	          
	          
	        dataMap.put("list", list);  
	    }  
	
	
	

}
