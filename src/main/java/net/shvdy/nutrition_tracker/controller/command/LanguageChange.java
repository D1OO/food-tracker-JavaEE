package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class LanguageChange implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("lang",
                Objects.requireNonNullElse(request.getParameter("lang"), "en"));

        request.getSession().setAttribute("user", ContextHolder.userService()
                .findByUsernameLocalised((String) request.getSession().getAttribute("user.username"),
                        Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));

        return "/redirect:home";
    }

}