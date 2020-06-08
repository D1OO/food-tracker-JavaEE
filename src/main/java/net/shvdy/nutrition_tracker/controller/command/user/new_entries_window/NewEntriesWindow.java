package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class NewEntriesWindow implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        createNewEntriesDTO(request);
        request.getSession().setAttribute("foodList", request.getSession().getAttribute("user.userFood"));
        return "/view/fragments/user/add-new-entries-window/window.jsp";
    }

    private void createNewEntriesDTO(HttpServletRequest request) {
        request.getSession().getServletContext().setAttribute("newEntriesDTO",
                NewEntriesDTO.builder()
                        .profileId(Long.valueOf(request.getParameter("profileId")))
                        .recordId(request.getParameter("recordId")
                                .isEmpty() ? null : Long.parseLong(request.getParameter("recordId")))
                        .recordDate(request.getParameter("recordDate"))
                        .currentDailyCaloriesNorm((Integer) request.getSession().getAttribute("user.dailyCaloriesNorm"))
                        .entries(new ArrayList<>()).build());
    }
}