package net.shvdy.nutrition_tracker.controller;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.model.service.DailyRecordService;
import net.shvdy.nutrition_tracker.model.service.FoodService;
import net.shvdy.nutrition_tracker.model.service.ServiceFactory;
import net.shvdy.nutrition_tracker.model.service.UserService;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

public class DreamfitServlet extends HttpServlet {

	public void init(ServletConfig servletConfig) {

		UserService userService = null;
		DailyRecordService dailyRecordService = null;
		FoodService foodService = null;

		try {
			userService = ServiceFactory.userService();
		} catch (IOException | NamingException e) {
			System.err.print(e.getMessage());
			e.printStackTrace();
		}
		try {
			dailyRecordService = ServiceFactory.dailyRecordService();
		} catch (IOException | NamingException e) {
			System.err.print(e.getMessage());
			e.printStackTrace();
		}
		try {
			foodService = ServiceFactory.foodService();
		} catch (IOException | NamingException e) {
			System.err.print(e.getMessage());
			e.printStackTrace();
		}

		CommandEnum.injectServices(userService, dailyRecordService, foodService);

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
