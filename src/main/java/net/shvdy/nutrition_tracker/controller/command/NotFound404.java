package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 29.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class NotFound404 implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/view/not_found.jsp";
    }
}