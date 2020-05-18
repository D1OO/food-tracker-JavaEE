package net.shvdy.food_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    String execute(HttpServletRequest request);
}
