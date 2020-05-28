package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.model.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SaveNewArticle  implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Article article = Article.builder()
				.authorId(Long.parseLong(request.getParameter("authorId")))
				.title(request.getParameter("title"))
				.date(LocalDateTime.now().toString())
				.text(request.getParameter("text"))
				.image(readImage(request).orElse(InputStream.nullInputStream()))
				.build();
		try {
			ContextHolder.getArticleService().save(article);
		} catch (SQLException e) {
			ContextHolder.getLogger().error("Article saving exception: " + e.getMessage());
		}

		return "/view/fragments/feed.jsp";
	}

	private Optional<InputStream> readImage(HttpServletRequest request) {
		Optional<Part> filePart = Optional.empty();
		try {
			filePart = Optional.ofNullable(request.getPart("image"));
		} catch (IOException | ServletException e) {
			ContextHolder.getLogger().error("Image loading error: " + e.getMessage());
		}
		if (filePart.isPresent()) {
			try {
				return Optional.of(filePart.get().getInputStream());
			} catch (IOException e) {
				ContextHolder.getLogger().error("Image loading error: " + e.getMessage());
			}
		}
		return Optional.empty();
	}
}
