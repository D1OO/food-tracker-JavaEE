package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.dao.mapper.DailyRecordMapper;
import net.shvdy.nutrition_tracker.model.service.mapper.UserEntityMapper;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static UserService userService() {
        return new UserService(DAOFactory.getInstance().getUserDAO(), new UserEntityMapper());
    }

    public static DailyRecordService dailyRecordService() {
        return new DailyRecordService(DAOFactory.getInstance().getDailyRecordDAO(), new DailyRecordMapper());
    }
}
