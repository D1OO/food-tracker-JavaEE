package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;
import net.shvdy.nutrition_tracker.dto.LoginDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.exception.InvalidPasswordException;
import net.shvdy.nutrition_tracker.model.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Locale;

@PostEndpoint
public class Login implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginDTO loginDTO = new LoginDTO( request.getParameter("username"), request.getParameter("password"));
        UserDTO user;

        try {
            user = ContextHolder.getUserService()
                    .findByLoginDTO(loginDTO, Locale.forLanguageTag((String) request.getSession().getAttribute("lang")));
        } catch (UserNotFoundException | InvalidPasswordException | SQLException | NullPointerException e) {
            ContextHolder.getLogger().warn("User log in error: " + e.getMessage());
            return "redirect:/login?error";
        }

        if (SecurityUtility.checkIsLoginNOTFresh(request, user.getUserId())) {
            ContextHolder.getLogger().warn("Session duplication try ID=: " + user.getUserId());
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("user.userId", user.getUserId());
            return "redirect:/login?session-exists";
        }

        SecurityUtility.createNewSessionForUserId(request, user.getUserId());
        SecurityUtility.setSessionInfo(request, user);

        return CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
    }

}
