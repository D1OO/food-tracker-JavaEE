package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.model.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SaveNewArticle  implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Article article = Article.builder()
				.author(request.getParameter("author"))
				.title(request.getParameter("title"))
				.date(LocalDate.now().toString())
				.text(request.getParameter("text"))
				.image(readImage(request))
				.build();
		try {
			CommandEnum.getArticleService().save(article);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "/view/admin/new-article-window.jsp";
	}

	private InputStream readImage(HttpServletRequest request) {
		Part filePart = null;
		try {
			filePart = request.getPart("image");
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		if (filePart != null) {
			try {
				return filePart.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
