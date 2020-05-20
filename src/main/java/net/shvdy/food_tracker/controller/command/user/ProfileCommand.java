package net.shvdy.food_tracker.controller.command.user;

import net.shvdy.food_tracker.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ProfileCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return "/view/user/profile.jsp";
	}
}
