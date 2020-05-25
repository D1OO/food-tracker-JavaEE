package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.dto.ArticleDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ReadArticle implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int requestedArticleId = Integer.parseInt(request.getParameter("id"));
		List<ArticleDTO> paginatedArticles = (List<ArticleDTO>) request.getSession().getAttribute("paginatedArticles");

		ArticleDTO requestedArticle;
		try {
			requestedArticle = getArticleFromCacheOrService(requestedArticleId, paginatedArticles);
		} catch (SQLException e) {
			e.printStackTrace();
			return "/view/feed.jsp";
		}

		request.getSession().setAttribute("text", requestedArticle.getText());
		request.getSession().setAttribute("base64Image", requestedArticle.getBase64Image());
		request.getSession().setAttribute("title", requestedArticle.getTitle());
		request.getSession().setAttribute("date", requestedArticle.getDate());
		request.getSession().setAttribute("author", requestedArticle.getAuthorName());
		return "/view/article.jsp";
	}

	private ArticleDTO getArticleFromCacheOrService(int id, List<ArticleDTO> cache) throws SQLException {
		return cache.stream()
				.filter(x -> x.getArticleId() == id)
				.findAny()
				.orElse(getFromService(id));
	}

	private ArticleDTO getFromService(int id) throws SQLException {
		return CommandEnum.getArticleService().findByID(id);
	}
}
