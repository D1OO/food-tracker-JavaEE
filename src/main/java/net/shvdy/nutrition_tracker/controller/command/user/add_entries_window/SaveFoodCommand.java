package net.shvdy.nutrition_tracker.controller.command.user.add_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.FoodDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.Food;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SaveFoodCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

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

		List<FoodDTO> updatedUserFood = (List<FoodDTO>) request.getSession().getAttribute("userFood");
		updatedUserFood.add(foodDTO);

		request.getSession().setAttribute("userFood", updatedUserFood);
		return "/view/user/add-new-entries-window/window.jsp";

	}
}
