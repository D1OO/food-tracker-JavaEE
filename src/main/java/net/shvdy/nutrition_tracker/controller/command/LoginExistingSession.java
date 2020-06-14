package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.command.utils.CommandUtil;
import net.shvdy.nutrition_tracker.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 31.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
//@PostEndpoint
public class LoginExistingSession implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = (Long) request.getSession().getAttribute("user.userId");

        CommandUtil.invalidateExistingSessionForUserId(request, id);
        CommandUtil.createNewSessionForUserId(request, id);
        CommandUtil.setSessionInfo(request, (UserDTO) request.getSession().getAttribute("user"));

        CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
    }

}
