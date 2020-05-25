package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserPage implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute("headerNews",
					CommandEnum.getArticleService().findRandom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/view/user.jsp";
	}
}
