package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.model.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectHome implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = (Role) request.getSession().getAttribute("userRole");
        if (role.equals(Role.USER)) {
            Response.REDIRECT.execute().response("/user", request, response);
        } else if (role.equals(Role.ADMIN)) {
            Response.REDIRECT.execute().response("/admin", request, response);
        } else {
            Response.REDIRECT.execute().response("/login", request, response);
        }
    }
}
