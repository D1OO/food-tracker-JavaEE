package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LoginPage implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "/view/login.jsp";
    }
}
