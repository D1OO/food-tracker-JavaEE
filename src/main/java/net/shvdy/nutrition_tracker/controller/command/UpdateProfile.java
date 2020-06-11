package net.shvdy.nutrition_tracker.controller.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 30.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class UpdateProfile implements ActionCommand {

    private static final Logger log = LogManager.getLogger(UpdateProfile.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Map<String, String> formErrors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.USER_PROFILE_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (formErrors.isEmpty()) {
            UserProfileDTO updatedProfile = getProfileDTO(request);
            ContextHolder.userService().updateProfile(updatedProfile);

            request.getSession().setAttribute("user", ContextHolder.userService()
                    .findByUsername((String) request.getSession().getAttribute("user.username")));
            Response.JSON.execute().response("{ \"url\": \"/profile?save=success\"}", request, response);
        } else {
            try {
                Response.JSON.execute().response(ContextHolder.objectMapper()
                        .writeValueAsString(formErrors), request, response);
            } catch (JsonProcessingException e) {
                log.error("UpdateProfile execute: objectMapper().writeValueAsString exception: " + e);
                Response.JSON.execute().response("", request, response);
            }
        }
    }

    private UserProfileDTO getProfileDTO(HttpServletRequest request) {
        return UserProfileDTO.builder()
                .profileId((Long) request.getSession().getAttribute("user.userId"))
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .lifestyle((UserProfile.Lifestyle.valueOf(request.getParameter("lifestyle"))))
                .age(Integer.parseInt(request.getParameter("age")))
                .weight(Integer.parseInt(request.getParameter("weight")))
                .height(Integer.parseInt(request.getParameter("height")))
                .build();
    }
}
