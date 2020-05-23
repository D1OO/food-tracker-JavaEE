package net.shvdy.nutrition_tracker.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.removeUserFromSession(request);
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login?logout";

//        CommandUtility.removeUserFromSession(request);
//        final HttpSession session = request.getSession();
//        ResourceBundle bundle = ResourceBundle.getBundle("messages",
//                new Locale(Optional.ofNullable((String) session.getAttribute("lang"))
//                        .orElse("en")));
//        session.invalidate();
//        CommandUtility.setUserRole(request, Role.GUEST, "Guest");
//        request.setAttribute("logout", bundle.getString("message.logged.out"));
//        return REDIRECT_LOGIN;
    }
}
