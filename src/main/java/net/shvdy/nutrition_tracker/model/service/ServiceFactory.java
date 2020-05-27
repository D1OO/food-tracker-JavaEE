package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.service.mapper.ArticleEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.DailyRecordEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.FoodEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.UserEntityMapper;

import javax.naming.NamingException;
import java.io.IOException;

public abstract class ServiceFactory {

    public static UserService userService() throws IOException, NamingException {
        return new UserService(DAOFactory.getJDBCInstance().getUserDAO(), new UserEntityMapper());
    }

    public static DailyRecordService dailyRecordService() throws IOException, NamingException {
        return new DailyRecordService(DAOFactory.getJDBCInstance().getDailyRecordDAO(), new DailyRecordEntityMapper());
    }

    public static FoodService foodService() throws IOException, NamingException {
        return new FoodService(DAOFactory.getJDBCInstance().getFoodDAO(), new FoodEntityMapper());
    }

    public static ArticleService articleService() throws IOException, NamingException {
        return new ArticleService(DAOFactory.getJDBCInstance().getArticleDAO(), new ArticleEntityMapper());
    }
}
