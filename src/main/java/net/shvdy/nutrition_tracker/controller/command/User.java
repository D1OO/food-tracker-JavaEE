package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class User implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "/view/user.jsp";
	}
}
