package net.shvdy.food_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

import net.shvdy.food_tracker.model.entity.User;
import net.shvdy.food_tracker.model.entity.UserProfile;
import net.shvdy.food_tracker.model.entity.Role;
import net.shvdy.food_tracker.model.service.UserService;

import java.sql.SQLException;

public class RegisterCommand implements ActionCommand {

	private final UserService userService;

	public RegisterCommand(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute(HttpServletRequest request) {
		try {
			userService.save(buildUser(request));
		} catch (SQLException e) {
			return "redirect:/registration?error";
		}
		return "redirect:/login?signed-up";
	}

	private User buildUser(HttpServletRequest request) {
		return User.builder()
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.userProfile(UserProfile.builder()
						.firstName(request.getParameter("firstName"))
						.lastName(request.getParameter("lastName")).build())
				.role(Role.USER)
				.build();
	}
}
