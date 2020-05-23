package net.shvdy.nutrition_tracker.controller.command.user.add_entries_window;

import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class AddEntriesModalWindowCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		String recordDate = request.getParameter("recordDate");
		Long profileId = Long.valueOf(request.getParameter("profileId"));
		Long recordId = Long.valueOf(request.getParameter("recordId"));

		request.getSession().getServletContext().setAttribute(
				"newEntriesDTO",
				NewEntriesDTO.builder()
						.profileId(profileId)
						.recordId(recordId)
						.recordDate(recordDate)
						.entries(new ArrayList<>()).build());

		request.getSession().getServletContext().setAttribute("userFood", Collections.EMPTY_MAP);

		return "/view/user/add-new-entries-window/window.jsp";

	}
}