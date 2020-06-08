package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class RemovedEntry implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().getServletContext().setAttribute("newEntriesDTO", NewEntriesDTOReader.read(request));
        } catch (IOException e) {
            ContextHolder.logger().error("RemovedEntry execute: : reading from JSON exception: " + e);
        }
        return "/view/fragments/user/add-new-entries-window/new-entries-list.jsp";
    }
}
