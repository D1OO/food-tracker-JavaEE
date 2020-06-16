package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.util.CommandUtil;
import net.shvdy.nutrition_tracker.controller.command.util.DTOBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewArticle implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (CommandUtil.validateRespondErrorsIfAny(request, response,
                PropertiesContainer.FormValidationConfig.ARTICLE.get())) {

            ContextHolder.articleService().save(DTOBuilder.createArticle(request));
            Response.REDIRECT.execute().response("/feed", request, response);
        }
    }
}
