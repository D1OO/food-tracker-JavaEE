package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.command.user.CompleteProfileToProceedCommand;
import net.shvdy.nutrition_tracker.controller.command.user.FoodDiaryCommand;
import net.shvdy.nutrition_tracker.controller.command.user.ProfileCommand;
import net.shvdy.nutrition_tracker.model.entity.Role;
import net.shvdy.nutrition_tracker.model.service.DailyRecordService;
import net.shvdy.nutrition_tracker.model.service.UserService;

import java.util.Arrays;
import java.util.Set;

public enum CommandEnum {
	LOGIN_PAGE(
			new LoginPageCommand(), "/login",
			Set.of(Role.GUEST)),
	LOGIN(
			new LoginCommand(), "/log-in",
			Set.of(Role.GUEST)),
	LOGOUT(
			new LogoutCommand(), "/logout",
			Set.of(Role.ADMIN, Role.USER)),
	REGISTRATION_PAGE(
			new RegistrationPageCommand(), "/registration",
			Set.of(Role.GUEST)),
	REGISTER(
			new RegisterCommand(), "/sign-up",
			Set.of(Role.GUEST)),
	HOME_PAGE(
			new HomeCommand(), "/",
			Set.of(Role.ADMIN, Role.USER, Role.GUEST)),
	REDIRECT_HOME(
			new RedirectHomeCommand(), "/redirect:home",
			Set.of(Role.ADMIN, Role.USER, Role.GUEST)),
	USER(
			new UserCommand(), "/user",
			Set.of(Role.USER)),
	COMPLETE_PROFILE(
			new CompleteProfileToProceedCommand(), "/complete",
			Set.of(Role.USER)),
	PROFILE(
			new ProfileCommand(), "/profile",
			Set.of(Role.USER)
	),
	FOOD_DIARY_PAGE(
			new FoodDiaryCommand(), "/food-diary",
			Set.of(Role.ADMIN, Role.USER));

	private final ActionCommand actionCommand;
	private final String path;
	private final Set<Role> permittedRoles;
	private static UserService userService;
	private static DailyRecordService dailyRecordService;

	CommandEnum(ActionCommand actionCommand, String path, Set<Role> permittedRoles) {
		this.actionCommand = actionCommand;
		this.path = path;
		this.permittedRoles = permittedRoles;
	}

	public String getPath() {
		return this.path;
	}

	public ActionCommand getActionCommand() {
		return this.actionCommand;
	}

	public Set<Role> getPermittedRoles() {
		return this.permittedRoles;
	}

	public static UserService getUserService() {
		return userService;
	}

	public static DailyRecordService getDailyRecordService() {
		return dailyRecordService;
	}

	public static void injectServices(UserService userService, DailyRecordService dailyRecordService) {
		CommandEnum.userService = userService;
		CommandEnum.dailyRecordService = dailyRecordService;
	}

	public static ActionCommand getByURI(String path) {
		return Arrays.stream(CommandEnum.values())
				.filter(ac -> path.matches(ac.getPath()))
				.findFirst().orElse(CommandEnum.REDIRECT_HOME)
				.getActionCommand();
	}

	public static boolean checkIsPathPermitted(String path, Role role) {
		return Arrays.stream(CommandEnum.values())
				.filter(ac -> path.matches(ac.getPath()))
				.findFirst()
				.map(ac -> ac.getPermittedRoles().contains(role))
				.orElse(false);
	}
}
