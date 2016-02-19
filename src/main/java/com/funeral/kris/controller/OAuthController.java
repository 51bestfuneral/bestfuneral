package com.funeral.kris.controller;

import java.net.URL;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.model.User;
import com.funeral.kris.service.OAuthService;
import com.funeral.kris.service.OAuthServiceImpl;
import com.funeral.kris.service.UserService;


@Controller
@RequestMapping(value = "/oauth")
public class OAuthController {
	
	@Autowired
	private OAuthService oauthService;
	
	@Autowired
	private UserService userService;
	
	
	
	@ResponseBody
	@RequestMapping(value = "getUrl", method = RequestMethod.POST)
	public Map<String, String> getUrl(HttpServletRequest request) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String oauthType = request.getParameter("oauthType");
		String returnUrl = request.getParameter("returnUrl");
		String url = oauthService.buildUrl(oauthType,returnUrl);
		resultMap.put("url", url);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateQQ", method = RequestMethod.GET)
	public String validateQQ(HttpServletRequest request) {
		//1. get token
		try{
	    String authorizationCode = request.getParameter("Code");
	    String appId =OAuthServiceImpl.QQ_APP_ID;
	    String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"
	    +"appid=APPID&secret=SECRET&code=CODE"+"&grant_type=authorizationCode";
	    tokenUrl=tokenUrl.replace("APPID", OAuthServiceImpl.QQ_APP_ID);
	    tokenUrl=tokenUrl.replace("secret", OAuthServiceImpl.QQ_APP_KEY);
	    tokenUrl=tokenUrl.replace("CODE", authorizationCode);
	    tokenUrl=tokenUrl.replace("authorizationCode", authorizationCode);
		String result = this.getAccessTokenAndOpenId(tokenUrl);
		String accessToken ="";
		String openId ="";
		String userUrl ="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESSTOKEN&openid=OPENID";
		userUrl=userUrl.replace("ACCESSTOKEN", accessToken);
		userUrl=userUrl.replace("OPENID", openId);
		String userInfo = this.getUserInfo(userUrl);
		
		//inset user
		User user = new User();
		userService.addResource(user);
		return "Success";
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public String getAccessTokenAndOpenId(String urlString) throws Exception{
		String res = "";   
        try {   
            URL url = new URL(urlString);  
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();  
            conn.setDoOutput(true);  
            conn.setRequestMethod("POST");  
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                res += line;  
            }  
            in.close();  
        } catch (Exception e) {  
        }  
        // System.out.println(res);  
        return res;  
	}
	
	public String getUserInfo(String urlString) throws Exception{
		String res = "";   
        try {   
            URL url = new URL(urlString);  
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();  
            conn.setDoOutput(true);  
            conn.setRequestMethod("POST");  
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                res += line;  
            }  
            in.close();  
        } catch (Exception e) {  
        }  
        // System.out.println(res);  
        return res;  
	}

	

}
