package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.Response;

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
public class ServerError implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Response.FORWARD.execute().response("/view/errors/500.jsp", request, response);
    }
}
