package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Register implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ContextHolder.getUserService().save(buildUser(request));
		} catch (SQLException | NullPointerException e) {
			ContextHolder.getLogger().error("User saving exception: " + e.getMessage());
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
