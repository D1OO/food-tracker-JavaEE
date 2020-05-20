package net.shvdy.food_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

import net.shvdy.food_tracker.model.entity.Role;

public class RedirectHomeCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Role role = (Role) request.getSession().getAttribute("role");

        if (role.equals(Role.ADMIN)) {
            return "redirect:/admin";
        } else if (role.equals(Role.USER)) {
            return "redirect:/food-diary";
        }
        return "redirect:/login";
    }
}
