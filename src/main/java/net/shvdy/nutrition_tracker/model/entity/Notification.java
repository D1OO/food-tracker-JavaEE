package net.shvdy.nutrition_tracker.model.entity;

import net.shvdy.nutrition_tracker.dto.UserDTO;

/**
 * 09.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
public class Notification {
    private UserDTO sender;
    private UserDTO receiver;
    private String dateTime;
    private String message;

    public Notification() {
    }

    public Notification(UserDTO sender, UserDTO receiver, String dateTime, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = dateTime;
        this.message = message;
    }

    public static NotificationBuilder builder() {
        return new NotificationBuilder();
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public UserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDTO receiver) {
        this.receiver = receiver;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static final class NotificationBuilder {
        private UserDTO sender;
        private UserDTO receiver;
        private String dateTime;
        private String message;

        private NotificationBuilder() {
        }

        public NotificationBuilder sender(UserDTO sender) {
            this.sender = sender;
            return this;
        }

        public NotificationBuilder receiver(UserDTO receiver) {
            this.receiver = receiver;
            return this;
        }

        public NotificationBuilder dateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public NotificationBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Notification build() {
            Notification notification = new Notification();
            notification.setSender(sender);
            notification.setReceiver(receiver);
            notification.setDateTime(dateTime);
            notification.setMessage(message);
            return notification;
        }
    }
}
