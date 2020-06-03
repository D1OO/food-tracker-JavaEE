package net.shvdy.nutrition_tracker.model.entity;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Article {
    private int articleId;
    private String titleLocalisation;
    private String titleEN;
    private String titleRU;
    private Long authorId;
    private String date;
    private String authorFirstName;
    private String authorLastName;
    private String textLocalisation;
    private String textEN;
    private String textRU;
    private byte[] imageBytes;

    public Article() {
    }

    public Article(int articleId, String titleLocalisation, String titleEN, String titleRU, Long authorId, String date,
                   String authorFirstName, String authorLastName, String textLocalisation, String textEN, String textRU,
                   byte[] imageBytes) {
        this.articleId = articleId;
        this.titleLocalisation = titleLocalisation;
        this.titleEN = titleEN;
        this.titleRU = titleRU;
        this.authorId = authorId;
        this.date = date;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.textLocalisation = textLocalisation;
        this.textEN = textEN;
        this.textRU = textRU;
        this.imageBytes = imageBytes;
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

    public String getTitleLocalisation() {
        return titleLocalisation;
    }

    public void setTitleLocalisation(String titleLocalisation) {
        this.titleLocalisation = titleLocalisation;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public String getTitleRU() {
        return titleRU;
    }

    public void setTitleRU(String titleRU) {
        this.titleRU = titleRU;
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

    public String getTextLocalisation() {
        return textLocalisation;
    }

    public void setTextLocalisation(String textLocalisation) {
        this.textLocalisation = textLocalisation;
    }

    public String getTextEN() {
        return textEN;
    }

    public void setTextEN(String textEN) {
        this.textEN = textEN;
    }

    public String getTextRU() {
        return textRU;
    }

    public void setTextRU(String textRU) {
        this.textRU = textRU;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public static final class ArticleBuilder {
        private int articleId;
        private String titleLocalisation;
        private String titleEN;
        private String titleRU;
        private Long authorId;
        private String date;
        private String authorFirstName;
        private String authorLastName;
        private String textLocalisation;
        private String textEN;
        private String textRU;
        private byte[] imageBytes;

        private ArticleBuilder() {
        }

        public ArticleBuilder articleId(int articleId) {
            this.articleId = articleId;
            return this;
        }

        public ArticleBuilder titleLocalisation(String titleLocalisation) {
            this.titleLocalisation = titleLocalisation;
            return this;
        }

        public ArticleBuilder titleEN(String titleEN) {
            this.titleEN = titleEN;
            return this;
        }

        public ArticleBuilder titleRU(String titleRU) {
            this.titleRU = titleRU;
            return this;
        }

        public ArticleBuilder authorId(Long authorId) {
            this.authorId = authorId;
            return this;
        }

        public ArticleBuilder date(String date) {
            this.date = date;
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

        public ArticleBuilder textLocalisation(String textLocalisation) {
            this.textLocalisation = textLocalisation;
            return this;
        }

        public ArticleBuilder textEN(String textEN) {
            this.textEN = textEN;
            return this;
        }

        public ArticleBuilder textRU(String textRU) {
            this.textRU = textRU;
            return this;
        }

        public ArticleBuilder imageBytes(byte[] imageBytes) {
            this.imageBytes = imageBytes;
            return this;
        }

        public Article build() {
            return new Article(articleId, titleLocalisation, titleEN, titleRU, authorId, date, authorFirstName,
                    authorLastName, textLocalisation, textEN, textRU, imageBytes);
        }
    }
}
