package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ChangeLang implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String lang = request.getParameter("lang");
		request.getSession().setAttribute("lang", Objects.requireNonNullElse(lang, "ru"));

		return "/redirect:home";
	}

}