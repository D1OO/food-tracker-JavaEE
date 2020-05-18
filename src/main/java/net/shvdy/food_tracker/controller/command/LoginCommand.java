package net.shvdy.food_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;

import net.shvdy.food_tracker.dto.LoginDTO;
import net.shvdy.food_tracker.dto.UserDTO;
import net.shvdy.food_tracker.model.exception.InvalidPasswordException;
import net.shvdy.food_tracker.model.exception.UserNotFoundException;
import net.shvdy.food_tracker.model.service.UserService;

import java.sql.SQLException;

public class LoginCommand implements ActionCommand {

	private final UserService userService;

	public LoginCommand(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute(HttpServletRequest request) {
		LoginDTO loginDto = buildLoginDto(request);
		UserDTO user;

		try {
			user = userService.findByLoginDTO(loginDto);
		} catch (UserNotFoundException | InvalidPasswordException | SQLException e) {
			return "redirect:/login?error";
		}

		if (CommandUtility.checkIsLoginNotFresh(request, user.getId())) {
			return "redirect:/login?error=session-exists";
		}

		CommandUtility.setUserInfo(request, user);

		return "redirect:/redirect-home";
	}

	private LoginDTO buildLoginDto(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		return new LoginDTO(username, password);
	}
}
