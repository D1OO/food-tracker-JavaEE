package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.model.dao.DAOFactory;
import net.shvdy.nutrition_tracker.model.service.mapper.UserMapperImpl;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static UserService userService() {
        return new UserServiceImpl(DAOFactory.getInstance().getUserDAO(), new UserMapperImpl());
    }
}
