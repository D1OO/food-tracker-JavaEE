package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.dto.ArticleDTO;
import net.shvdy.nutrition_tracker.model.entity.Article;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger log = LogManager.getLogger(ArticleEntityMapper.class);

    public static List<ArticleDTO> entityListToDTO(List<Article> articleList, Locale locale) {
        return articleList.stream().map(x -> entityToDTO(x, locale)).collect(Collectors.toList());
    }

    public static ArticleDTO entityToDTO(Article article, Locale locale) {
        return ArticleDTO.builder().articleId(article.getArticleId())
                .authorName(article.getAuthorFirstName() + " " + article.getAuthorLastName())
                .date(readDateString(article.getDate(), locale))
                .titleLocalisation(article.getTitleLocalisation())
                .textLocalisation(article.getTextLocalisation())
                .base64Image(readImageString(article.getImageStream()))
                .build();
    }

    private static String readDateString(String string, Locale locale) {
        LocalDateTime date = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return date.format(DateTimeFormatter.ofPattern("d MMM kk:mm", locale));
    }

    private static String readImageString(InputStream imageStream) {
        byte[] buffer = new byte[4096];
        int bytesRead;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            while ((bytesRead = imageStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            imageStream.close();
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            log.warn("Image could not be read");
            return "";
        }
    }
}
