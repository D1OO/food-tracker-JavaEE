package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
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

	private static final Logger log = LogManager.getLogger(SaveNewEntries.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		NewEntriesDTO newEntries;
		try {
			newEntries = NewEntriesDTOReader.read(request);
		} catch (IOException e) {
			log.error("SaveNewEntries execute: : reading from JSON exception: " + e);
			throw new RuntimeException(e);
		}
		if (NewEntriesDTOReader.validateHasErrors(newEntries)) {
			request.getSession().getServletContext().setAttribute("newEntriesDTO", newEntries);
			Response.FORWARD.execute()
					.response("/view/fragments/user/add-new-entries-window/new-entries-list.jsp", request, response);
		} else {
			ContextHolder.dailyRecordService().saveNewEntries(newEntries);
			Response.OK_200.execute().response("", request, response);
		}
	}

}
