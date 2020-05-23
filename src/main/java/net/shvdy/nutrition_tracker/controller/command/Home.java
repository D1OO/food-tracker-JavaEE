package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Home implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "/view/index.jsp";
    }
}
