package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewEntries implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NewEntriesDTO newEntries = NewEntriesDTOReader.read(request);
		if (NewEntriesDTOReader.validateHasErrors(newEntries)) {
			request.getSession().getServletContext().setAttribute("newEntriesDTO", newEntries);
			return "/view/user/add-new-entries-window/new-entries-list.jsp";
		} else {
            ContextHolder.dailyRecordService().saveNewEntries(newEntries);
            return "ok";
        }
	}

}
