package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

/**
 * 10.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class ShowGroupMemberData implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO member = ContextHolder.userService().findByUsername(request.getParameter("username"));
        request.getSession().setAttribute("member", member);
        processPagination(request, member);
        Response.FORWARD.execute()
                .response("/view/fragments/admin/member-data.jsp", request, response);
    }

    private void processPagination(HttpServletRequest request, UserDTO user) {
        String datePeriodLastDay = Optional.ofNullable(request.getParameter("d"))
                .orElse(LocalDate.now().toString());
        int pageSize = Integer.parseInt((String) request.getServletContext()
                .getAttribute("dairy_weekly-view-records-quantity"));

        request.getSession().setAttribute("paginatedWeeklyRecords",
                ContextHolder.dailyRecordService().findPaginated(
                        user.getUserId(), datePeriodLastDay, pageSize,
                        Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));

        request.getSession().setAttribute("prevWeekDay",
                datePeriodLastDay.equals(LocalDate.now().toString()) ? null :
                        LocalDate.parse(datePeriodLastDay).plusDays(pageSize).toString());

        request.getSession().setAttribute("nextWeekDay",
                LocalDate.parse(datePeriodLastDay).minusDays(pageSize).toString());
    }

}
