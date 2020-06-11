package net.shvdy.nutrition_tracker.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * 10.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SectionContainerRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = ((HttpServletRequest) request).getSession();

        if (Optional.ofNullable(request.getParameter("AJAXrequest")).isEmpty()) {
            session.setAttribute("sectionToFetchWithAJAX",
                    httpRequest.getRequestURI() + '?' + parseParams(httpRequest.getParameterMap()));
            ((HttpServletResponse) response).sendRedirect(session.getAttribute("userRole").toString().toLowerCase());
        } else {
            filterChain.doFilter(request, response);
        }
    }


    @Override
    public void destroy() {
    }

    private String parseParams(Map<String, String[]> params) {
        return params.entrySet().stream().map(x -> x.getKey() + '=' +
                Arrays.toString(x.getValue()).replace("[", "")
                        .replace("]", "")).reduce(String::concat).orElse("");
    }

}
