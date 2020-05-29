package net.shvdy.nutrition_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.model.service.*;
import org.apache.logging.log4j.LogManager;

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.stream.Collectors;

public class DreamfitServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) {
        ContextHolder.injectLogger(LogManager.getLogger(DreamfitServlet.class));
        ContextHolder.getLogger().info("Servlet initialization started");

        PropertiesContainer.readProperties(this.getClass().getClassLoader());
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<Long>());
        servletConfig.getServletContext().setAttribute("page-size", servletConfig.getInitParameter("page-size"));

        initAndInjectServicesIntoContext();

        CommandEnum.setPostEndpoints(new HashSet<>(Arrays.stream(CommandEnum.values())
                .filter(c -> c.getActionCommand().getClass().isAnnotationPresent(PostEndpoint.class))
                .map(CommandEnum::getPath)
                .collect(Collectors.toSet())));

        ContextHolder.getLogger().info("Servlet initialization ended");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setDateForLocale(request);
        if (checkGETRequestToOnlyPOSTEndpoint(request))
            request.getRequestDispatcher("/view/not_found.jsp").forward(request, response);
        else
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
        } else if (path.startsWith("ok")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    private String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            return CommandEnum.getByURI(request.getRequestURI()).execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            ContextHolder.getLogger().error("Command threw an exception: " + e.getMessage());
            request.getSession().setAttribute("error-message", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return CommandEnum.SERVER_ERROR.getPath();
        }
    }

    private boolean checkGETRequestToOnlyPOSTEndpoint(HttpServletRequest request) {
        return CommandEnum.getPostEndpoints().contains(request.getRequestURI());
    }

    private void respondWithJSON(HttpServletResponse response, String JSONString) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(JSONString);
        out.flush();
    }

    private void initAndInjectServicesIntoContext() {
        UserService userService = null;
        DailyRecordService dailyRecordService = null;
        FoodService foodService = null;
        ArticleService articleService = null;

        try {
            userService = ServiceFactory.userService();
        } catch (IOException | NamingException e) {
            ContextHolder.getLogger().error(e.getMessage() + "User Service initialization failed");
        }
        try {
            dailyRecordService = ServiceFactory.dailyRecordService();
        } catch (IOException | NamingException e) {
            ContextHolder.getLogger().error(e.getMessage() + "DailyRecord Service initialization failed");
        }
        try {
            foodService = ServiceFactory.foodService();
        } catch (IOException | NamingException e) {
            ContextHolder.getLogger().error(e.getMessage() + "Food Service initialization failed");
        }
        try {
            articleService = ServiceFactory.articleService();
        } catch (IOException | NamingException e) {
            ContextHolder.getLogger().error(e.getMessage() + "Article Service initialization failed");
        }

        ContextHolder.injectServices(userService, dailyRecordService, foodService, articleService, new ObjectMapper());
    }

    private void setDateForLocale(HttpServletRequest request) {
        Locale locale = Locale.forLanguageTag((String) request.getSession().getAttribute("lang"));
        request.getServletContext().setAttribute("localizedDate",
                LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale)));
    }
}
