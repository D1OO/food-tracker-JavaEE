package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserPage implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.getSession().setAttribute("headerNews",
					CommandEnum.getArticleService().findRandom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/view/user.jsp";
	}
}
