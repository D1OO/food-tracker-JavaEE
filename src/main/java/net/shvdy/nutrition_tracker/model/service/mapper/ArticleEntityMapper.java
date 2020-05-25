package net.shvdy.nutrition_tracker.model.service.mapper;

import net.shvdy.nutrition_tracker.dto.ArticleDTO;
import net.shvdy.nutrition_tracker.model.entity.Article;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 25.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ArticleEntityMapper {

	public List<ArticleDTO> entityListToDTO(List<Article> articleList) {
		System.out.print(articleList.get(0).getDate());
		return articleList.stream().map(x -> ArticleDTO.builder()
				.title(x.getTitle())
				.date(readDateString(x.getDate()))
				.text(x.getText())
				.authorName(x.getAuthorFirstName() + x.getAuthorLastName())
				.base64Image(getBase64String(x.getImage()))
				.build()).collect(Collectors.toList());
	}

	private String getBase64String(InputStream inputStream) {
		try {
			return readImageString(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	private String readDateString(String string) {
		LocalDateTime date = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return date.format(DateTimeFormatter.ofPattern("d MMM kk:mm"));
//		return date.getDayOfMonth() + "/" + date.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru"))
//					+ " " + date.getHour() + ":" + date.getMinute();
	}

	private String readImageString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes = outputStream.toByteArray();
		inputStream.close();
		outputStream.close();
		return Base64.getEncoder().encodeToString(imageBytes);
	}
}
