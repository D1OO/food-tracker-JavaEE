package net.shvdy.nutrition_tracker.controller.command;

import net.shvdy.nutrition_tracker.controller.ContextContainer;
import net.shvdy.nutrition_tracker.controller.command.utils.SecurityUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Feed implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("paginatedArticles", ContextContainer.getArticleService().findPaginated());
		return SecurityUtility.processAJAXSectionRequest("feed", "", request);
	}
}
