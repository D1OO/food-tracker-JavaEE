package net.shvdy.nutrition_tracker.controller.command.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.user.new_entries_window.RemovedEntry;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class CommandUtil {

    private static final Logger log = LogManager.getLogger(RemovedEntry.class);

    public static void setSessionInfo(HttpServletRequest request, UserDTO user) {
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("userRole", user.getRole());
        request.getSession().setAttribute("user.userId", user.getUserId());
        request.getSession().setAttribute("user.username", user.getUsername());
        request.getSession().setAttribute("user.userFood", user.getUserFood());
        request.getSession().setAttribute("user.dailyCaloriesNorm", user.getDailyCaloriesNorm());
        request.getSession().setAttribute("lifestyleEnum", UserProfile.Lifestyle.values());
    }

    public static boolean checkIsLoginNOTFresh(HttpServletRequest request, Long id) {
        return getLoggedUsers(request).containsKey(id);
    }

    public static void createNewSessionForUserId(HttpServletRequest request, Long id) {
        HashMap<Long, HttpSession> loggedUsers = getLoggedUsers(request);
        loggedUsers.put(id, request.getSession());
        request.getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

    public static void invalidateExistingSessionForUserId(HttpServletRequest request, Long id) {
        getLoggedUsers(request).get(id).invalidate();
    }

    public static Locale getCurrentLocale(HttpServletRequest request) {
        return Locale.forLanguageTag((String) request.getSession().getAttribute("lang"));
    }

    private static HashMap<Long, HttpSession> getLoggedUsers(HttpServletRequest request) {
        return (HashMap<Long, HttpSession>) request.getServletContext().getAttribute("loggedUsers");
    }

    public static boolean validateRespondErrorsIfAny(HttpServletRequest request, HttpServletResponse response,
                                                     Map<String, Properties> form) throws IOException, ServletException {

        Map<String, String> errors = Validator.validateFormAndReturnErrors(request, form);
        if (errors.isEmpty()) {
            return false;
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                Response.JSON.execute().response(ContextHolder.objectMapper().writeValueAsString(errors), request, response);
            } catch (JsonProcessingException e) {
                log.error("SaveNewArticle execute: objectMapper().writeValueAsString exception: " + e);
                Response.JSON.execute().response("", request, response);
            }
            return true;
        }
    }
}
