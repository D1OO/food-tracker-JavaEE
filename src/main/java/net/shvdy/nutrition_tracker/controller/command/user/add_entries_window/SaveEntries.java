package net.shvdy.nutrition_tracker.controller.command.user.add_entries_window;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class SaveEntries implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		String newEntriesDTOJSON = request.getParameter("newEntriesDTOJSON");
		String newEntriesListJSON = request.getParameter("newEntriesJSON");

		ArrayList<DailyRecordEntryDTO> newEntriesList;
		NewEntriesDTO newEntriesDTO;
		try {
			newEntriesDTO = new ObjectMapper().readValue(newEntriesDTOJSON, NewEntriesDTO.class);
			newEntriesList = new ObjectMapper().readValue(newEntriesListJSON,
					new TypeReference<ArrayList<DailyRecordEntryDTO>>() {});
			newEntriesDTO.setEntries(newEntriesList);

			CommandEnum.getDailyRecordService().saveNewEntries(newEntriesDTO);

			return ("redirect:/user");
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			return "/view/user/error.jsp";
		}
	}
}
