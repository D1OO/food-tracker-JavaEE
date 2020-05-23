package net.shvdy.nutrition_tracker.controller.command.user.add_entries_window;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 22.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */


//@PostMapping(value = "/added-entry")
//public String updateAddingEntriesWindow(@RequestParam String foodDTOJSON, @RequestParam String foodName,
//@RequestParam String newEntriesDTOJSON, Model model) throws IOException {
//		NewEntriesContainerDTO newEntriesDTO = new ObjectMapper().readValue(newEntriesDTOJSON, NewEntriesContainerDTO.class);
//		newEntriesDTO.getEntries().add(DailyRecordEntryDTO.builder().foodName(foodName).foodDTOJSON(foodDTOJSON).build());
//		model.addAttribute("newEntriesDTO", newEntriesDTO);
//		return "fragments/user-page/add-entries-and-create-food :: new-entries-list";
//		}
public class AddedEntry implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {

		String foodDTOJSON = request.getParameter("foodDTOJSON");
		String foodName = request.getParameter("foodName");
		String newEntriesDTOJSON = request.getParameter("newEntriesDTOJSON");
		String newEntriesListJSON = request.getParameter("newEntriesJSON");

		ArrayList<DailyRecordEntryDTO> newEntriesList;
		NewEntriesDTO newEntriesDTO;
		try {
			newEntriesDTO = new ObjectMapper().readValue(newEntriesDTOJSON, NewEntriesDTO.class);
			newEntriesList = new ObjectMapper().readValue(newEntriesListJSON,
					new TypeReference<ArrayList<DailyRecordEntryDTO>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return "/view/user/new-entries-list.jsp";
		}

		newEntriesList.add(DailyRecordEntryDTO.builder().foodName(foodName).foodDTOJSON(foodDTOJSON).build());
		newEntriesDTO.setEntries(newEntriesList);
		request.getSession().getServletContext().setAttribute("newEntriesDTO", newEntriesDTO);

	return "/view/user/add-new-entries-window/new-entries-list.jsp";
	}
}
