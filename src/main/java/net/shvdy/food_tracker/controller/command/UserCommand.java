package net.shvdy.food_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

public class UserCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return "/view/user.jsp";
	}
}
