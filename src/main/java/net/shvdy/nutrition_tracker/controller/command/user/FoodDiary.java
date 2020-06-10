package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        processPagination(request);
        return "/view/fragments/section/food-diary.jsp";
    }

    private void processPagination(HttpServletRequest request) {
        String datePeriodLastDay = Optional.ofNullable(request.getParameter("d"))
                .orElse(LocalDate.now().toString());
        int pageSize = Integer.parseInt((String) request.getServletContext().getAttribute("page-size"));

        request.getSession().setAttribute("paginatedWeeklyRecords",
                ContextHolder.dailyRecordService().findPaginated(
                        (Long) request.getSession().getAttribute("user.userId"),
                        datePeriodLastDay, pageSize,
                        Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));

        request.getSession().setAttribute("prevWeekDay",
                datePeriodLastDay.equals(LocalDate.now().toString()) ? null :
                        LocalDate.parse(datePeriodLastDay).plusDays(pageSize).toString());

        request.getSession().setAttribute("nextWeekDay",
                LocalDate.parse(datePeriodLastDay).minusDays(pageSize).toString());
    }
}
