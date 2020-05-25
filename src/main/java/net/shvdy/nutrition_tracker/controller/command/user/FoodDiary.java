package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;

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
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		processPagination(request);
		return SecurityUtility.processSectionRequest("food-diary", request);
	}

	private void processPagination(HttpServletRequest request) throws SQLException {
		String currWeekDay = Optional.ofNullable(request.getParameter("d"))
				.orElse(LocalDate.now().toString());
		int pageSize = Integer.parseInt((String) request.getServletContext().getAttribute("page-size"));
		request.getSession().setAttribute("paginatedWeeklyRecords",
				CommandEnum.getDailyRecordService().findPaginated(
						(Long) request.getSession().getAttribute("user.userId"),
						currWeekDay, pageSize,
						Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));
		request.getSession().setAttribute("prevWeekDay",
				currWeekDay.equals(LocalDate.now().toString()) ? null :
						LocalDate.parse(currWeekDay).plusDays(pageSize).toString());
		request.getSession().setAttribute("nextWeekDay",
				LocalDate.parse(currWeekDay).minusDays(pageSize).toString());
	}
}
