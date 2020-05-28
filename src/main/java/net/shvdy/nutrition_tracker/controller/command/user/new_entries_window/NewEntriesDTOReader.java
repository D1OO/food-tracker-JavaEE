package net.shvdy.nutrition_tracker.controller.command.user.new_entries_window;

import com.fasterxml.jackson.core.type.TypeReference;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.dto.DailyRecordEntryDTO;
import net.shvdy.nutrition_tracker.dto.NewEntriesDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 26.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class NewEntriesDTOReader {

	static NewEntriesDTO read(HttpServletRequest request) throws IOException {
		NewEntriesDTO dto = readDTO(request);
		dto.setEntries(readList(request));
		return dto;
	}

	static NewEntriesDTO readAddNew(HttpServletRequest request, DailyRecordEntryDTO newEntry) throws IOException {
		NewEntriesDTO dto = readDTO(request);
		List<DailyRecordEntryDTO> list = readList(request);
		list.add(newEntry);
		dto.setEntries(list);
		return dto;
	}

	static NewEntriesDTO readDTO(HttpServletRequest request) throws IOException {
		String newEntriesDTOJSON = request.getParameter("newEntriesDTOJSON");
		return ContextHolder.getJacksonObjectMapper().readValue(newEntriesDTOJSON, NewEntriesDTO.class);
	}

	static List<DailyRecordEntryDTO> readList(HttpServletRequest request) throws IOException {
		String newEntriesListJSON = request.getParameter("newEntriesJSON");
		return ContextHolder.getJacksonObjectMapper()
				.readValue(newEntriesListJSON, new TypeReference<ArrayList<DailyRecordEntryDTO>>() {});
	}
}
