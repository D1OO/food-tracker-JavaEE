package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Feed implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("paginatedArticles",
                ContextHolder.articleService().findPaginatedForLocale(Locale
                        .forLanguageTag((String) request.getSession().getAttribute("lang"))));
        return "/view/fragments/feed.jsp";
    }
}
