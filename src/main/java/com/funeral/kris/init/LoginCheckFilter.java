package com.funeral.kris.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		URIsList.add("wishList.html");
		URIsList.add("paymentInfo.html");
		URIsList.add("paymentMethod.html");
		URIsList.add("paymentConfirm.html");
		URIsList.add("designProcess.html");
	}

	public void initUrls() throws ServletException {

		URIsList.add("wishList.html");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String js = "<script src='../js/generalUsage.js'></script><script>popupLogInPage();</script>";

		if (URIsList.isEmpty()) {
			initUrls();
		}
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);

		
		
		
		String requestURL = request.getRequestURL().toString();
		String url = response.encodeRedirectURL(request.getRequestURL().toString());
		if (url.indexOf("www.niannian.com") > 0) {

			url = "http://www.365niannian.com";
		}

		if (!needCheck(requestURL)) {

			chain.doFilter(req, res);
		} else if (session != null && session.getAttribute(LoginConstants.LoginStatus) != null
				&& session.getAttribute("user") != null
				&& !StringUtils.isNullOrEmpty(session.getAttribute(LoginConstants.LoginStatus).toString())) {

			if (session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {

				chain.doFilter(req, res);
			} else {

				response.setContentType("text/html;charset=utf-8");

				response.sendRedirect("mainPage.html?returnUrl=" + url);
			}

		} else {

			response.setContentType("text/html;charset=utf-8");
			response.sendRedirect("needLogIn.html?returnUrl=" + url);

		}

	}

	private boolean needCheck(String requestURL) {

		java.util.Iterator<String> iterator = URIsList.iterator();

		while (iterator.hasNext()) {

			String url = iterator.next();

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
