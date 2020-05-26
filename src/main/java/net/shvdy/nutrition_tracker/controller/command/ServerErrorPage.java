package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ServerErrorPage implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/view/server-error.jsp";
	}
}
