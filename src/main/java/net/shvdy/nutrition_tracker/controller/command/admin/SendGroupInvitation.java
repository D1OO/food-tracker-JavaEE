package net.shvdy.nutrition_tracker.controller.command.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.exception.SQLRuntimeException;
import net.shvdy.nutrition_tracker.model.entity.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
                return "ok";
            } catch (SQLRuntimeException ignored) {
            }
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        try {
            return "json:" + ContextHolder.objectMapper().writeValueAsString(errors);
        } catch (JsonProcessingException e) {
            ContextHolder.logger().error("Register execute: objectMapper().writeValueAsString exception: " + e);
        }
        return "json:";
    }
}
