package net.shvdy.nutrition_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.dao.impl.SQLRuntimeException;
import net.shvdy.nutrition_tracker.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

public class DreamfitServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(DreamfitServlet.class);

    public void init(ServletConfig servletConfig) {
        log.info("Servlet initialization started");

        PropertiesContainer.readProperties(this.getClass().getClassLoader());
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashMap<Long, HttpSession>());
        servletConfig.getServletContext().setAttribute("dairy_weekly-view-records-quantity",
                servletConfig.getInitParameter("dairy_weekly-view-records-quantity"));
        servletConfig.getServletContext().setAttribute("header-news-quantity",
                servletConfig.getInitParameter("header-news-quantity"));

        try {
            initAndInjectServicesIntoContextHolder();
        } catch (NamingException e) {
            log.error("Services initialization failed (DataSource lookup fail):\n" + e);
        }
        ContextHolder.injectObjectMapper(new ObjectMapper());

        CommandEnum.setPostEndpoints(new HashSet<>(Arrays.stream(CommandEnum.values())
                .filter(c -> c.getActionCommand().getClass().isAnnotationPresent(PostEndpoint.class))
                .map(CommandEnum::getPath)
                .collect(Collectors.toSet())));

        log.info("Servlet initialization ended");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLRuntimeException, NoSuchElementException {
        setLocalizedDate(request);
        processResponse(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLRuntimeException, NoSuchElementException {
        processResponse(request, response);
    }

    private void processResponse(HttpServletRequest request, HttpServletResponse response)
            throws SQLRuntimeException, NoSuchElementException, IOException, ServletException {
        CommandEnum.getByURI(request.getRequestURI()).execute(request, response);
    }

    private void setLocalizedDate(HttpServletRequest request) {
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
