package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.command.admin.AdminPage;
import net.shvdy.nutrition_tracker.controller.command.admin.NewArticleWindow;
import net.shvdy.nutrition_tracker.controller.command.admin.SaveNewArticle;
import net.shvdy.nutrition_tracker.controller.command.user.CompleteProfileToProceed;
import net.shvdy.nutrition_tracker.controller.command.user.FoodDiary;
import net.shvdy.nutrition_tracker.controller.command.user.UserPage;
import net.shvdy.nutrition_tracker.controller.command.user.new_entries_window.*;
import net.shvdy.nutrition_tracker.model.entity.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum CommandEnum {
    LOGIN_PAGE(
            new LoginPage(), "/login",
            Set.of(Role.GUEST)),
    LOGIN(
            new Login(), "/log-in",
            Set.of(Role.GUEST)),
    LOGIN_EXISTING_SESSION(
            new LoginExistingSession(), "/log-in-existing",
            Set.of(Role.GUEST)),
    LOGOUT(
            new Logout(), "/logout",
            Set.of(Role.ADMIN, Role.USER)),
    REGISTRATION_PAGE(
            new RegistrationPage(), "/registration",
            Set.of(Role.GUEST)),
    REGISTER(
            new Register(), "/sign-up",
            Set.of(Role.GUEST)),
    HOME_PAGE(
            new Home(), "/",
            Set.of(Role.ADMIN, Role.USER, Role.GUEST)),
    REDIRECT_HOME(
            new RedirectHome(), "/redirect:home",
            Set.of(Role.ADMIN, Role.USER, Role.GUEST)),
    ADMIN_PAGE(
            new AdminPage(), "/admin",
            Set.of(Role.ADMIN)),
    NEW_ARTICLE_MODAL(
            new NewArticleWindow(), "/new-article-window",
            Set.of(Role.ADMIN)),
    SAVE_NEW_ARTICLE_MODAL(
            new SaveNewArticle(), "/save-new-article",
            Set.of(Role.ADMIN)),
    FEED(
            new Feed(), "/feed",
            Set.of(Role.ADMIN, Role.USER)),
    READ_ARTICLE(
            new ReadArticle(), "/read-article",
            Set.of(Role.ADMIN, Role.USER)),
    CHANGE_LANG(
            new LanguageChange(), "/lang",
            Set.of(Role.ADMIN, Role.USER, Role.GUEST)),
    USER(
            new UserPage(), "/user",
            Set.of(Role.USER)),
    COMPLETE_PROFILE(
            new CompleteProfileToProceed(), "/complete",
            Set.of(Role.USER)),
    PROFILE(
            new Profile(), "/profile",
            Set.of(Role.USER, Role.ADMIN)),
    UPDATE_PROFILE(
            new UpdateProfile(), "/update-profile",
            Set.of(Role.ADMIN, Role.USER)),
    FOOD_DIARY_PAGE(
            new FoodDiary(), "/food-diary",
            Set.of(Role.USER)),
    FOOD_MODAL_WINDOW(
            new NewEntriesWindow(), "/adding-entries-modal-window",
            Set.of(Role.USER)),
    SEARCH_FOOD(
            new Search(), "/search",
            Set.of(Role.USER)),
    NEW_RECORD_ENTRY(
            new AddedEntry(), "/added-entry",
            Set.of(Role.USER)),
    REMOVED__RECORD_ENTRY(
            new RemovedEntry(), "/removed-entry",
            Set.of(Role.USER)),
    SAVE_NEW_ENTRIES(
            new SaveNewEntries(), "/save-new-entries",
            Set.of(Role.USER)),
    SAVE_NEW_FOOD(
            new SaveNewFood(), "/save-new-food",
            Set.of(Role.ADMIN, Role.USER)),
    NOT_FOUND(
            new NotFound404(), "",
            Set.of(Role.ADMIN, Role.USER, Role.GUEST));

    private final ActionCommand actionCommand;
    private final String path;
    private final Set<Role> permittedRoles;
    private static HashSet<String> postEndpoints;

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

    public static HashSet<String> getPostEndpoints() {
        return postEndpoints;
    }

    public static void setPostEndpoints(HashSet<String> postEndpoints) {
        CommandEnum.postEndpoints = postEndpoints;
    }

    public static ActionCommand getByURI(String path) {
        return Arrays.stream(CommandEnum.values())
                .filter(ac -> path.matches(ac.getPath()))
                .findFirst().orElse(CommandEnum.NOT_FOUND)
                .getActionCommand();
    }

    public static boolean checkIsPathForbidden(String path, Role role) {
        return Arrays.stream(CommandEnum.values())
                .filter(ac -> path.matches(ac.getPath()))
                .findFirst()
                .map(ac -> !ac.getPermittedRoles().contains(role))
                .orElse(true);
    }

}
