package net.shvdy.nutrition_tracker.controller.command.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.dao.impl.SQLRuntimeException;
import net.shvdy.nutrition_tracker.model.entity.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 09.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SendGroupInvitation implements ActionCommand {

    private static final Logger log = LogManager.getLogger(SendGroupInvitation.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> errors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.GROUP_INVITE_VALIDATION_DATA.getFormFieldsValidationData());

        if (errors.isEmpty()) {
            try {
                ContextHolder.userService().sendGroupInvitation(Notification.builder()
                        .sender(UserDTO.builder()
                                .id((Long) request.getSession().getAttribute("user.userId")).build())
                        .receiver((UserDTO.builder()
                                .username(request.getParameter("receiver_email")).build()))
                        .dateTime(LocalDateTime.now().toString())
                        .message("Mentoring invite").build());
                Response.OK_200.execute().response("", request, response);
            } catch (SQLRuntimeException ignored) {
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                Response.JSON.execute().response(ContextHolder.objectMapper().writeValueAsString(errors), request, response);
            } catch (JsonProcessingException e) {
                log.error("Register execute: objectMapper().writeValueAsString exception: " + e);
                Response.JSON.execute().response("", request, response);
            }
        }
    }
}
