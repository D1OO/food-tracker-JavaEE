package net.shvdy.nutrition_tracker.controller;

import net.shvdy.nutrition_tracker.PropertiesReader;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.model.service.*;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Locale;

public class DreamfitServlet extends HttpServlet {

	public void init(ServletConfig servletConfig) {
		servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<Long>());
		servletConfig.getServletContext().setAttribute("page-size", servletConfig.getInitParameter("page-size"));

		PropertiesReader.readProperties(this.getClass().getClassLoader());
		loadAndInjectServices();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Locale locale = Locale.forLanguageTag((String) request.getSession().getAttribute("lang"));
		request.getSession().getServletContext().setAttribute("localizedDate",
				LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale)));
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
		String page = actionCommand.execute(request, response);

		if (page.contains("redirect:")) {
			response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
		} else {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	private void loadAndInjectServices() {
		UserService userService = null;
		DailyRecordService dailyRecordService = null;
		FoodService foodService = null;
		ArticleService articleService = null;

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
		try {
			articleService = ServiceFactory.articleService();
		} catch (IOException | NamingException e) {
			System.err.print(e.getMessage());
			e.printStackTrace();
		}

		CommandEnum.injectServices(userService, dailyRecordService, foodService, articleService);
	}
}
