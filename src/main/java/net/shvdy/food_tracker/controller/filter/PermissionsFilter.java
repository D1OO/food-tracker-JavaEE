package net.shvdy.food_tracker.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.shvdy.food_tracker.controller.command.CommandEnum;
import net.shvdy.food_tracker.model.entity.Role;

public class PermissionsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        Role role = (Role) session.getAttribute("role");
        String path = ((HttpServletRequest) request).getRequestURI();

        if (CommandEnum.checkIsPathPermitted(path, role)) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/redirect-home").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
