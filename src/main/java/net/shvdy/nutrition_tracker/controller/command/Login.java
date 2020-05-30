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
        LoginDTO loginDto = buildLoginDto(request);
        UserDTO user;

        try {
            user = ContextHolder.getUserService()
                    .findByLoginDTO(loginDto, Locale.forLanguageTag((String) request.getSession().getAttribute("lang")));
        } catch (UserNotFoundException | InvalidPasswordException |
                SQLException | NullPointerException e) {
            e.printStackTrace();
            ContextHolder.getLogger().warn("User log in error: " + e.getMessage());
            return "redirect:/login?error";
        }

        if (SecurityUtility.checkIsLoginNOTFresh(request, user.getUserId())) {
            ContextHolder.getLogger().warn("Session duplication try ID=: " + user.getUserId());
            return "redirect:/login?session-exists";
        }

        SecurityUtility.setSessionInfo(request, user);

        return CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
    }

    private LoginDTO buildLoginDto(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return new LoginDTO(username, password);
    }
}
