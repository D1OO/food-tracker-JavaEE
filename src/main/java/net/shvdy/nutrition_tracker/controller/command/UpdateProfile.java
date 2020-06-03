package net.shvdy.nutrition_tracker.controller.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;
import net.shvdy.nutrition_tracker.model.exception.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * 30.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class UpdateProfile implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> formErrors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.USER_PROFILE_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (formErrors.isEmpty()) {
            UserProfileDTO updatedProfile = getProfileDTO(request);
            ContextHolder.userService().updateProfile(updatedProfile);
            try {
                request.getSession().setAttribute("user", ContextHolder.userService()
                        .findByUsernameLocalised((String) request.getSession().getAttribute("user.username"),
                                Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));
            } catch (BadCredentialsException e) {
                ContextHolder.logger().error("UpdateProfile execute: Failed to retrieve user from DB: " + e);
            }
            return "json:" + "{ \"url\": \"/profile?save-success\"}";
        } else {
            try {
                return "json:" + ContextHolder.objectMapper().writeValueAsString(formErrors);
            } catch (JsonProcessingException e) {
                ContextHolder.logger().error("UpdateProfile execute: objectMapper().writeValueAsString exception: " + e);
                return "json:";
            }
        }
    }

    private UserProfileDTO getProfileDTO(HttpServletRequest request) {
        return UserProfileDTO.builder()
                .profileId((Long) request.getSession().getAttribute("user.userId"))
                .firstNameEN(request.getParameter("firstNameEN"))
                .firstNameRU(request.getParameter("firstNameRU"))
                .lastName(request.getParameter("lastName"))
                .lifestyle((UserProfile.Lifestyle.valueOf(request.getParameter("lifestyle"))))
                .age(Integer.parseInt(request.getParameter("age")))
                .weight(Integer.parseInt(request.getParameter("weight")))
                .height(Integer.parseInt(request.getParameter("height")))
                .build();
    }
}
