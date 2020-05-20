package net.shvdy.nutrition_tracker.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class LocaleFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		if (httpServletRequest.getParameter("sessionLocale") != null) {
			httpServletRequest.getSession().setAttribute("lang", httpServletRequest.getParameter("sessionLocale"));
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
