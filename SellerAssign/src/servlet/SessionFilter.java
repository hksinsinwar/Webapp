package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
//@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {

	/**
	 * Default constructor.
	 * 
	 */
	List<String> skipUrls = new ArrayList<String>();

	public SessionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// // place your code here
		// HttpServletRequest req = (HttpServletRequest) request;
		// HttpServletResponse res = (HttpServletResponse) response;
		// // if (!skipUrls.contains(req.getServletPath())) {
		// // res.sendRedirect("/SellerAssign/login/Login.jsp");
		// // }
		// System.out.println("req.getServletPath()" + req.getServletPath());
		// if (!req.getServletPath().equals("/login/Login.jsp")) {
		// Cookie authCookie = (Cookie) req.getSession().getAttribute(
		// "authCookie");
		// Cookie[] cookie = req.getCookies();
		// boolean isVerified = false;
		// for (Cookie ck : cookie) {
		// if (ck.getName().equals(authCookie.getName())
		// && ck.getValue().equals(authCookie.getValue())) {
		// isVerified = true;
		// break;
		// }
		// }
		// if (!isVerified) {
		// res.sendRedirect("/SellerAssign/login/Login.jsp");
		// res.flushBuffer();
		// }
		// }
		// // pass the request along the filter chain
		

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getServletPath();
		boolean allowedRequest = false;

		if (skipUrls.contains(url)) {
			allowedRequest = true;
		}
		System.out.println("allowedres"+allowedRequest);
		if (!allowedRequest) {
			HttpSession session = req.getSession(false);
			if (null == session) {
				res.sendRedirect("/login/Login.jsp");
			}
		}
		 chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		String skipU = fConfig.getInitParameter("skipUrls");
		System.out.print("skipUrls" + skipU);
		if (skipU != null) {
			String[] skipUrl = skipU.split(",");
			for (String s : skipUrl) {
				skipUrls.add(s);
			}
		}
	}

}
