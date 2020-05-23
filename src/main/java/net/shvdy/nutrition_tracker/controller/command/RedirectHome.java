package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

import net.shvdy.nutrition_tracker.model.entity.Role;

public class RedirectHome implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Role role = (Role) request.getSession().getAttribute("role");

        if (role.equals(Role.ADMIN)) {
            return "redirect:/admin";
        } else if (role.equals(Role.USER)) {
            return "redirect:user";
        }
        return "redirect:/login";
    }
}
