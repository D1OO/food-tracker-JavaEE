package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPage implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Response.FORWARD.execute().response("/view/login.jsp", request, response);
    }
}
