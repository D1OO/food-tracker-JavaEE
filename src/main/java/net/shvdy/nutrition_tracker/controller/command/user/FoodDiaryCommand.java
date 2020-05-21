package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodDiaryCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		request.getSession().getServletContext().setAttribute("data", new HashMap<>());
		request.getSession().getServletContext().setAttribute("dateName", "datename");
		request.getSession().getServletContext().setAttribute("lastDate", "lastdate");
		request.getSession().getServletContext().setAttribute("profileFilled", true); //{rec.getTotalCalories() != -1
		request.getSession().getServletContext().setAttribute("recPercentage", 23); //{rec.getTotalCalories() != -1
		request.getSession().getServletContext().setAttribute("dailyCalsNorm", 23); //{rec.getTotalCalories() != -1
//		request.getSession().getServletContext().setAttribute("user", 5); //{rec.getTotalCalories() != -1
		request.getSession().getServletContext().setAttribute("userId", 22); //{rec.getTotalCalories() != -1

		try {
			request.getSession().getServletContext().setAttribute("dailyRecord",
					CommandEnum.getDailyRecordService().findByDate("2020-03-30"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "/view/user/food-diary.jsp";
	}
}
