package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.ArticleDTO;
import net.shvdy.nutrition_tracker.model.dao.ArticleDAO;
import net.shvdy.nutrition_tracker.model.entity.Article;
import net.shvdy.nutrition_tracker.model.service.mapper.ArticleEntityMapper;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleService {

    private final ArticleDAO articleDAO;
    private final ArticleEntityMapper articleEntityMapper;

    public ArticleService(ArticleDAO articleDAO, ArticleEntityMapper articleEntityMapper) {
        this.articleDAO = articleDAO;
        this.articleEntityMapper = articleEntityMapper;
    }

    public void save(Article article) {
        articleDAO.save(article);
    }

    public List<ArticleDTO> findPaginatedForLocale(Locale locale) {
        return articleEntityMapper.entityListToDTO(articleDAO.findPaginatedLocalised(locale), locale);
    }

    public ArticleDTO findByIDForLocale(int articleId, Locale locale) {
        return articleEntityMapper.entityToDTO(articleDAO.findByIDLocalised(articleId, locale)
                .orElseThrow(NoSuchElementException::new), locale);
    }

    public List<ArticleDTO> findRandomForLocale(Locale locale) {
        List<ArticleDTO> randomNews = findPaginatedForLocale(locale).subList(0, 3);
        Collections.shuffle(randomNews);
        return randomNews;
    }
}