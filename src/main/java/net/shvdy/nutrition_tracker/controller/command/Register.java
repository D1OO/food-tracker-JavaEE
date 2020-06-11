package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@PostEndpoint
public class Register implements ActionCommand {

    private static final Logger log = LogManager.getLogger(Register.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> formErrors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.USER_SIGN_UP_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (formErrors.isEmpty()) {
            ContextHolder.userService().save(getUser(request));
            Response.JSON.execute().response("{ \"url\": \"/login?signedup\"}", request, response);
        } else {
            try {
                Response.JSON.execute().response(ContextHolder
                        .objectMapper().writeValueAsString(formErrors), request, response);
            } catch (IOException | ServletException e) {
                log.error("Register execute: objectMapper().writeValueAsString exception: " + e);
                Response.JSON.execute().response("", request, response);
            }
        }
    }

    private User getUser(HttpServletRequest request) {
        return User.builder()
                .username(request.getParameter("username"))
                .password(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()))
                .userProfile(UserProfile.builder()
                        .firstName(request.getParameter("firstName"))
                        .lastName(request.getParameter("lastName")).build())
                .role(Role.USER)
                .build();
    }
}
