package net.shvdy.nutrition_tracker.controller.command.utils;

import net.shvdy.nutrition_tracker.controller.command.admin.SaveNewArticle;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * 14.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class DTOBuilder {

    private static final Logger log = LogManager.getLogger(SaveNewArticle.class);

    public static UserProfileDTO createProfileDTO(HttpServletRequest request) {
        return UserProfileDTO.builder()
                .profileId((Long) request.getSession().getAttribute("user.userId"))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .lifestyle((UserProfile.Lifestyle.valueOf(request.getParameter("lifestyle"))))
                .age(Integer.parseInt(request.getParameter("age")))
                .weight(Integer.parseInt(request.getParameter("weight")))
                .height(Integer.parseInt(request.getParameter("height"))).build();
    }

    public static User createUser(HttpServletRequest request) {
        return User.builder()
                .username(request.getParameter("username"))
                .password(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()))
                .userProfile(UserProfile.builder()
                        .firstName(request.getParameter("firstName"))
                        .lastName(request.getParameter("lastName")).build())
                .role(Role.USER).build();
    }

    public static Notification createNotification(HttpServletRequest request) {
        return Notification.builder()
                .sender(UserDTO.builder()
                        .id((Long) request.getSession().getAttribute("user.userId")).build())
                .receiver((UserDTO.builder()
                        .username(request.getParameter("receiver_email")).build()))
                .dateTime(LocalDateTime.now().toString())
                .message("Mentoring invite").build();
    }

    public static Food createFood(HttpServletRequest request) {
        return Food.builder().name(request.getParameter("newFoodName"))
                .calories(Integer.parseInt(request.getParameter("newFoodCalories")))
                .carbohydrates(Integer.parseInt(request.getParameter("newFoodCarbohydrates")))
                .fats(Integer.parseInt(request.getParameter("newFoodFats")))
                .proteins(Integer.parseInt(request.getParameter("newFoodProt"))).build();
    }

    public static Article createArticle(HttpServletRequest request) {
        return Article.builder()
                .authorId(Long.parseLong(request.getParameter("authorId")))
                .titleEN(request.getParameter("titleEN")).titleRU(request.getParameter("titleRU"))
                .date(LocalDateTime.now().toString())
                .textEN(request.getParameter("textEN")).textRU(request.getParameter("textRU"))
                .imageStream(readImageBytes(request)).build();
    }

    private static InputStream readImageBytes(HttpServletRequest request) {
        try {
            return request.getPart("image").getInputStream();
        } catch (IOException | ServletException e) {
            log.error("Image loading error:\n" + e);
            return null;
        }
    }
}
