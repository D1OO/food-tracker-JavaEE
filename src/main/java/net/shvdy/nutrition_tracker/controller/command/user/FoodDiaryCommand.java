package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.model.entity.Food;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodDiaryCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		try {
			request.getSession().getServletContext().setAttribute("data",
					List.of(CommandEnum.getDailyRecordService().findByDate("2020-03-30",
							Locale.forLanguageTag((String)request.getSession().getAttribute("lang")))));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "/view/user/server-error.jsp";
		}
		request.getSession().getServletContext().setAttribute("lastDate", "lastdate");
		request.getSession().getServletContext().setAttribute("profileFilled", true); //{rec.getTotalCalories() != -1
//		request.getSession().getServletContext().setAttribute("user", 5); //{rec.getTotalCalories() != -1
//		request.getSession().setAttribute("userFood", user.getUserFood());

		try {
			request.getSession().getServletContext().setAttribute("dailyRecord",
					CommandEnum.getDailyRecordService().findByDate("2020-03-30", request.getLocale()));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "/view/user/food-diary.jsp";
	}

}
