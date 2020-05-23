package net.shvdy.nutrition_tracker.controller.command.user.add_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.FoodDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 23.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SaveFoodCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		FoodDTO foodDTO = FoodDTO.builder().name(request.getParameter("newFoodName"))
				.calories(Integer.parseInt(request.getParameter("newFoodCalories")))
				.carbohydrates(Integer.parseInt(request.getParameter("newFoodCarbohydrates")))
				.fats(Integer.parseInt(request.getParameter("newFoodFats")))
				.prot(Integer.parseInt(request.getParameter("newFoodProteins")))
				.build();

		try {
			CommandEnum.getFoodService().save(foodDTO);
			return "/view/user.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			return "/view/user/error.jsp";
		}

	}
}
