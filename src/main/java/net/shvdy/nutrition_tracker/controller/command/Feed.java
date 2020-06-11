package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Feed implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().setAttribute("paginatedArticles",
                ContextHolder.articleService().findPaginatedForLocale(Locale
                        .forLanguageTag((String) request.getSession().getAttribute("lang"))));
        Response.FORWARD.execute().response("/view/fragments/feed.jsp", request, response);
    }
}
