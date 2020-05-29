package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;
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
            ContextHolder.getUserService().save(getUser(request));
        } catch (SQLException | NullPointerException e) {
            ContextHolder.getLogger().error("User saving exception: " + e.getMessage());
            return "redirect:/registration?error";
        }
        return "redirect:/login?signed-up";
    }

    private User getUser(HttpServletRequest request) {
        return User.builder()
                .username(request.getParameter("username"))
                .password(SecurityUtility.bCryptHash(request.getParameter("password")))
                .userProfile(UserProfile.builder()
                        .firstNameEN(request.getParameter("firstNameEN"))
                        .firstNameRU(request.getParameter("firstNameRU"))
                        .lastName(request.getParameter("lastName")).build())
                .role(Role.USER)
                .build();
    }
}
