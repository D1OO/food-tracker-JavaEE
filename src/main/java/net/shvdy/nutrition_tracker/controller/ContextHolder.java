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

	public static UserService userService() {
		return userService;
	}

	public static DailyRecordService dailyRecordService() {
		return dailyRecordService;
	}

	public static FoodService foodService() {
		return foodService;
	}

	public static ArticleService articleService() {
		return articleService;
	}

	public static ObjectMapper objectMapper() {
		return jacksonObjectMapper;
	}

	public static Logger logger() {
		return logger;
	}

	public static void injectUserService(UserService userService) {
		ContextHolder.userService = userService;
	}

	public static void injectDailyRecordService(DailyRecordService dailyRecordService) {
		ContextHolder.dailyRecordService = dailyRecordService;
	}

	public static void injectFoodService(FoodService foodService) {
		ContextHolder.foodService = foodService;
	}

	public static void injectArticleService(ArticleService articleService) {
		ContextHolder.articleService = articleService;
	}

	public static void injectObjectMapper(ObjectMapper jacksonObjectMapper) {
		ContextHolder.jacksonObjectMapper = jacksonObjectMapper;
	}

	public static void injectLogger(Logger logger) {
		ContextHolder.logger = logger;
	}
}
