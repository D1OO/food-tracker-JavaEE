package net.shvdy.nutrition_tracker.controller.command.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.Response;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.model.entity.Article;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewArticle implements ActionCommand {

    private static final Logger log = LogManager.getLogger(SaveNewArticle.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Map<String, String> errors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.ARTICLE_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (errors.isEmpty()) {
            ContextHolder.articleService().save(createArticle(request));
            Response.REDIRECT.execute().response("/feed", request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                Response.JSON.execute().response(ContextHolder
                        .objectMapper().writeValueAsString(errors), request, response);
            } catch (JsonProcessingException e) {
                log.error("SaveNewArticle execute: objectMapper().writeValueAsString exception: " + e);
                Response.JSON.execute().response("", request, response);
            }
        }
    }

    private Article createArticle(HttpServletRequest request) {
        return Article.builder()
                .authorId(Long.parseLong(request.getParameter("authorId")))
                .titleEN(request.getParameter("titleEN"))
                .titleRU(request.getParameter("titleRU"))
                .date(LocalDateTime.now().toString())
                .textEN(request.getParameter("textEN"))
                .textRU(request.getParameter("textRU"))
                .imageStream(readImageBytes(request))
                .build();
    }

    private InputStream readImageBytes(HttpServletRequest request) {
        try {
            return request.getPart("image").getInputStream();
        } catch (IOException | ServletException e) {
            log.error("Image loading error:\n" + e);
        }
        return null;
    }
}
