package net.shvdy.nutrition_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ContextHolder {

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

	public static ArticleService getArticleService() {
		return articleService;
	}

	public static ObjectMapper getObjectMapper() {
		return jacksonObjectMapper;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void injectServices(UserService userService,
									  DailyRecordService dailyRecordService,
									  FoodService foodService,
									  ArticleService articleService) {
		ContextHolder.userService = userService;
		ContextHolder.dailyRecordService = dailyRecordService;
		ContextHolder.foodService = foodService;
		ContextHolder.articleService = articleService;
	}

	public static void injectObjectMapper(ObjectMapper jacksonObjectMapper) {
		ContextHolder.jacksonObjectMapper = jacksonObjectMapper;
	}

	public static void injectLogger(Logger logger) {
		ContextHolder.logger = logger;
	}
}
