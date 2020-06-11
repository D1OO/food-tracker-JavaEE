package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        ArticleDTO requestedArticle;
        int requestedArticleId = Integer.parseInt(request.getParameter("id"));

        try {
            requestedArticle = getArticleFromCacheOrService(request, requestedArticleId,
                    Locale.forLanguageTag((String) session.getAttribute("lang")));
        } catch (NoSuchElementException e) {
            log.warn("Requested article not found: " + requestedArticleId);
            Response.NOT_FOUND_404.execute().response("", request, response);
            return;
        }

        session.setAttribute("titleLocalisation", requestedArticle.getTitleLocalisation());
        session.setAttribute("textLocalisation", requestedArticle.getTextLocalisation());
        session.setAttribute("base64Image", requestedArticle.getBase64Image());
        session.setAttribute("date", requestedArticle.getDate());
        session.setAttribute("author", requestedArticle.getAuthorName());
        Response.FORWARD.execute().response("/view/fragments/article.jsp", request, response);
    }

    private ArticleDTO getArticleFromCacheOrService(HttpServletRequest request, int id, Locale locale)
            throws NoSuchElementException {
        return ((List<ArticleDTO>) request.getSession().getAttribute("paginatedArticles")).stream()
                .filter(x -> x.getArticleId() == id)
                .findFirst()
                .orElseGet(() -> ContextHolder.articleService().findByIDForLocale(id, locale));
    }
}
