package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.controller.ContextHolder;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;
import net.shvdy.nutrition_tracker.model.entity.Article;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleEntityMapper {

    public List<ArticleDTO> entityListToDTO(List<Article> articleList, Locale locale) {
        return articleList.stream().map(x-> entityToDTO(x, locale)).collect(Collectors.toList());
    }

    public ArticleDTO entityToDTO(Article article, Locale locale) {
        return ArticleDTO.builder().articleId(article.getArticleId())
                .authorName(article.getAuthorFirstName() + article.getAuthorLastName())
                .date(readDateString(article.getDate(), locale))
                .titleLocalisation(article.getTitleLocalisation())
                .textLocalisation(article.getTextLocalisation())
                .base64Image(getBase64String(article.getImage()))
                .build();
    }

    private String getBase64String(InputStream inputStream) {
        try {
            return readImageString(inputStream);
        } catch (IOException e) {
            ContextHolder.logger().warn("Image could not be read");
            return "";
        }
    }

    private String readDateString(String string, Locale locale) {
        LocalDateTime date = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return date.format(DateTimeFormatter.ofPattern("d MMM kk:mm", locale));
    }

    private String readImageString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        inputStream.close();
        outputStream.close();
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
