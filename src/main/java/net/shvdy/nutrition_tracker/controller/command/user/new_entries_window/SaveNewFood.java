package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.dto.FoodDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SaveNewFood implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> errors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.FOOD_FORM_VALIDATION_DATA.getFormFieldsValidationData());
        if (errors.isEmpty()) {
            saveFoodAndUpdateCache(request);
            return "ok";
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "json:" + ContextHolder.getJacksonObjectMapper().writeValueAsString(errors);
        }
    }

    private void saveFoodAndUpdateCache(HttpServletRequest request) throws SQLException {
        FoodDTO foodDTO = FoodDTO.builder().name(request.getParameter("newFoodName"))
                .calories(Integer.parseInt(request.getParameter("newFoodCalories")))
                .carbohydrates(Integer.parseInt(request.getParameter("newFoodCarbohydrates")))
                .fats(Integer.parseInt(request.getParameter("newFoodFats")))
                .proteins(Integer.parseInt(request.getParameter("newFoodProt")))
                .build();

        foodDTO.setFoodId(ContextHolder.getFoodService().saveForProfile(foodDTO,
                Long.parseLong(request.getParameter("profileId"))));

        List<FoodDTO> updatedFoodCache = (List<FoodDTO>) request.getSession().getAttribute("user.userFood");
        updatedFoodCache.add(foodDTO);
        request.getSession().setAttribute("user.userFood", updatedFoodCache);
    }
}
