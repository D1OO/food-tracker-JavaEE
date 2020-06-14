package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.CommandUtil;
import net.shvdy.nutrition_tracker.controller.command.utils.DTOBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 09.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SendGroupInvitation implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (!CommandUtil.validateRespondErrorsIfAny(request, response,
                PropertiesContainer.FormValidationConfig.USER_PROFILE.get())) {

            ContextHolder.userService().sendGroupInvitation(DTOBuilder.createNotification(request));
            Response.OK_200.execute().response("", request, response);
        }
    }
}
