package net.shvdy.nutrition_tracker.controller.command.utils;

import net.shvdy.nutrition_tracker.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class SecurityUtility {
	public static void setSessionInfo(HttpServletRequest request, UserDTO user) {
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("user.role", user.getRole());
		request.getSession().setAttribute("user.userId", user.getUserId());
		request.getSession().setAttribute("user.userFood", user.getUserFood());
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

	public static String processSectionRequest(String section, HttpServletRequest request) {
		if (request.getParameter("AJAXrequest") != null)
			return String.format("/view/fragments/%s.jsp", section);
		else {
			request.getSession().setAttribute("containerSection", section);
			return "/view/" + request.getSession().getAttribute("user.role").toString().toLowerCase() + ".jsp";
		}
	}
}
