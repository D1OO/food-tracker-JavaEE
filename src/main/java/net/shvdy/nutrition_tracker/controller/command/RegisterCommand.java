package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegisterCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		try {
			CommandEnum.getUserService().save(buildUser(request));
		} catch (SQLException | NullPointerException e) {
			System.out.println(e.getMessage());

			e.printStackTrace();
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
