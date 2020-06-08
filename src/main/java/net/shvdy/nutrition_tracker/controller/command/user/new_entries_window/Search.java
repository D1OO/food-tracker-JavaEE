package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 08.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Search implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        request.getSession().setAttribute("foodList", name.trim().isEmpty() ?
                request.getSession().getAttribute("user.userFood") :
                ContextHolder.foodService().foodSearch(name));

        return "/view/fragments/user/add-new-entries-window/search-results.jsp";
    }
}
