package net.findeasily.website.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import net.findeasily.website.entity.User;

@Getter
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String email;
    private Date registerTime;
    private Boolean active;
    private String role;

    public UserDto(@NotNull User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.registerTime = user.getRegisterTime();
        this.active = user.getActivated() && user.getLockStatus() == 0;
        this.role = user.getRole();
    }

}
