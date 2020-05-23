package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
	static void setUserInfo(HttpServletRequest request, UserDTO user) {
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		context.setAttribute("userId", user.getId());
		context.setAttribute("userFirstName", user.getFirstName());
		context.setAttribute("userLastName", user.getLastName());
		session.setAttribute("role", user.getRole());
		System.out.println(user.getUserFoodDTO().get(0).getName() + "QQQQQ");
		session.setAttribute("userFood", user.getUserFoodDTO());
	}

	static boolean checkIsLoginNotFresh(HttpServletRequest request, Long id) {
		HashSet<Long> loggedUsers = getLoggedUsers(request);

		if (loggedUsers.add(id)) {
			request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
			return false;
		}
		return true;
	}

	static void removeUserFromSession(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		HashSet<Long> loggedUsers = getLoggedUsers(request);
		loggedUsers.remove(user.getId());
		request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
	}

	private static HashSet<Long> getLoggedUsers(HttpServletRequest request) {
		HashSet<Long> loggedUsers = (HashSet<Long>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");
		return loggedUsers;
	}
}
