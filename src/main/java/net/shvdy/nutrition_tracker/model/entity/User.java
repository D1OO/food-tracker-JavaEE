package net.shvdy.nutrition_tracker.model.entity;

public class User {
    private Long id;
    private String username;
    private String password;
    private UserProfile userProfile;
    private boolean account_non_expired;
    private boolean account_non_locked;
    private boolean credentials_non_expired;
    private boolean enabled;
    private Role role;

    public User(Long id, String username, String password, UserProfile userProfile, boolean account_non_expired,
                boolean account_non_locked, boolean credentials_non_expired, boolean enabled, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userProfile = userProfile;
        this.account_non_expired = account_non_expired;
        this.account_non_locked = account_non_locked;
        this.credentials_non_expired = credentials_non_expired;
        this.enabled = enabled;
        this.role = role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public boolean isAccount_non_expired() {
        return account_non_expired;
    }

    public void setAccount_non_expired(boolean account_non_expired) {
        this.account_non_expired = account_non_expired;
    }

    public boolean isAccount_non_locked() {
        return account_non_locked;
    }

    public void setAccount_non_locked(boolean account_non_locked) {
        this.account_non_locked = account_non_locked;
    }

    public boolean isCredentials_non_expired() {
        return credentials_non_expired;
    }

    public void setCredentials_non_expired(boolean credentials_non_expired) {
        this.credentials_non_expired = credentials_non_expired;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static class UserBuilder {

        private Long id;
        private String username;
        private String password;
        private UserProfile userProfile;
        private boolean account_non_expired;
        private boolean account_non_locked;
        private boolean credentials_non_expired;
        private boolean enabled;
        private Role role;

        private UserBuilder() {
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder userProfile(UserProfile userProfile) {
            this.userProfile = userProfile;
            return this;
        }

        public UserBuilder account_non_expired(boolean account_non_expired) {
            this.account_non_expired = account_non_expired;
            return this;
        }

        public UserBuilder account_non_locked(boolean account_non_locked) {
            this.account_non_locked = account_non_locked;
            return this;
        }

        public UserBuilder credentials_non_expired(boolean credentials_non_expired) {
            this.credentials_non_expired = credentials_non_expired;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {
            return new User(this.id, this.username, this.password, this.userProfile, this.account_non_expired, this.account_non_locked,
                    this.credentials_non_expired, this.enabled, this.role);
        }
    }
}
