package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextContainer;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;
import net.shvdy.nutrition_tracker.dto.LoginDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.exception.InvalidPasswordException;
import net.shvdy.nutrition_tracker.model.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Login implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDTO loginDto = buildLoginDto(request);
		UserDTO user;

		try {
			user = ContextContainer.getUserService().findByLoginDTO(loginDto);
		} catch (UserNotFoundException | InvalidPasswordException |
				SQLException | NullPointerException e) {
			e.printStackTrace();
			return "redirect:/login?error";
		}

		if (SecurityUtility.checkIsLoginNOTFresh(request, user.getUserId())) {
			return "redirect:/login?error=session-exists";
		}

		SecurityUtility.setSessionInfo(request, user);

		return CommandEnum.REDIRECT_HOME.getActionCommand().execute(request, response);
	}

	private LoginDTO buildLoginDto(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		return new LoginDTO(username, password);
	}
}
