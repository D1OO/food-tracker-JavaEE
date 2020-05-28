package net.shvdy.nutrition_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.model.service.*;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Locale;

public class DreamfitServlet extends HttpServlet {

	public void init(ServletConfig servletConfig) {
		servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<Long>());
		servletConfig.getServletContext().setAttribute("page-size", servletConfig.getInitParameter("page-size"));
		PropertiesContainer.readProperties(this.getClass().getClassLoader());
		initAndInjectServices();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setDateForLocale(request);
		processResponse(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processResponse(request, response);
	}

	private void processResponse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = executeCommand(request, response);
		if ((path).startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + path.replace("redirect:", ""));
		} else if (path.startsWith("json:")) {
			respondWithJSON(response, path.replace("json:", ""));
		} else {
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	private String executeCommand(HttpServletRequest request, HttpServletResponse response) {
		try {
			return CommandEnum.getByURI(request.getRequestURI()).execute(request, response);
		} catch (Exception e) {
			e.printStackTrace(); //  logger soon
			request.getSession().setAttribute("error-message", e.getMessage());
			return CommandEnum.SERVER_ERROR.getPath();
		}
	}

	private void respondWithJSON(HttpServletResponse response, String JSONString) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(JSONString);
		out.flush();
	}

	private void initAndInjectServices() {
		UserService userService = null;
		DailyRecordService dailyRecordService = null;
		FoodService foodService = null;
		ArticleService articleService = null;

		try {
			userService = ServiceFactory.userService();
		} catch (IOException | NamingException e) {
			e.printStackTrace();
		}
		try {
			dailyRecordService = ServiceFactory.dailyRecordService();
		} catch (IOException | NamingException e) {
			e.printStackTrace();
		}
		try {
			foodService = ServiceFactory.foodService();
		} catch (IOException | NamingException e) {
			e.printStackTrace();
		}
		try {
			articleService = ServiceFactory.articleService();
		} catch (IOException | NamingException e) {
			e.printStackTrace();
		}
		CommandEnum.injectServices(userService, dailyRecordService, foodService, articleService, new ObjectMapper());
	}

	private void setDateForLocale(HttpServletRequest request) {
		Locale locale = Locale.forLanguageTag((String) request.getSession().getAttribute("lang"));
		request.getServletContext().setAttribute("localizedDate",
				LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale)));
	}
}
