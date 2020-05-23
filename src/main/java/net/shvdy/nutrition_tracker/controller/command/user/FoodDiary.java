package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodDiary implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.getSession().getServletContext().setAttribute("paginatedWeeklyRecords",
					CommandEnum.getDailyRecordService().findPaginated(
							(Long) request.getServletContext().getAttribute("userId"),
							Optional.ofNullable((String) request.getAttribute("requestedDate"))
									.orElse(LocalDate.now().toString()),
							7,
							Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));
		} catch (SQLException e) {
			e.printStackTrace();
			return "/view/user/server-error.jsp";
		}
//		request.getSession().getServletContext().setAttribute("lastDate", "lastdate");

		return "/view/user/food-diary.jsp";
	}

}
