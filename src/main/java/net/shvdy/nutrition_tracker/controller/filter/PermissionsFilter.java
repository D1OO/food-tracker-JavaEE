package net.shvdy.nutrition_tracker.controller.filter;

import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PermissionsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        Role role = (Role) session.getAttribute("user.role");
        String path = ((HttpServletRequest) request).getRequestURI();

        if (CommandEnum.checkIsPathPermitted(path, role)) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/redirect:home").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
