package net.shvdy.nutrition_tracker.model.dao;

import net.shvdy.nutrition_tracker.dto.UserDTO;
import net.shvdy.nutrition_tracker.model.entity.Notification;
import net.shvdy.nutrition_tracker.model.entity.User;
import net.shvdy.nutrition_tracker.model.entity.UserProfile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByUsername(String username);

    void create(User user);

    void updateProfile(UserProfile userProfile);

    List<User> findGroup(String adminUsername);

    Set<Notification> findNotifications(UserDTO receiver);

    void createGroupInvitation(Notification n);

    void acceptGroupInvitation(Notification n);

    void declineGroupInvitation(Notification n);
}
