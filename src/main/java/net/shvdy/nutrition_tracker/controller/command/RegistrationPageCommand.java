package net.shvdy.nutrition_tracker.controller.command;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.shvdy.nutrition_tracker.model.entity.Role;

public class RegistrationPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("roles", Set.of(Role.ADMIN, Role.USER));
        return "/view/registration.jsp";
    }
}