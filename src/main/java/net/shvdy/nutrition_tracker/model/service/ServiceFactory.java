package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.service.mapper.DailyRecordEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.FoodEntityMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.UserEntityMapper;

import javax.naming.NamingException;
import java.io.IOException;

public abstract class ServiceFactory {

    public static UserService userService() throws IOException, NamingException {
        return new UserService(DAOFactory.getInstance().getUserDAO(), new UserEntityMapper());
    }

    public static DailyRecordService dailyRecordService() throws IOException, NamingException {
        return new DailyRecordService(DAOFactory.getInstance().getDailyRecordDAO(), new DailyRecordEntityMapper());
    }

    public static FoodService foodService() throws IOException, NamingException {
        return new FoodService(DAOFactory.getInstance().getFoodDAO(), new FoodEntityMapper());
    }


}
