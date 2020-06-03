package net.shvdy.nutrition_tracker.controller.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@PostEndpoint
public class Register implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> formErrors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.USER_SIGN_UP_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (formErrors.isEmpty()) {
            ContextHolder.userService().save(getUser(request));
            return "json:" + "{ \"url\": \"/login?signedup\"}";
        } else {
            try {
                return "json:" + ContextHolder.objectMapper().writeValueAsString(formErrors);
            } catch (JsonProcessingException e) {
                ContextHolder.logger().error("Register execute: objectMapper().writeValueAsString exception: " + e);
                return "json:";
            }
        }
    }

    private User getUser(HttpServletRequest request) {
        return User.builder()
                .username(request.getParameter("username"))
                .password(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()))
                .userProfile(UserProfile.builder()
                        .firstNameEN(request.getParameter("firstNameEN"))
                        .firstNameRU(request.getParameter("firstNameRU"))
                        .lastName(request.getParameter("lastName")).build())
                .role(Role.USER)
                .build();
    }
}
