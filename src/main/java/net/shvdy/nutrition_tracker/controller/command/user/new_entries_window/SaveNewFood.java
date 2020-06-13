package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.model.entity.Food;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewFood implements ActionCommand {

    private static final Logger log = LogManager.getLogger(SaveNewFood.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> errors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.FOOD_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (errors.isEmpty()) {
            saveFoodAndUpdateCache(request);
            Response.OK_200.execute().response("", request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                Response.JSON.execute().response(ContextHolder
                        .objectMapper().writeValueAsString(errors), request, response);
            } catch (JsonProcessingException e) {
                log.error("SaveNewFood execute: objectMapper().writeValueAsString exception: " + e);
                Response.JSON.execute().response("", request, response);
            }
        }
    }

    private void saveFoodAndUpdateCache(HttpServletRequest request) {
        Food food = Food.builder().name(request.getParameter("newFoodName"))
                .calories(Integer.parseInt(request.getParameter("newFoodCalories")))
                .carbohydrates(Integer.parseInt(request.getParameter("newFoodCarbohydrates")))
                .fats(Integer.parseInt(request.getParameter("newFoodFats")))
                .proteins(Integer.parseInt(request.getParameter("newFoodProt")))
                .build();

        food.setFoodId(ContextHolder.foodService().saveForProfile(food,
                Long.parseLong(request.getParameter("profileId"))));

        List<Food> updatedFoodCache = (List<Food>) request.getSession().getAttribute("user.userFood");
        updatedFoodCache.add(food);
        request.getSession().setAttribute("user.userFood", updatedFoodCache);
    }
}
