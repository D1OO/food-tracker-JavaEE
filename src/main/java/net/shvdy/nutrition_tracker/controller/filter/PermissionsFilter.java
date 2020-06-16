package net.shvdy.nutrition_tracker.controller.filter;

import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PermissionsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (!httpRequest.getMethod().equals("POST") &&
                CommandEnum.getPostEndpoints().contains((httpRequest).getRequestURI())) {
            httpRequest.getRequestDispatcher("404").forward(httpRequest, response);
        } else if (CommandEnum.checkIsPathAllowed(httpRequest.getRequestURI(),
                (Role) httpRequest.getSession().getAttribute("userRole"))) {
            filterChain.doFilter(httpRequest, response);
        } else {
            httpRequest.getRequestDispatcher("/redirect:home").forward(httpRequest, response);
        }
    }

    @Override
    public void destroy() {
    }
}
