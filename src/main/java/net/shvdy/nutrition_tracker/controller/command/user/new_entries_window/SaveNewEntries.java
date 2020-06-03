package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewEntries implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		NewEntriesDTO newEntries;
		try {
			newEntries = NewEntriesDTOReader.read(request);
		} catch (IOException e) {
			ContextHolder.logger().error("SaveNewEntries execute: : reading from JSON exception: " + e);
			throw new RuntimeException(e);
		}
		if (NewEntriesDTOReader.validateHasErrors(newEntries)) {
			request.getSession().getServletContext().setAttribute("newEntriesDTO", newEntries);
			return "/view/user/add-new-entries-window/new-entries-list.jsp";
		} else {
			ContextHolder.dailyRecordService().saveNewEntries(newEntries);
			return "ok";
		}
	}

}
