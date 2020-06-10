package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.dto.DailyRecordDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.exception.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDTO wantedUser;
        try {
            wantedUser = ContextHolder.userService()
                    .findByUsernameLocalised(request.getParameter("username"),
                            Locale.forLanguageTag((String) request.getSession().getAttribute("lang")));
        } catch (BadCredentialsException e) {
            return "";
        }

        request.getSession().setAttribute("member", wantedUser);
        processPagination(request, wantedUser);
        return "/view/fragments/admin/member-data.jsp";
    }

    private void processPagination(HttpServletRequest request, UserDTO user) {
        String datePeriodLastDay = Optional.ofNullable(request.getParameter("d"))
                .orElse(LocalDate.now().toString());
        int pageSize = Integer.parseInt((String) request.getServletContext().getAttribute("page-size"));
        List<DailyRecordDTO> s = ContextHolder.dailyRecordService().findPaginated(
                user.getUserId(), datePeriodLastDay, pageSize,
                Locale.forLanguageTag((String) request.getSession().getAttribute("lang")));
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
