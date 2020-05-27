package net.shvdy.nutrition_tracker.model.service;

import net.shvdy.nutrition_tracker.dto.LoginDTO;
import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.dao.UserDAO;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.exception.InvalidPasswordException;
import net.shvdy.nutrition_tracker.model.exception.UserNotFoundException;
import net.shvdy.nutrition_tracker.model.service.mapper.UserEntityMapper;

import java.sql.SQLException;

public class UserService {

	private UserDAO userDao;
	private UserEntityMapper entityMapper;

	public UserService(UserDAO userDao, UserEntityMapper entityMapper) {
		this.userDao = userDao;
		this.entityMapper = entityMapper;
	}

	public void save(User user) throws SQLException {
		userDao.create(user);
	}

	public User findByUsername(String username) throws SQLException {
		return userDao.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(String.format("Username '%s' not found", username)));
	}

	public UserDTO findByLoginDTO(LoginDTO loginDTO) throws SQLException, UserNotFoundException, InvalidPasswordException {
		User user = findByUsername(loginDTO.getUsername());
		if (!loginDTO.getPassword().equals(user.getPassword())) {
			throw new InvalidPasswordException();
		}
		return entityMapper.entityToDTO(user);
	}
}
