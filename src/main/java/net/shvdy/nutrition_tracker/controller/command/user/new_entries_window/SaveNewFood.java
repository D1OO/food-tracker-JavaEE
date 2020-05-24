package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.controller.command.utils.FoodValidator;
import net.shvdy.nutrition_tracker.controller.exception.FoodValidationException;
import net.shvdy.nutrition_tracker.dto.FoodDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SaveNewFood implements ActionCommand {

	FoodValidator foodValidator = new FoodValidator();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			foodValidator.validate(request);
		} catch (FoodValidationException e) {
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return "/view/user/add-new-entries-window/window.jsp";
		}

		Long profileId = Long.parseLong(request.getParameter("profileId"));
		FoodDTO foodDTO = FoodDTO.builder().name(request.getParameter("newFoodName"))
				.calories(Integer.parseInt(request.getParameter("newFoodCalories")))
				.carbohydrates(Integer.parseInt(request.getParameter("newFoodCarbohydrates")))
				.fats(Integer.parseInt(request.getParameter("newFoodFats")))
				.proteins(Integer.parseInt(request.getParameter("newFoodProteins")))
				.build();
		Long generatedFoodId;

		try {
			generatedFoodId = CommandEnum.getFoodService().saveForProfile(foodDTO, profileId);
		} catch (SQLException e) {
			e.printStackTrace();
			return "/view/user/error.jsp";
		}

		foodDTO.setFoodId(generatedFoodId);

		List<FoodDTO> userFoodToUpdate = (List<FoodDTO>) request.getSession().getAttribute("user.userFood");
		userFoodToUpdate.add(foodDTO);

		request.getSession().setAttribute("user.userFood", userFoodToUpdate);
		return "/view/user/add-new-entries-window/window.jsp";

	}
}
