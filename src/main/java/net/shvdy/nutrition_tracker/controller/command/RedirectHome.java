package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectHome implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Role role = (Role) request.getSession().getAttribute("user.role");
		if (role.equals(Role.ADMIN)) {
			return "redirect:/admin";
		} else if (role.equals(Role.USER)) {
			return "redirect:/user";
		}
		return "redirect:/login";
	}
}
