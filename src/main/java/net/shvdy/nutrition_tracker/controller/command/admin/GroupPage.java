package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * 08.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class GroupPage implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<User> l = ContextHolder.userService()
                .findGroup((String) request.getSession().getAttribute("user.username"),
                        Locale.forLanguageTag((String) request.getSession().getAttribute("lang")));

        request.getSession().setAttribute("group", l);
        return "/view/fragments/admin/group.jsp";
    }
}
