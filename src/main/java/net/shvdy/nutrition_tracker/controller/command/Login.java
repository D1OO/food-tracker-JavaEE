package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.exception.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@PostEndpoint
public class Login implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDTO user;
        try {
            user = ContextHolder.userService().findByUsernameVerifyPassword(request.getParameter("username"),
                    request.getParameter("password"));
        } catch (BadCredentialsException e) {
            ContextHolder.logger().warn("Bad login try: " + e.getMessage());
            return "redirect:/login?error";
        }

        if (SecurityUtility.checkIsLoginNOTFresh(request, user.getUserId())) {
            ContextHolder.logger().warn("Session duplication try ID=: " + user.getUserId());
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("user.userId", user.getUserId());
            return "redirect:/login?session-exists";
        }

        SecurityUtility.createNewSessionForUserId(request, user.getUserId());
        SecurityUtility.setSessionInfo(request, user);

        return CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
    }

}
