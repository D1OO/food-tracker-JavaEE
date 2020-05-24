package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		SecurityUtility.removeUserFromSession(request);
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login?logout";
	}
}
