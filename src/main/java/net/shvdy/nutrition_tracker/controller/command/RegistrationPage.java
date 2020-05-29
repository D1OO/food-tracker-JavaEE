package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class RegistrationPage implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("roles", Set.of(Role.ADMIN, Role.USER));
        return "/view/registration.jsp";
    }
}
