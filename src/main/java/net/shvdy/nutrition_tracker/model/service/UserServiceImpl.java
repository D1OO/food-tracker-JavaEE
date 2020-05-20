package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.LoginDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.exception.InvalidPasswordException;
import net.shvdy.nutrition_tracker.model.exception.UserNotFoundException;
import net.shvdy.nutrition_tracker.model.service.mapper.UserMapper;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDAO userDao;
    private final UserMapper userMapper;

    public UserServiceImpl(UserDAO userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) throws SQLException {
        userDao.create(user);
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("Username '%s' not found", username)));
    }

    @Override
    public UserDTO findByLoginDTO(LoginDTO loginDTO) throws SQLException {
        User user = findByUsername(loginDTO.getUsername());


        if (!loginDTO.getPassword().equals(user.getPassword())) {
            throw new InvalidPasswordException();
        }
        return userMapper.entityToDTO(user);
    }
}
