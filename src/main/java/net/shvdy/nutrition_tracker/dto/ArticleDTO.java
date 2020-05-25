package net.shvdy.nutrition_tracker.dto;

import java.io.InputStream;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleDTO {

	private String title;
	private String author;
	private String date;
	private String text;
	private InputStream image;

	public ArticleDTO(String title, String author, String date, String text, InputStream image) {
		this.title = title;
		this.author = author;
		this.date = date;
		this.text = text;
		this.image = image;
	}

	public static ArticleDTOBuilder builder() {
		return new ArticleDTOBuilder();
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


	public static final class ArticleDTOBuilder {
		private String title;
		private String author;
		private String date;
		private String text;
		private InputStream image;

		private ArticleDTOBuilder() {
		}

		public ArticleDTOBuilder title(String title) {
			this.title = title;
			return this;
		}

		public ArticleDTOBuilder author(String author) {
			this.author = author;
			return this;
		}

		public ArticleDTOBuilder date(String date) {
			this.date = date;
			return this;
		}

		public ArticleDTOBuilder text(String text) {
			this.text = text;
			return this;
		}

		public ArticleDTOBuilder image(InputStream image) {
			this.image = image;
			return this;
		}

		public ArticleDTO build() {
			return new ArticleDTO(title, author, date, text, image);
		}
	}
}
