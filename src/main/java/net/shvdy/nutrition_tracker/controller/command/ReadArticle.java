package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.util.CommandUtil;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ReadArticle implements ActionCommand {

    private static final Logger log = LogManager.getLogger(ReadArticle.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int requestedArticleId = Integer.parseInt(request.getParameter("id"));

        try {
            request.getSession().setAttribute("article", getArticleFromCacheOrService(request, requestedArticleId,
                    CommandUtil.getCurrentLocale(request)));
            Response.FORWARD.execute().response("/view/fragments/article.jsp", request, response);
        } catch (NoSuchElementException e) {
            log.warn("Requested article not found: " + requestedArticleId);
            Response.NOT_FOUND_404.execute().response("", request, response);
        }
    }

    private ArticleDTO getArticleFromCacheOrService(HttpServletRequest request, int id, Locale locale)
            throws NoSuchElementException {
        return ((List<ArticleDTO>) request.getSession().getAttribute("paginatedArticles")).stream()
                .filter(x -> x.getArticleId() == id)
                .findFirst()
                .orElseGet(() -> ContextHolder.articleService().findByIDForLocale(id, locale));
    }
}
