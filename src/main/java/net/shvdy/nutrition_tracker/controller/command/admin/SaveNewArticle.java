package net.shvdy.nutrition_tracker.controller.command.admin;

import net.shvdy.nutrition_tracker.PropertiesContainer;
import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.controller.command.ActionCommand;
import net.shvdy.nutrition_tracker.controller.command.PostEndpoint;
import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import net.shvdy.nutrition_tracker.model.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@PostEndpoint
public class SaveNewArticle implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> errors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.ARTICLE_FORM_VALIDATION_DATA.getFormFieldsValidationData());

        if (errors.isEmpty()) {
            ContextHolder.articleService().save(createArticle(request));
            return "/view/fragments/feed.jsp";
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "json:" + ContextHolder.objectMapper().writeValueAsString(errors);
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
                .image(readImage(request).orElse(InputStream.nullInputStream()))
                .build();
    }

    private Optional<InputStream> readImage(HttpServletRequest request) {
        try {
            return Optional.of(request.getPart("image").getInputStream());
        } catch (IOException | ServletException e) {
            ContextHolder.logger().error("Image loading error:\n" + e);
        }
        return Optional.empty();
    }
}
