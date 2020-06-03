package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;
import net.shvdy.nutrition_tracker.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 31.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
//@PostEndpoint
public class LoginExistingSession implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = (Long) request.getSession().getAttribute("user.userId");

        SecurityUtility.invalidateExistingSessionForUserId(request, id);
        SecurityUtility.createNewSessionForUserId(request, id);
        SecurityUtility.setSessionInfo(request, (UserDTO) request.getSession().getAttribute("user"));

        return CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
    }

}
