package net.shvdy.nutrition_tracker.model.entity;

import java.io.InputStream;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Article {

	private Long articleId;
	private String title;
	private String author;
	private String date;
	private String text;
	private InputStream image;

	public Article(Long articleId, String title, String author, String date, String text, InputStream image) {
		this.articleId = articleId;
		this.title = title;
		this.author = author;
		this.date = date;
		this.text = text;
		this.image = image;
	}

	public static ArticleBuilder builder() {
		return new ArticleBuilder();
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}


	public static final class ArticleBuilder {
		private Long articleId;
		private String title;
		private String author;
		private String date;
		private String text;
		private InputStream image;

		private ArticleBuilder() {
		}

		public ArticleBuilder articleId(Long articleId) {
			this.articleId = articleId;
			return this;
		}

		public ArticleBuilder title(String title) {
			this.title = title;
			return this;
		}

		public ArticleBuilder author(String author) {
			this.author = author;
			return this;
		}

		public ArticleBuilder date(String date) {
			this.date = date;
			return this;
		}

		public ArticleBuilder text(String text) {
			this.text = text;
			return this;
		}

		public ArticleBuilder image(InputStream image) {
			this.image = image;
			return this;
		}

		public Article build() {
			return new Article(articleId, title, author, date, text, image);
		}
	}
}
