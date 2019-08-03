package net.findeasily.website.domain;

import org.springframework.security.core.authority.AuthorityUtils;

import lombok.EqualsAndHashCode;
import net.findeasily.website.entity.User;

@EqualsAndHashCode(callSuper = true)
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

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

    public String getAvatar() {
        return "/public/user/" + user.getId() + "/avatar";
    }
}
