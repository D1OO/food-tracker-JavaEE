package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.util.CommandUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserPage implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().setAttribute("headerNews", ContextHolder.articleService()
                .findRandomForLocale(Integer.parseInt((String) request.getServletContext()
                                .getAttribute("header-news-quantity")),
                        CommandUtil.getCurrentLocale(request)));
        Response.FORWARD.execute().response("/view/user.jsp", request, response);
    }
}
