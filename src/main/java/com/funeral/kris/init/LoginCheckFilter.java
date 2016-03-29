package com.funeral.kris.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.funeral.kris.init.constants.LoginConstants;
import com.mysql.jdbc.StringUtils;

@Component
public class LoginCheckFilter implements Filter {

	private static List<String> URIsList = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		URIsList.add("wishLis.html");

	}
	public void initUrls() throws ServletException {

		URIsList.add("wishLis.html");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String js="<script> window.open('/funeral/pages/component/logIn.html', 'newwindow','height=100, width=400, top=0,left=0,toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no') </script>";
		
		if(URIsList.isEmpty()){
			initUrls();
		}

		System.out.println("  come on LoginCheckFilter---");
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		
	
		String requestURL = request.getRequestURL().toString();

		if (!needCheck(requestURL)) {
			
			System.out.println("  come on LoginCheckFilter--1-");

			chain.doFilter(req, res);
		} else if (session.getAttribute(LoginConstants.LoginStatus) != null
				&& !StringUtils.isNullOrEmpty(session.getAttribute(LoginConstants.LoginStatus).toString())
				) {
			
			System.out.println("  come on LoginCheckFilter--2-");


			if (session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {
				System.out.println("  come on LoginCheckFilter--3-");

				chain.doFilter(req, res);
			} else {
				System.out.println("  come on LoginCheckFilter--4-");


				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(
						"<script> window.open('/funeral/pages/component/logIn.html')</script>");
				response.getWriter().flush();
			}

		} else {
			System.out.println("  come on LoginCheckFilter--5-");

			response.setContentType("text/html;charset=utf-8");
			response.getWriter()
					.write("<script> window.open('/funeral/pages/component/logIn.html')</script>");
			response.getWriter().flush();

		}

	}

	private boolean needCheck(String requestURL) {

		java.util.Iterator<String> iterator = URIsList.iterator();

		while (iterator.hasNext()) {

			String url = iterator.next();
			
			System.out.println("----LoginCheckFilter  url  = "+url);

			if (requestURL.endsWith(url)) {

				return true;
			}

		}

		return false;

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}