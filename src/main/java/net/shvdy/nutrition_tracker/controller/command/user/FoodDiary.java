package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String currWeekDay = Optional.ofNullable(request.getParameter("d"))
				.orElse(LocalDate.now().toString());
		int pageSize = Integer.parseInt((String) request.getServletContext().getAttribute("page-size"));

		try {
			request.getServletContext().setAttribute("paginatedWeeklyRecords",
					CommandEnum.getDailyRecordService().findPaginated(
							(Long) request.getServletContext().getAttribute("userId"),
							currWeekDay, pageSize,
							Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));
		} catch (SQLException e) {
			e.printStackTrace();
			return "/view/user/server-error.jsp";
		}

		request.getServletContext().setAttribute("prevWeekDay",
				currWeekDay.equals(LocalDate.now().toString()) ? null :
				LocalDate.parse(currWeekDay).plusDays(pageSize).toString());
		request.getServletContext().setAttribute("nextWeekDay",
				LocalDate.parse(currWeekDay).minusDays(pageSize).toString());

		return "/view/user/food-diary.jsp";
	}

}
