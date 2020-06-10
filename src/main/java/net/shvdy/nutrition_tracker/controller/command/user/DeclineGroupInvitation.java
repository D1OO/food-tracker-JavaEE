package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.model.entity.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 09.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DeclineGroupInvitation implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ContextHolder.userService().declineGroupInvitation(((Set<Notification>) request.getSession()
                .getAttribute("notifications")).stream()
                .filter(n -> n.getSender().getFirstName().equals(request.getParameter("sender"))
                        && n.getDateTime().equals(request.getParameter("time")))
                .findFirst().get());
        return "ok";
    }
}
