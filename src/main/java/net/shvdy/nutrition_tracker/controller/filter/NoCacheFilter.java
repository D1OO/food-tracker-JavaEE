package net.shvdy.nutrition_tracker.controller.filter;

/**
 * 11.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NoCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Cache-Control", "no-store, no-cache,  must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}










