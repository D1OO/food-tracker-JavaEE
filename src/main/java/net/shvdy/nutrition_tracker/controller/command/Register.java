package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.util.CommandUtil;
import net.shvdy.nutrition_tracker.controller.command.util.DTOBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@PostEndpoint
public class Register implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (CommandUtil.validateRespondErrorsIfAny(request, response,
                PropertiesContainer.FormValidationConfig.USER_SIGN_UP.get())) {

            ContextHolder.userService().save(DTOBuilder.createUser(request));
            Response.OK_200.execute().response("", request, response);
        }
    }
}
