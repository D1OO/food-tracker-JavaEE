package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.util.CommandUtil;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Diary implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (checkProfileCompletion(request)) {
            processPagination(request);
            Response.FORWARD.execute().response("/view/fragments/user/diary.jsp", request, response);
        } else {
            Response.FORWARD.execute().response("/view/fragments/user/complete-profile-to-proceed.jsp", request, response);
        }
    }

    private void processPagination(HttpServletRequest request) {
        String datePeriodLastDay = Optional.ofNullable(request.getParameter("d"))
                .orElse(LocalDate.now().toString());
        int pageSize = Integer.parseInt((String) request.getServletContext()
                .getAttribute("dairy_weekly-view-records-quantity"));

        request.getSession().setAttribute("paginatedWeeklyRecords",
                ContextHolder.dailyRecordService()
                        .findPaginated((Long) request.getSession().getAttribute("user.userId"),
                                datePeriodLastDay, pageSize, CommandUtil.getCurrentLocale(request)));

        request.getSession().setAttribute("prevWeekDay",
                datePeriodLastDay.equals(LocalDate.now().toString()) ? null :
                        LocalDate.parse(datePeriodLastDay).plusDays(pageSize).toString());

        request.getSession().setAttribute("nextWeekDay",
                LocalDate.parse(datePeriodLastDay).minusDays(pageSize).toString());
    }

    private boolean checkProfileCompletion(HttpServletRequest request) {
        UserProfileDTO profileDTO = ((UserDTO) request.getSession().getAttribute("user")).getUserProfileDTO();
        return List.of(profileDTO.getHeight(), profileDTO.getAge(), profileDTO.getWeight())
                .stream().noneMatch(i -> i == 0);
    }
}
