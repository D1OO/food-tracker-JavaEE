package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.dto.ArticleDTO;
import net.shvdy.nutrition_tracker.model.dao.ArticleDAO;
import net.shvdy.nutrition_tracker.model.entity.Article;
import net.shvdy.nutrition_tracker.model.service.mapper.ArticleEntityMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public void save(Article article) throws SQLException {
		articleDAO.save(article);
	}

	public List<ArticleDTO> findPaginated() throws SQLException {
		return articleEntityMapper.entityListToDTO(articleDAO.findPaginated());
	}

	public ArticleDTO findByID(int articleId) throws SQLException {
		return articleEntityMapper.entityToDTO(articleDAO.findByID(articleId));
	}

	public List<ArticleDTO> findRandom() throws SQLException {
		List<ArticleDTO> randomNews = findPaginated();
		Collections.shuffle(randomNews);
		return randomNews.subList(0, 3);
	}
}