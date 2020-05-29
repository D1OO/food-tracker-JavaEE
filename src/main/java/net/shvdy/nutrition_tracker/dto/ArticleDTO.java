package net.shvdy.nutrition_tracker.dto;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleDTO {
    private int articleId;
    private String title;
    private String authorName;
    private String date;
    private String text;
    private String base64Image;

    public ArticleDTO(int articleId, String title, String authorName, String date, String text, String base64Image) {
        this.articleId = articleId;
        this.title = title;
        this.authorName = authorName;
        this.date = date;
        this.text = text;
        this.base64Image = base64Image;
    }

    public static ArticleDTOBuilder builder() {
        return new ArticleDTOBuilder();
    }

    public String getTitle() {
        return title;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public static final class ArticleDTOBuilder {
        private int articleId;
        private String title;
        private String authorName;
        private String date;
        private String text;
        private String base64Image;

        private ArticleDTOBuilder() {
        }

        public ArticleDTOBuilder articleId(int articleId) {
            this.articleId = articleId;
            return this;
        }

        public ArticleDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ArticleDTOBuilder authorName(String author) {
            this.authorName = authorName;
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

        public ArticleDTOBuilder base64Image(String base64Image) {
            this.base64Image = base64Image;
            return this;
        }

        public ArticleDTO build() {
            return new ArticleDTO(articleId, title, authorName, date, text, base64Image);
        }
    }
}
