package net.shvdy.nutrition_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.shvdy.nutrition_tracker.controller.command.CommandEnum;
import net.shvdy.nutrition_tracker.model.service.ArticleService;
import net.shvdy.nutrition_tracker.model.service.DailyRecordService;
import net.shvdy.nutrition_tracker.model.service.FoodService;
import net.shvdy.nutrition_tracker.model.service.UserService;
import org.apache.logging.log4j.Logger;

/**
 * 28.05.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class ContextContainer {

	private static UserService userService;
	private static DailyRecordService dailyRecordService;
	private static FoodService foodService;
	private static ArticleService articleService;
	private static ObjectMapper jacksonObjectMapper;
	private static Logger logger;

	public static UserService getUserService() {
		return userService;
	}

	public static DailyRecordService getDailyRecordService() {
		return dailyRecordService;
	}

	public static FoodService getFoodService() {
		return foodService;
	}

	public static ArticleService getArticleService(){return articleService;}

	public static ObjectMapper getJacksonObjectMapper() {return jacksonObjectMapper;}

	public static Logger getLogger() {
		return logger;
	}

	public static void injectServices(UserService userService,
									  DailyRecordService dailyRecordService,
									  FoodService foodService,
									  ArticleService articleService,
									  ObjectMapper jacksonObjectMapper) {
		ContextContainer.userService = userService;
		ContextContainer.dailyRecordService = dailyRecordService;
		ContextContainer.foodService = foodService;
		ContextContainer.articleService = articleService;
		ContextContainer.jacksonObjectMapper = jacksonObjectMapper;
	}

	public static void injectLogger(Logger logger) {
		ContextContainer.logger = logger;
	}
}
