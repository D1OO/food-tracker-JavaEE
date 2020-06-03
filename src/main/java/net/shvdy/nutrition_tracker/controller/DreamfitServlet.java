package net.shvdy.nutrition_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.exception.SQLRuntimeException;
import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

public class DreamfitServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) {
        ContextHolder.injectLogger(LogManager.getLogger(DreamfitServlet.class));
        ContextHolder.logger().info("Servlet initialization started");

        PropertiesContainer.readProperties(this.getClass().getClassLoader());
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashMap<Long, HttpSession>());
        servletConfig.getServletContext().setAttribute("page-size", servletConfig.getInitParameter("page-size"));

        try {
            initAndInjectServicesIntoContextHolder();
        } catch (NamingException e) {
            ContextHolder.logger().error("Services initialization failed (DataSource lookup fail):\n" + e);
        }

        ContextHolder.injectObjectMapper(new ObjectMapper());

        CommandEnum.setPostEndpoints(new HashSet<>(Arrays.stream(CommandEnum.values())
                .filter(c -> c.getActionCommand().getClass().isAnnotationPresent(PostEndpoint.class))
                .map(CommandEnum::getPath)
                .collect(Collectors.toSet())));

        ContextHolder.logger().info("Servlet initialization ended");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLRuntimeException, NoSuchElementException {
        setDateForLocale(request);
        if (CommandEnum.getPostEndpoints().contains(request.getRequestURI()))
            request.getRequestDispatcher("/view/not_found.jsp").forward(request, response);
        else
            processResponse(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLRuntimeException, NoSuchElementException {
        processResponse(request, response);
    }

    private void processResponse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLRuntimeException, NoSuchElementException {
        String commandResponse = CommandEnum.getByURI(request.getRequestURI()).execute(request, response);
        if ((commandResponse).startsWith("redirect:")) {
            response.sendRedirect(request.getContextPath() + commandResponse.replace("redirect:", ""));
        } else if (commandResponse.startsWith("json:")) {
            respondWithJSON(response, commandResponse.replace("json:", ""));
        } else if (commandResponse.startsWith("ok")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            request.getRequestDispatcher(commandResponse).forward(request, response);
        }
    }

    private void respondWithJSON(HttpServletResponse response, String JSONString) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(JSONString);
        out.flush();
    }

    private void setDateForLocale(HttpServletRequest request) {
        request.getServletContext().setAttribute("localizedDate",
                LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                        .withLocale(Locale.forLanguageTag((String) request.getSession().getAttribute("lang")))));
    }

    private void initAndInjectServicesIntoContextHolder() throws NamingException {
        ServiceFactory.injectDaoFactory(DAOFactory.getJDBCInstance());

        ContextHolder.injectUserService(ServiceFactory.userService());
        ContextHolder.injectDailyRecordService(ServiceFactory.dailyRecordService());
        ContextHolder.injectFoodService(ServiceFactory.foodService());
        ContextHolder.injectArticleService(ServiceFactory.articleService());
    }

}
