package net.shvdy.nutrition_tracker.controller.command.user;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class UserPage implements ActionCommand {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("headerNews",
                ContextHolder.articleService().findRandomForLocale(Locale
                        .forLanguageTag((String) request.getSession().getAttribute("lang"))));

        return "/view/user.jsp";
    }
}
