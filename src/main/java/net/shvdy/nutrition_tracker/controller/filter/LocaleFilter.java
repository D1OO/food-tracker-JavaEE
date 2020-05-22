package net.shvdy.nutrition_tracker.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


public class LocaleFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String lang = httpServletRequest.getParameter("lang");
		httpServletRequest.getSession().setAttribute("lang", Objects.requireNonNullElse(lang, "ru"));

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
