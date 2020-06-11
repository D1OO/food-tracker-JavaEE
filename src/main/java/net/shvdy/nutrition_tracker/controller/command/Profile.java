package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Profile implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("notifications",
                ContextHolder.userService().findNotifications((UserDTO) request.getSession().getAttribute(("user"))));
        Response.FORWARD.execute().response("/view/fragments/profile.jsp", request, response);
    }
}
