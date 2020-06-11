package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class UserPage implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().setAttribute("headerNews",
                ContextHolder.articleService()
                        .findRandomForLocale(Integer.parseInt((String) request.getServletContext()
                                        .getAttribute("header-news-quantity")),
                                Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));
        Response.FORWARD.execute().response("/view/user.jsp", request, response);
    }
}
