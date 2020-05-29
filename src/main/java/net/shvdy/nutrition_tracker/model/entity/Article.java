package net.shvdy.nutrition_tracker.model.entity;

import java.io.InputStream;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Article {
    private int articleId;
    private String title;
    private Long authorId;
    private String date;
    private String authorFirstName;
    private String authorLastName;
    private String text;
    private InputStream image;

    public Article(int articleId, String title, Long authorId, String date, String authorFirstName,
                   String authorLastName, String text, InputStream image) {
        this.articleId = articleId;
        this.title = title;
        this.authorId = authorId;
        this.date = date;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.text = text;
        this.image = image;
    }

    public static ArticleBuilder builder() {
        return new ArticleBuilder();
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
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
        private int articleId;
        private String title;
        private Long authorId;
        private String date;
        private String authorFirstName;
        private String authorLastName;
        private String text;
        private InputStream image;

        private ArticleBuilder() {
        }

        public ArticleBuilder articleId(int articleId) {
            this.articleId = articleId;
            return this;
        }

        public ArticleBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ArticleBuilder authorId(Long authorId) {
            this.authorId = authorId;
            return this;
        }

        public ArticleBuilder authorFirstName(String authorFirstName) {
            this.authorFirstName = authorFirstName;
            return this;
        }

        public ArticleBuilder authorLastName(String authorLastName) {
            this.authorLastName = authorLastName;
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
            return new Article(articleId, title, authorId, date, authorFirstName,
                    authorLastName, text, image);
        }
    }
}
