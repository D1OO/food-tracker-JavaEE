package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.CommandUtil;
import net.shvdy.nutrition_tracker.controller.command.utils.DTOBuilder;
import net.shvdy.nutrition_tracker.model.entity.Food;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewFood implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (!CommandUtil.validateRespondErrorsIfAny(request, response,
                PropertiesContainer.FormValidationConfig.FOOD.get())) {

            saveFoodAndUpdateCache(request);
            Response.OK_200.execute().response("", request, response);
        }
    }

    private void saveFoodAndUpdateCache(HttpServletRequest request) {
        Food newFood = DTOBuilder.createFood(request);
        newFood.setFoodId(ContextHolder.foodService().saveForProfile(newFood,
                Long.parseLong(request.getParameter("profileId"))));

        List<Food> updatedFoodCache = (List<Food>) request.getSession().getAttribute("user.userFood");
        updatedFoodCache.add(newFood);
        request.getSession().setAttribute("user.userFood", updatedFoodCache);
    }
}
