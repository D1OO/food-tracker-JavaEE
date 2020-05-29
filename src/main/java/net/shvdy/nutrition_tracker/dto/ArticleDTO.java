package net.shvdy.nutrition_tracker.dto;

/**
 * 24.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleDTO {
    private int articleId;
    private String titleLocalisation;
    private String titleEN;
    private String titleRU;
    private String authorName;
    private String date;
    private String textLocalisation;
    private String textEN;
    private String textRU;
    private String base64Image;

    public ArticleDTO(int articleId, String titleLocalisation, String titleEN, String titleRU, String authorName,
                      String date, String textLocalisation, String textEN, String textRU, String base64Image) {
        this.articleId = articleId;
        this.titleLocalisation = titleLocalisation;
        this.titleEN = titleEN;
        this.titleRU = titleRU;
        this.authorName = authorName;
        this.date = date;
        this.textLocalisation = textLocalisation;
        this.textEN = textEN;
        this.textRU = textRU;
        this.base64Image = base64Image;
    }

    public static ArticleDTOBuilder builder() {
        return new ArticleDTOBuilder();
    }

    public String getTitleLocalisation() {
        return titleLocalisation;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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

    public String getTextLocalisation() {
        return textLocalisation;
    }

    public void setTextLocalisation(String textLocalisation) {
        this.textLocalisation = textLocalisation;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public static final class ArticleDTOBuilder {
        private int articleId;
        private String titleLocalisation;
        private String titleEN;
        private String titleRU;
        private String authorName;
        private String date;
        private String textLocalisation;
        private String textEN;
        private String textRU;
        private String base64Image;

        private ArticleDTOBuilder() {
        }

        public ArticleDTOBuilder articleId(int articleId) {
            this.articleId = articleId;
            return this;
        }

        public ArticleDTOBuilder titleLocalisation(String titleLocalisation) {
            this.titleLocalisation = titleLocalisation;
            return this;
        }

        public ArticleDTOBuilder titleEN(String titleEN) {
            this.titleEN = titleEN;
            return this;
        }

        public ArticleDTOBuilder titleRU(String titleRU) {
            this.titleRU = titleRU;
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

        public ArticleDTOBuilder textEN(String textEN) {
            this.textEN = textEN;
            return this;
        }

        public ArticleDTOBuilder textRU(String textRU) {
            this.textRU = textRU;
            return this;
        }

        public ArticleDTOBuilder textLocalisation(String textLocalisation) {
            this.textLocalisation = textLocalisation;
            return this;
        }

        public ArticleDTOBuilder base64Image(String base64Image) {
            this.base64Image = base64Image;
            return this;
        }

        public ArticleDTO build() {
            return new ArticleDTO(articleId, titleLocalisation, titleEN, textRU, authorName, date,
                    textLocalisation, textEN, textRU, base64Image);
        }
    }
}
