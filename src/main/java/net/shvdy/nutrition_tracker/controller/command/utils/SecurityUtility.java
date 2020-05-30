package net.shvdy.nutrition_tracker.controller.command.utils;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;

public class SecurityUtility {
    public static void setSessionInfo(HttpServletRequest request, UserDTO user) {
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("user.userProfile", user.getUserProfileDTO());
        request.getSession().setAttribute("user.role", user.getRole());
        request.getSession().setAttribute("user.userId", user.getUserId());
        request.getSession().setAttribute("user.username", user.getUsername());
        request.getSession().setAttribute("user.userFood", user.getUserFood());
        request.getSession().setAttribute("user.dailyCaloriesNorm", user.getDailyCaloriesNorm());
        request.getSession().setAttribute("lifestyleEnum", UserProfile.Lifestyle.values());
    }

    public static boolean checkIsLoginNOTFresh(HttpServletRequest request, Long id) {
        HashSet<Long> loggedUsers = getLoggedUsers(request);
        if (loggedUsers.add(id)) {
            request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
            return false;
        }
        return true;
    }

    public static void processLogout(HttpServletRequest request) {
        HashSet<Long> loggedUsers = getLoggedUsers(request);
        loggedUsers.remove(request.getSession().getAttribute("user.userId"));
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        request.getSession().invalidate();
    }

    private static HashSet<Long> getLoggedUsers(HttpServletRequest request) {
        return (HashSet<Long>) request.getServletContext().getAttribute("loggedUsers");
    }

    public static String bCryptHash(String data) {
        return BCrypt.hashpw(data, BCrypt.gensalt(10));
    }

    public static String processAJAXSectionRequest(String section, String params, HttpServletRequest request) {
        if (Optional.ofNullable(request.getParameter("AJAXrequest")).isPresent())
            return String.format("/view/fragments/%s.jsp", section);
        else {
            request.getSession().setAttribute("sectionToFetchWithAJAX", section + params);
            return "/view/" + request.getSession().getAttribute("user.role").toString().toLowerCase() + ".jsp";
        }
    }

}
