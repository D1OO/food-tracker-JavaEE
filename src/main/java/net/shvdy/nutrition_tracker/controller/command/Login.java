package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.utils.CommandUtil;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.exception.BadCredentialsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@PostEndpoint
public class Login implements ActionCommand {

    private static final Logger log = LogManager.getLogger(Login.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO user;
        try {
            user = ContextHolder.userService().findByUsernameVerifyPassword(request.getParameter("username"),
                    request.getParameter("password"));
        } catch (BadCredentialsException e) {
            log.warn("Bad login try: " + e.getMessage());
            Response.REDIRECT.execute().response("/login?error", request, response);
            return;
        }

        if (CommandUtil.checkIsLoginNOTFresh(request, user.getUserId())) {
            log.warn("Session duplication try: " + user.getUsername());
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("user.userId", user.getUserId());
            Response.REDIRECT.execute().response("/login?session-exists", request, response);
        } else {
            CommandUtil.createNewSessionForUserId(request, user.getUserId());
            CommandUtil.setSessionInfo(request, user);
            CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
        }
    }

}
