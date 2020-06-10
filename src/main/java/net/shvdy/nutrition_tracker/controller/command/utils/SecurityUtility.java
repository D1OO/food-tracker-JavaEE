package net.shvdy.nutrition_tracker.controller.command.utils;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
        return getLoggedUsers(request).containsKey(id);
    }

    public static void createNewSessionForUserId(HttpServletRequest request, Long id){
        HashMap<Long, HttpSession> loggedUsers = getLoggedUsers(request);
        loggedUsers.put(id, request.getSession());
        request.getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

    public static void invalidateExistingSessionForUserId(HttpServletRequest request, Long id){
        getLoggedUsers(request).get(id).invalidate();
    }

    private static HashMap<Long, HttpSession> getLoggedUsers(HttpServletRequest request) {
        return (HashMap<Long, HttpSession>) request.getServletContext().getAttribute("loggedUsers");
    }

}
