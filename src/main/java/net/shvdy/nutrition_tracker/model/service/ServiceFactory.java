package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.service.mapper.ArticleEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.DailyRecordEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.FoodEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.UserEntityMapper;

import javax.naming.NamingException;
import java.io.IOException;

public abstract class ServiceFactory {

    private static DAOFactory daoFactory;

    public static void injectDaoFactory(DAOFactory daoFactory) {
        ServiceFactory.daoFactory = daoFactory;
    }

    public static UserService userService() {
        return new UserService(daoFactory.getUserDAO(), new UserEntityMapper());
    }

    public static DailyRecordService dailyRecordService()  {
        return new DailyRecordService(daoFactory.getDailyRecordDAO(), new DailyRecordEntityMapper());
    }

    public static FoodService foodService() {
        return new FoodService(daoFactory.getFoodDAO(), new FoodEntityMapper());
    }

    public static ArticleService articleService() {
        return new ArticleService(daoFactory.getArticleDAO(), new ArticleEntityMapper());
    }
}
