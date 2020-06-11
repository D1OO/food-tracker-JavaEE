package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.Notification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 09.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DeclineGroupInvitation implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ContextHolder.userService().declineGroupInvitation(Notification.builder()
                .sender(UserDTO.builder().username(request.getParameter("sender")).build())
                .receiver(UserDTO.builder().username((String) request.getSession().getAttribute("user.username")).build())
                .dateTime(request.getParameter("time")).build());
        Response.OK_200.execute().response("", request, response);
    }
}
