package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class SecurityUtility {
	static void setUserInfo(HttpServletRequest request, UserDTO user) {
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		context.setAttribute("userId", user.getId());
		context.setAttribute("userFirstName", user.getFirstName());
		context.setAttribute("userLastName", user.getLastName());
		session.setAttribute("role", user.getRole());
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
		long userId = (long) request.getServletContext().getAttribute("userId");
		HashSet<Long> loggedUsers = getLoggedUsers(request);
		loggedUsers.remove(userId);
		request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
	}


	private static HashSet<Long> getLoggedUsers(HttpServletRequest request) {
		return (HashSet<Long>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");
	}
}
