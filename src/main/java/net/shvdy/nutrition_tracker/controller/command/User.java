package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

public class User implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return "/view/user.jsp";
	}
}
