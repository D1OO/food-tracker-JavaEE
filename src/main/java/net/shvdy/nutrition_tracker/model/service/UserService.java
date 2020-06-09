package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.dto.UserProfileDTO;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.exception.BadCredentialsException;
import net.shvdy.nutrition_tracker.model.service.mapper.UserEntityMapper;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Locale;

public class UserService {

    private UserDAO userDao;
    private UserEntityMapper entityMapper;

    public UserService(UserDAO userDao, UserEntityMapper entityMapper) {
        this.userDao = userDao;
        this.entityMapper = entityMapper;
    }

    public void save(User user) {
        userDao.create(user);
    }

    public void updateProfile(UserProfileDTO userProfileDTO) {
        userDao.updateProfile(entityMapper.userProfileDTOToEntity(userProfileDTO));
    }

    public UserDTO findByUsernameLocalised(String username, Locale locale) throws BadCredentialsException {
        return entityMapper.entityToDTO(userDao.findByUsernameLocalised(username, locale)
                .orElseThrow(() -> new BadCredentialsException(String.format("Username '%s' not found", username))));
    }

    public UserDTO findByUsernameVerifyPassword(String username, String password, Locale locale)
            throws BadCredentialsException {

        User user = userDao.findByUsernameLocalised(username, locale)
                .orElseThrow(() -> new BadCredentialsException(String.format("username '%s' not found", username)));

        if (!BCrypt.checkpw(password, user.getPassword()))
            throw new BadCredentialsException(String.format("wrong password for username: %s", username));

        return entityMapper.entityToDTO(user);
    }

    public List<User> findGroup(String adminUsername, Locale locale) {
        return userDao.findGroup(adminUsername, locale);
    }

}
