package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        retrieveArticle(request);
        return "/view/fragments/article.jsp";
    }

    private void retrieveArticle(HttpServletRequest request) {
        ArticleDTO requestedArticle;
        int requestedArticleId = Integer.parseInt(request.getParameter("id"));

        try {
            requestedArticle = getArticleFromCacheOrService(request, requestedArticleId,
                    Locale.forLanguageTag((String) request.getSession().getAttribute("lang")));
        } catch (NoSuchElementException e) {
            ContextHolder.logger().warn("Requested article not found: " + requestedArticleId);
            throw e;
        }

        request.getSession().setAttribute("titleLocalisation", requestedArticle.getTitleLocalisation());
        request.getSession().setAttribute("textLocalisation", requestedArticle.getTextLocalisation());
        request.getSession().setAttribute("base64Image", requestedArticle.getBase64Image());
        request.getSession().setAttribute("date", requestedArticle.getDate());
        request.getSession().setAttribute("author", requestedArticle.getAuthorName());
    }

    private ArticleDTO getArticleFromCacheOrService(HttpServletRequest request, int id, Locale locale)
            throws NoSuchElementException {
        return ((List<ArticleDTO>) request.getSession().getAttribute("paginatedArticles")).stream()
                .filter(x -> x.getArticleId() == id)
                .findAny()
                .orElse(getFromService(id, locale));
    }

    private ArticleDTO getFromService(int id, Locale locale) throws NoSuchElementException {
        return ContextHolder.articleService().findByIDForLocale(id, locale);
    }
}
