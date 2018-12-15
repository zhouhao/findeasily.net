package net.findeasily.website.domain;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getId();
    }

    public Role getRole() {
        return Role.valueOf(user.getRole());
    }

    @Override
    public boolean isEnabled() {
        return user.getActivated();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getLockStatus() == 0;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
