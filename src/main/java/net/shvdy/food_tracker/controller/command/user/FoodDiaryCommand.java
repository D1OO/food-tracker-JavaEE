package net.shvdy.food_tracker.controller.command.user;

import net.shvdy.food_tracker.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 20.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class FoodDiaryCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		request.getSession().getServletContext().setAttribute("data", new HashMap<>());
		request.getSession().getServletContext().setAttribute("dateName", "datename");
		request.getSession().getServletContext().setAttribute("lastDate", "lastdate");
		request.getSession().getServletContext().setAttribute("lastDate", "lastDate");
		request.getSession().getServletContext().setAttribute("profileFilled", false); //{rec.getTotalCalories() != -1
		request.getSession().getServletContext().setAttribute("recPercentage", 23); //{rec.getTotalCalories() != -1
		request.getSession().getServletContext().setAttribute("dailyCalsNorm", 23); //{rec.getTotalCalories() != -1
//		request.getSession().getServletContext().setAttribute("user", 5); //{rec.getTotalCalories() != -1
		request.getSession().getServletContext().setAttribute("userId", 22); //{rec.getTotalCalories() != -1

//		th:text="#{daily-cals-norm}">Your daily calories norm:</h4>
//                            <h4 class="logged-in-as" th:text="${rec.getPercentage()} + '%'"></h4>
//                            <h4 class="float-right" th:text="' (' + ${rec.getTotalCalories()} + '/' +


//		${#temporals.day(rec.getRecordDateAsLD()) +
//				' ' + #temporals.format(rec.getRecordDateAsLD(),'EE', currentLocale)
//		r
//			context.setAttribute("userFirstName", user.getFirstName());

		return "/view/user/food-diary.jsp";
	}
}
