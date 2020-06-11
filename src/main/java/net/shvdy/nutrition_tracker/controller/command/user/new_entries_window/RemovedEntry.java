package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
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

    private static final Logger log = LogManager.getLogger(RemovedEntry.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.getSession().getServletContext().setAttribute("newEntriesDTO", NewEntriesDTOReader.read(request));
        } catch (IOException e) {
            log.error("RemovedEntry execute: : reading from JSON exception: " + e);
        }
        Response.FORWARD.execute()
                .response("/view/fragments/user/add-new-entries-window/new-entries-list.jsp", request, response);
    }
}
