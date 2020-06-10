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
                CommandEnum.getPostEndpoints().contains((httpRequest).getRequestURI()))
            request.getRequestDispatcher("404").forward(request, response);

        Role role = (Role) ((HttpServletRequest) request).getSession().getAttribute("user.role");

        if (CommandEnum.checkIsPathForbidden(((HttpServletRequest) request).getRequestURI(), role)) {
            request.getRequestDispatcher(
                    role.equals(Role.GUEST) ? "/redirect:home" : "/view/errors/403.jsp").forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
