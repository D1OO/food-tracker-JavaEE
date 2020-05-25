package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class AdminPage implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		List<ArticleDTO> randomNews = new ArrayList<>();
		try {
			randomNews = CommandEnum.getArticleService().findPaginated();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Collections.shuffle(randomNews);
		request.getSession().setAttribute("headerNews", randomNews.subList(0,3));
		return "/view/admin.jsp";
	}
}
