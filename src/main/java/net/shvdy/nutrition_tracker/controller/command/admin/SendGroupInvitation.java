package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 09.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SendGroupInvitation implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ContextHolder.userService().sendGroupInvitation(Notification.builder()
                .sender(UserDTO.builder()
                        .id((Long) request.getSession().getAttribute("user.userId")).build())
                .receiver((UserDTO.builder()
                        .username(request.getParameter("receiver_email")).build()))
                .dateTime(LocalDateTime.now().toString()).build());
        return "ok";
    }
}
