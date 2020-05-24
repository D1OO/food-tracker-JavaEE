package net.shvdy.nutrition_tracker.controller.command.utils;

import net.shvdy.nutrition_tracker.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class SecurityUtility {
	public static void setSessionInfo(HttpServletRequest request, UserDTO user) {
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("user.role", user.getRole());
		System.out.println(user.getRole().name() + "nnnn");
		request.getSession().setAttribute("user.userId", user.getUserId());
		request.getSession().setAttribute("user.dailyCaloriesNorm", user.getDailyCaloriesNorm());
	}

	public static boolean checkIsLoginNOTFresh(HttpServletRequest request, Long id) {
		HashSet<Long> loggedUsers = getLoggedUsers(request);
		if (loggedUsers.add(id)) {
			request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
			return false;
		}
		return true;
	}

	public static void removeUserFromSession(HttpServletRequest request) {
		HashSet<Long> loggedUsers = getLoggedUsers(request);
		loggedUsers.remove(request.getSession().getAttribute("user.userId"));
		request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
	}

	private static HashSet<Long> getLoggedUsers(HttpServletRequest request) {
		return (HashSet<Long>) request.getServletContext().getAttribute("loggedUsers");
	}
}
