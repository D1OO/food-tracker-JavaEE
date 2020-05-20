package net.shvdy.food_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

import net.shvdy.food_tracker.model.entity.Role;
import net.shvdy.food_tracker.model.entity.User;

import java.util.Optional;

public class LoginPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "/view/login.jsp";
    }
}
