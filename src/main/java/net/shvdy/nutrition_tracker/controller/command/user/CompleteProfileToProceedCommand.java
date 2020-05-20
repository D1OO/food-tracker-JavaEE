package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class CompleteProfileToProceedCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return "/view/user/complete-profile-to-proceed.jsp";
	}
}
