package net.shvdy.food_tracker.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shvdy.food_tracker.controller.command.ActionCommand;
import net.shvdy.food_tracker.controller.command.CommandEnum;
import net.shvdy.food_tracker.controller.command.LoginCommand;
import net.shvdy.food_tracker.controller.command.LogoutCommand;
import net.shvdy.food_tracker.model.service.UserService;

public class DreamfitServlet extends HttpServlet {

	public void init(ServletConfig servletConfig) {
		servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    request.getSession().getServletContext().setAttribute("localizedDate", LocalDate.now().toString());
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionCommand actionCommand = CommandEnum.getByURI(request.getRequestURI());
		String page = actionCommand.execute(request);

		if (page.contains("redirect:")) {
			response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
		} else {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}
}
