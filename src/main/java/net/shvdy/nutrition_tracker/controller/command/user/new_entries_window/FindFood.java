package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 08.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FindFood implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Optional.ofNullable(request.getParameter("name")).ifPresent(name ->
                request.getSession().setAttribute("foodList", name.trim().isEmpty() ?
                        request.getSession().getAttribute("user.userFood") :
                        ContextHolder.foodService().findByNameStart(name)));
        Response.FORWARD.execute()
                .response("/view/fragments/user/add-new-entries-window/search-results.jsp", request, response);
    }
}
