package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> formErrors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.USER_PROFILE_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (formErrors.isEmpty()) {
            UserProfileDTO updatedProfile= getProfile(request);
            ContextHolder.getUserService().updateProfile(updatedProfile);
            request.getSession().setAttribute("user", ContextHolder.getUserService()
                    .findByUsernameLocalised((String) request.getSession().getAttribute("user.username"),
                            Locale.forLanguageTag((String) request.getSession().getAttribute("lang"))));
            return "json:" + "{ \"url\": \"/profile?save-success\"}";
        } else {
            return "json:" + ContextHolder.getJacksonObjectMapper().writeValueAsString(formErrors);
        }
    }

    private UserProfileDTO getProfile(HttpServletRequest request) {
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
