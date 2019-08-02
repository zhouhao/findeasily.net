package net.findeasily.website.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", insertable = false, nullable = false)
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "register_time", nullable = false)
    private Timestamp registerTime;

    @Column(name = "activated")
    private Boolean activated = Boolean.FALSE;

    @Column(name = "lock_status", nullable = false)
    private Integer lockStatus = 0;

    @Column(name = "role", nullable = false)
    private String role = "USER";


}