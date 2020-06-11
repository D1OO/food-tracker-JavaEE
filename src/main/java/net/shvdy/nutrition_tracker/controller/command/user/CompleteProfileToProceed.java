package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteProfileToProceed implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Response.FORWARD.execute().response("/view/fragments/user/complete-profile-to-proceed.jsp", request, response);
    }
}
